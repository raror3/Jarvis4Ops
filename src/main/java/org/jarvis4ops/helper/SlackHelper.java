package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.SlackMessagingConstants;
import org.jarvis4ops.controller.JiraController;
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
public class SlackHelper {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private Environment environment;
	
	@Autowired
	private SlackMessagingConstants slackMessagingConstants;

	public String getRockstarNames(Set<String> rockStarJiraIds) {
		Map<String, String> rockstarMap = new HashMap<String, String>(10);
		rockstarMap.put("agupta", "Akash");
		rockstarMap.put("htomar", "Himanshu");
		rockstarMap.put("hmisra", "Hari Om");
		rockstarMap.put("pkumar1", "Prabhat");
		rockstarMap.put("dkhandelwal", "Divyansh");
		rockstarMap.put("rarora", "Raghav");
		rockstarMap.put("bkaur", "Bikran");
		rockstarMap.put("jsahni@sapient.com", "Japneet");
		rockstarMap.put("pgoyal", "Pulkit");
		rockstarMap.put("aksharma", "Avinash");
		rockstarMap.put("hsrivastava", "Harshit");
		rockstarMap.put("hsinha", "Harshita");
		rockstarMap.put("vmathur", "Varuneshwar");
		rockstarMap.put("ayadav", "Antariksh");
		rockstarMap.put("marora", "Madhur");
		rockstarMap.put("sswami", "Sundeep");
		rockstarMap.put("ssingh1", "Sandeep");
		rockstarMap.put("rpandey", "Rahul");
		rockstarMap.put("sgupta1", "Sumit");
		rockstarMap.put("uagarwal", "Umang");
		rockstarMap.put("rjha", "Raman");
		rockstarMap.put("pkohli", "Prakash");
		rockstarMap.put("jtripathy", "Jitendra");
		rockstarMap.put("sgoel1", "Sonali");
		rockstarMap.put("vkumar1", "VikasKumar");
		rockstarMap.put("dgupta", "Deepak");
		rockstarMap.put("ibanerjee", "Ishika");
		rockstarMap.put("rkumar1", "Rishikesh");

		StringBuilder builder = new StringBuilder();
		rockStarJiraIds.forEach(rockStar->{
			if(builder.length()==0) {
				builder.append(rockstarMap.get(rockStar));
			} else {
				builder.append(", " + rockstarMap.get(rockStar));
			}
		});

		return builder.toString();
	}

	public String getOpenIncidentNotificationImageUrl(int openScIncidentCount) {
		String imageUrl = null;
		if (openScIncidentCount<40) {
			imageUrl = slackMessagingConstants.getOpenIncidentNotificationMemeList().get(0);
		} else {
			imageUrl = slackMessagingConstants.getOpenIncidentNotificationMemeList().get(1);
		}
		return imageUrl;
	}
	
	public String getScIssueNotificationImageUrl(int openScIncidentCount) {
		String imageUrl = null;
		if (openScIncidentCount<3) {
			imageUrl = slackMessagingConstants.getScIssueNotificationMemeList().get(0);
		} else {
			imageUrl = slackMessagingConstants.getScIssueNotificationMemeList().get(1);
		}
		return imageUrl;
	}

	public String getRockstarsImageUrl(int rockstars) {
		// Random randomNumber = new Random();
		// int memeListKey = randomNumber.nextInt(9 - 0 + 1) + 0;
		return configObj.getIncidentRockstarMemeList().get(rockstars-1);
	}

	public String buildSlackUrl(String channelName) {
		String slackUrl = null;
		if (StringUtils.isNotEmpty(channelName)) {
			switch (channelName) {
				case "operations":
					slackUrl = configObj.getSlackService() + configObj.getSlackApiKeyOperations();
					break;
				case "opsLeads":
					slackUrl = configObj.getSlackService() + configObj.getSlackApiKeyOpsLeads();
					break;
				case "jiraBots":
				default:
					slackUrl = configObj.getSlackService() + configObj.getSlackApiKeyJiraBots();
			}
		}
		return slackUrl;
	}
	
	public String postOnSlack(String requestBody, String channelName) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headerMap);
        String slashUrl = buildSlackUrl(channelName);
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        log.info("Response: " + response);
        return response;
    }

	public String composeWipBreachedMsg(HashMap<String, String> jiraMaxWipCountMap) {
		StringBuilder jiraMaxWipBreachedDetailMsgSb = new StringBuilder();
		if (Integer.parseInt(jiraMaxWipCountMap.get("InDev")) > configObj.getJiraInDevWipMaxLimit()) {
			jiraMaxWipBreachedDetailMsgSb.append("\n Max WIP limit breached for In Dev items and is: *" + jiraMaxWipCountMap.get("InDev") + slackMessagingConstants.getJiraMaxWipBreachedDetailMsg2() + configObj.getEmptySpace() + configObj.getJiraInDevWipMaxLimit());
		} else if (Integer.parseInt(jiraMaxWipCountMap.get("InTest")) > configObj.getJiraInDevWipMaxLimit()) {
			jiraMaxWipBreachedDetailMsgSb.append("\n Max WIP limit breached for In Test items and is: *" + jiraMaxWipCountMap.get("InTest") + slackMessagingConstants.getJiraMaxWipBreachedDetailMsg2() + configObj.getEmptySpace() + configObj.getJiraInTestWipMaxLimit());
		} else if (Integer.parseInt(jiraMaxWipCountMap.get("ToDo")) > configObj.getJiraInDevWipMaxLimit()) {
			jiraMaxWipBreachedDetailMsgSb.append("\n Max WIP limit breached for ToDo items and is: *" + jiraMaxWipCountMap.get("ToDo") + slackMessagingConstants.getJiraMaxWipBreachedDetailMsg2() + configObj.getEmptySpace() + configObj.getJiraToDoWipMaxLimit());
		}
		return jiraMaxWipBreachedDetailMsgSb.toString();
	}

}
