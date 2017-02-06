package org.jarvis4ops.helper;

import java.util.Map;

import org.jarvis4ops.configurations.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class ServiceInvoker {
	private static final Logger log = LoggerFactory.getLogger(ServiceInvoker.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private Environment environment;

	@Autowired
	private ApiHelper apiHelper;

	/**
	 * This method invokes API service to post rewards for the rockstars passed in the request.
	 * This method takes the map of rockstars, creates the request of the API service and sends the map data as request body.
	 * @param rockstarsJiraIssueCountMap HashMap<String, Integer>
	 * @return String
	 */
	public String invokeBonusLyServiceToPost(Map<String, Integer> rockstarsJiraIssueCountMap) {

		Gson gson = new Gson();
		log.info("Request JSON for rockstars: " + gson.toJson(rockstarsJiraIssueCountMap));
		String rockstarsJiraIssueCountJson = gson.toJson(rockstarsJiraIssueCountMap);

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(rockstarsJiraIssueCountJson, headerMap);
        String bonusLyUrl = configObj.getHost()+environment.getProperty("server.port")+"/rewardRockstars";
        String response = restTemplate.postForObject(bonusLyUrl, entity, String.class);
        log.info("Response from bonusLy Service: " + response);
        return response;
    }

	/**
	 * This method invokes API service to post message on Slack for the rockstars passed in the request.
	 * This method takes the map of rockstars, creates the request of the API service and sends the map data as request body.
	 * @param rockstarsJiraIssueCountMap HashMap<String, Integer>
	 * @return String
	 */
	public String invokeSlackServiceToPost(Map<String, Integer> rockstarsJiraIssueCountMap) {

		Gson gson = new Gson();
		log.info("Response JSON for rockstars: " + gson.toJson(rockstarsJiraIssueCountMap));
		String rockstarsJiraIssueCountJson = gson.toJson(rockstarsJiraIssueCountMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(rockstarsJiraIssueCountJson, headerMap);
        String slashUrl = configObj.getHost()+environment.getProperty("server.port")+"/postRockstarsOnSlack";
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        log.info("Response: " + response);
        return response;
    }

}
