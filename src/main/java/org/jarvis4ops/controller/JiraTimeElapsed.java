package org.jarvis4ops.controller;

import java.util.Base64;
import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.bean.TimeBean;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.JiraIssueResponseHelper;
import org.jarvis4ops.bean.SlackResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
public class JiraTimeElapsed {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);
	
	@Autowired
	private Configurations configObj;
	
	@Autowired
	private JiraIssueResponseHelper jiraIssueResponseHelper;
	
	@Autowired
	private Environment environment;
	
	@RequestMapping(path="/getTimeElapsedInJira")
	public String getTimeElapsedInJira()
	{
		String plainCreds = configObj.getJiraCreds();

		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		String slackChannel_jira = "@bverma1";
		
		log.info("base64Creds - " + base64Creds);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic "+base64Creds);
		headers.set("Accept", "application/json");
	    headers.set("Content-Type", "application/json");
	    
		HttpEntity<String> entity = new HttpEntity<String>(headers);

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getPrevDayIncidentRockstarJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		Map<String, TimeBean> jiraTimeElasedMap = jiraIssueResponseHelper.issuesTimeElapsed(response.getBody().getIssues());

		HttpHeaders headerSlack = new HttpHeaders();
		headerSlack.set("Authorization", "None");
		headerSlack.set("Accept", "application/json");
	    headerSlack.set("Content-Type", "application/json");
		HttpEntity<String> entityslack = new HttpEntity<String>(headerSlack);		
		
		log.info(jiraTimeElasedMap.entrySet().toString());
		
		if (null != jiraTimeElasedMap && jiraTimeElasedMap.size()>0) {
			invokeTimeElapsedSlackServiceToPost(jiraTimeElasedMap);
		}
		
		
//		ResponseEntity<SlackResponse> responseSlack = restTemplate.postForEntity("https://slack.com/api/chat.postMessage?token=" + configObj.getSlackToken() +"&channel=" +slackChannel_jira +"&text=Slack Message for time less than 5 mins&pretty=1", entityslack, SlackResponse.class);
//		log.info(responseSlack.toString());
//		return responseSlack.toString();
		return "";
//		return jiraTimeElasedMap.keySet().toString();
	}
	
	public void invokeTimeElapsedSlackServiceToPost(Map<String, TimeBean> jiraTimeElasedMap) {

		Gson gson = new Gson();
		log.info("Response JSON for rockstars: " + gson.toJson(jiraTimeElasedMap));
		String invalidJiraTimeIssueCountJson = gson.toJson(jiraTimeElasedMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(invalidJiraTimeIssueCountJson, headerMap);
        String slashUrl = configObj.getHost()+environment.getProperty("server.port")+"/postInvalidTimeOnSlack";
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        //log.info("Response: ", response);
        log.info("Response: " + response);
    }

}
