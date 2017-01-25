package org.jarvis4ops.controller;

import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.bean.DorParameters;
import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.DorDodIssuesHelper;
import org.jarvis4ops.helper.JiraIssueResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class JiraController {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraIssueResponseHelper jiraIssueResponseHelper;
	
	@Autowired
	private DorDodIssuesHelper dorIssuesHelper;

	@RequestMapping(path="/getPrevDayJiraRockstars")
	public String getPrevDayJiraRockstars() {
    	HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getPrevDayIncidentRockstarJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		Map<String, Integer> rockstarsJiraIssueCountMap = jiraIssueResponseHelper.maxIssueCount(response.getBody().getIssues());

		if (null != rockstarsJiraIssueCountMap && rockstarsJiraIssueCountMap.size()>0) {
			invokeSlackServiceToPost(rockstarsJiraIssueCountMap);
		}

		return rockstarsJiraIssueCountMap.keySet().toString();
    }

	public void invokeSlackServiceToPost(Map<String, Integer> rockstarsJiraIssueCountMap) {

		Gson gson = new Gson();
		log.info("Response JSON for rockstars: " + gson.toJson(rockstarsJiraIssueCountMap));
		String rockstarsJiraIssueCountJson = gson.toJson(rockstarsJiraIssueCountMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(rockstarsJiraIssueCountJson, headerMap);
        String slashUrl = configObj.getHost()+configObj.getPort()+"/postRockstarsOnSlack";
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        //log.info("Response: ", response);
        log.info("Response: " + response);
    }

	public IssueDetails index(RestTemplate restTemplate) {
		String base64Creds = "";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic "+base64Creds);
		headers.set("Accept", "application/json");
	    headers.set("Content-Type", "application/json");
	    
		HttpEntity<String> entity = new HttpEntity<String>(headers);	

		ResponseEntity<IssueDetails> response = restTemplate.exchange(configObj.getSampleJiraEndPoint(), HttpMethod.GET, entity, IssueDetails.class);
		IssueDetails issueDetails = response.getBody();
		log.info(response.toString());
		
		return issueDetails;
    }
	
	@RequestMapping(path="/getDorDodJira")
	public void issuesDorDod()
	{
		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getDorDodJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);
		
		Map<String, DorParameters> dorIssuesMap = dorIssuesHelper.issuesDorDodList(response.getBody().getIssues());
					
		if (null != dorIssuesMap && dorIssuesMap.size()>0) {
			invokeSlackServiceDor(dorIssuesMap);
		}

	}

	
	public void invokeSlackServiceDor(Map<String, DorParameters> dorJiraIssuesMap) {

		Gson gson = new Gson();
		String jiraIssuesJson = gson.toJson(dorJiraIssuesMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(jiraIssuesJson, headerMap);

        String slashUrl = configObj.getHost()+configObj.getPort()+"/postDorStatus";
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        
        log.info("Response: " + response);
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
