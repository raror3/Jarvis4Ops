package org.jarvis4ops.controller;

import java.net.URI;
import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.ApiHelper;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

@RestController
public class KanbanImprovementController {
	private static final Logger log = LoggerFactory.getLogger(KanbanImprovementController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraHelper jiraIssueResponseHelper;
	
	@Autowired
	private Environment environment;

	@Autowired
	private ApiHelper apiHelper;
	
	@RequestMapping(value="/checkOpenScIssuesAndPost", method = { RequestMethod.GET })
	public String getOpenSiteConfidenceIncidents() {

		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getOpenSiteConfidenceIncidentsJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		if (response.getBody().getTotal() > configObj.getOpenScIncidentsShout()) {
			slackSCIssueNotification(response.getBody().getTotal());
		}

		return Integer.toString(response.getBody().getTotal());
    }

	@RequestMapping(value="openIncidents", method= { RequestMethod.GET })
	public String getOpenIncidents() {

		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getOpenIncidentsJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		if (response.getBody().getTotal() > configObj.getJiraOpenIncidentsShout()) {
			slackOpenIncidentNotification(response.getBody().getTotal());
		}

		return Integer.toString(response.getBody().getTotal());
	}

	private void slackOpenIncidentNotification(int total) {
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(headerMap);
        String slashUrl = configObj.getHost()+environment.getProperty("server.port")+"/postScNotificationOnSlack";
        URI targetUrl= UriComponentsBuilder.fromUriString(configObj.getHost()+environment.getProperty("server.port")).
        		path("/postOpenIncidentNotificationOnSlack").queryParam("openIncidents", total).build().toUri();
        String response = restTemplate.postForObject(targetUrl, entity, String.class);
        //log.info("Response: ", response);
        log.info("Response: " + response);
	}

	private void slackSCIssueNotification(Integer total) {
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(headerMap);
        String slashUrl = configObj.getHost()+environment.getProperty("server.port")+"/postScNotificationOnSlack";
        URI targetUrl= UriComponentsBuilder.fromUriString(configObj.getHost()+environment.getProperty("server.port")).
        		path("/postScNotificationOnSlack").queryParam("openIncidents", total).build().toUri();
        String response = restTemplate.postForObject(targetUrl, entity, String.class);
        //log.info("Response: ", response);
        log.info("Response: " + response);
	}

	public void invokeSlackServiceToPost(Map<String, Integer> rockstarsJiraIssueCountMap) {

		Gson gson = new Gson();
		log.info("Response JSON for rockstars: " + gson.toJson(rockstarsJiraIssueCountMap));
		String rockstarsJiraIssueCountJson = gson.toJson(rockstarsJiraIssueCountMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(rockstarsJiraIssueCountJson, headerMap);
        String slashUrl = configObj.getHost()+environment.getProperty("server.port")+"/postOnSlack";
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        //log.info("Response: ", response);
        log.info("Response: " + response);
    }

	public IssueDetails index(RestTemplate restTemplate) {

		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

		ResponseEntity<IssueDetails> response = restTemplate.exchange(configObj.getSampleJiraEndPoint(), HttpMethod.GET, entity, IssueDetails.class);
		IssueDetails issueDetails = response.getBody();
		log.info(response.toString());
		
		return issueDetails;
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
