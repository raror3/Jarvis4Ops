package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jarvis4ops.bean.UserProfileBean;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
import org.jarvis4ops.configurations.SlackMessagingConstants;
import org.jarvis4ops.controller.JiraController;
import org.jarvis4ops.mongoRepositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class SlackHelper {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private Environment environment;
	
	@Autowired
	private SlackMessagingConstants slackMessagingConstants;
	
	@Autowired
	private JiraConstants jiraConstants;
	
	@Autowired
	private UserProfileRepository userProfileRepository;

	public String getRockstarNames(Set<String> rockStarJiraIds) {
		
		final StringBuilder rockstarNamesSb = new StringBuilder();
		for (String rockstarId : rockStarJiraIds)
		{
		    UserProfileBean userProfile = userProfileRepository.findByJiraUsername(rockstarId);
			if (userProfile != null) {
				if (rockstarNamesSb.length() == 0) {
					rockstarNamesSb.append(userProfile.getFirstName());
					rockstarNamesSb.append(configObj.getEmptySpace());
					rockstarNamesSb.append(userProfile.getLastName());
				} else {
					rockstarNamesSb.append(", " + userProfile.getFirstName());
					rockstarNamesSb.append(configObj.getEmptySpace());
					rockstarNamesSb.append(userProfile.getLastName());
				}
				
			}
		}

		return rockstarNamesSb.toString();
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
		return slackMessagingConstants.getIncidentRockstarMemeList().get(rockstars-1);
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
	
	public String composeFoundWorkMsg(HashMap<String, String> jiraFoundWorkCountMap) {
		StringBuilder jiraFoundWorkDetailMsgSb = new StringBuilder();
		int count = Integer.parseInt(jiraFoundWorkCountMap.get("FoundWorkCount"));
		jiraFoundWorkDetailMsgSb.append("\n " + "Total found work created today - " + count);
		return jiraFoundWorkDetailMsgSb.toString();
	}

	public String buildPrevDayRockstarMessage(String rockstars) {
		StringBuilder slackMsgSb = new StringBuilder("@here ");
		slackMsgSb.append(rockstars);
		slackMsgSb.append(configObj.getEmptySpace());
		slackMsgSb.append(slackMessagingConstants.getGreatJobTitleMsg());
		slackMsgSb.append(slackMessagingConstants.getIncidentsResolvedMsg().get(0));
		slackMsgSb.append(configObj.getEmptySpace());
		slackMsgSb.append(jiraConstants.getPrevDayJiraRockstarThreshold() + "+");
		slackMsgSb.append(configObj.getEmptySpace());
		slackMsgSb.append(slackMessagingConstants.getIncidentsResolvedMsg().get(1));
		return slackMsgSb.toString();
	}

}
