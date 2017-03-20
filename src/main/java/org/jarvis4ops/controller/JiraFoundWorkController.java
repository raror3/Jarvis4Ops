package org.jarvis4ops.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.JiraHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class JiraFoundWorkController {
	private static final Logger log = LoggerFactory.getLogger(JiraFoundWorkController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraHelper jiraIssueResponseHelper;
	
	@Autowired
	private Environment environment;

	@RequestMapping(value = "/foundWork/{projectName}", method = { RequestMethod.GET })
	public String fetchFoundWorkAndAlert(@PathVariable(value = "projectName", required = false) String projectName) {

		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

		RestTemplate restTemplate = new RestTemplate();
		final String jql = configObj.getFoundWorkJql() + projectName;
		ResponseEntity<ArrayIssueDetails> foundWorkResponse = restTemplate.exchange(
				configObj.getJiraEndPoint() + jql, HttpMethod.GET, entity,
				ArrayIssueDetails.class);
		final int foundWorkCount = foundWorkResponse.getBody().getTotal();
		Map jiraFoundWorkMap = new HashMap(3);
		jiraFoundWorkMap.put("FoundWorkCount", foundWorkResponse.getBody().getTotal());
		jiraFoundWorkMap.put("projectName", projectName);
		if (foundWorkCount >= 1) {
			StringBuffer jiraId = new StringBuffer();
			for (int i = 0; i < foundWorkResponse.getBody().getIssues().size(); i++) {
				if (i>0) {
					jiraId.append(",");
				}
				jiraId.append(foundWorkResponse.getBody().getIssues().get(i).getKey() + "::::" + foundWorkResponse.getBody().getIssues().get(i).getFields().getSummary());
			}
			jiraFoundWorkMap.put("storyId", jiraId.toString());
			jiraIssueResponseHelper.invokeSlackPostFoundWork(
					jiraFoundWorkMap);
		}
		Gson gson = new Gson();
		return gson.toJson(jiraFoundWorkMap);
	}


	@Bean
	private RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
