package org.jarvis4ops.controller;

import java.util.HashMap;
import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.JiraIssueResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class JiraWipLimitController {
	private static final Logger log = LoggerFactory.getLogger(JiraWipLimitController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraIssueResponseHelper jiraIssueResponseHelper;
	
	@Autowired
	private Environment environment;

	@RequestMapping(value="/validateWipLimitsAndAlert", method = { RequestMethod.GET })
	public String validateWipLimitsAndAlert() {

		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> inDevItemsResponse = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getTotalInDevItemsJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);
		ResponseEntity<ArrayIssueDetails> toDoItemsResponse = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getTotalToDoItemsJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);
		ResponseEntity<ArrayIssueDetails> inTestItemsResponse = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getTotalInTestItemsJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		Map<String, Integer> jiraMaxWipCountMap = new HashMap<String, Integer>(3);
		jiraMaxWipCountMap.put("InDev", inDevItemsResponse.getBody().getTotal());
		jiraMaxWipCountMap.put("InTest", inTestItemsResponse.getBody().getTotal());
		jiraMaxWipCountMap.put("ToDo", toDoItemsResponse.getBody().getTotal());

		if (jiraIssueResponseHelper.isJiraMaxWipLimitBreached(jiraMaxWipCountMap)) {
			jiraIssueResponseHelper.invokeSlackPostWipBreachedInfo(jiraMaxWipCountMap);
		}

		Gson gson = new Gson();
		return gson.toJson(jiraMaxWipCountMap);
    }

	@Bean
	private RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
