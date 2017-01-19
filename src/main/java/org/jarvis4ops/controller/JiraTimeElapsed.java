package org.jarvis4ops.controller;

import java.util.Base64;
import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.JiraIssueResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JiraTimeElapsed {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);
	
	@Autowired
	private Configurations configObj;
	
	@Autowired
	private JiraIssueResponseHelper jiraIssueResponseHelper;
	
	@RequestMapping(path="/getTimeElapsedInJira")
	public String getTimeElapsedInJira()
	{
		String plainCreds = configObj.getJiraCreds();

		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		log.info("base64Creds - " + base64Creds);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic "+base64Creds);
		headers.set("Accept", "application/json");
	    headers.set("Content-Type", "application/json");
	    
		HttpEntity<String> entity = new HttpEntity<String>(headers);

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getPrevDayIncidentRockstarJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		Map<String, Integer> jiraTimeElasedMap = jiraIssueResponseHelper.issuesTimeElapsed(response.getBody().getIssues());

		log.info(jiraTimeElasedMap.entrySet().toString());
		
		
		return "";
	}

}
