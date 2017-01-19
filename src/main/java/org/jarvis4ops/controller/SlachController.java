/**
 * 
 */
package org.jarvis4ops.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.jarvis4ops.bean.TestAttachments;
import org.jarvis4ops.bean.TestBean;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.SlackMessagingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author raror3
 *
 */
@RestController
public class SlachController {
	//private static final Logger log = LoggerFactory.getLogger(SlachController.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private SlackMessagingConstants slackMessagingConstants;

	@RequestMapping(value = "/postRockstarsOnSlack", method = { RequestMethod.POST })
	public String buildSlackMessageForRockstars(@RequestBody String rockstarsWithCountFixed) {
		
		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> map = new Gson().fromJson(rockstarsWithCountFixed, newType);
		
		String rockstars = getRockstarNames(map.keySet());
		SlachBean slachBean = new SlachBean();
		slachBean.setFallback("@here: " + rockstars + " " + configObj.getGreatJobTitleMsg() + configObj.getIncidentsResolvedMsg());
		
		if (null != map.keySet() && map.keySet().size() == 1) {
			slachBean.setFooter(configObj.getCfd());
		} else if (null != map.keySet() && map.keySet().size() > 1) {
			slachBean.setFooter(configObj.getCfd() + " " + configObj.getTeamWork());
		}

		slachBean.setImage_url(getRockstarsImageUrl(map.keySet().size()));
		slachBean.setText(configObj.getIncidentsResolvedMsg());
		slachBean.setTitle(rockstars + " " + configObj.getGreatJobTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		//log.info("Json Value: ", gson.toJson(attachments));
		System.out.println("Json Value: " + gson.toJson(slachAttachments));
		postOnSlack(gson.toJson(slachAttachments));
		//postOnSlack(attachments);
		return "200";
	}

	private String getRockstarNames(Set<String> rockStarJiraIds) {
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

	@RequestMapping(value = "/postScNotificationOnSlack", method = { RequestMethod.POST })
	public String buildSCNotificationMessageForSlack(@RequestParam(value = "openIncidents", required = false) Integer openIncidentCount) {
		
		Gson gson = new Gson();
		
		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getScIssueNotificationTitleMsg() + slackMessagingConstants.getScIssueNotificationDetailMsg());
		System.out.println("Open incident count: " + openIncidentCount);
		slachBean.setImage_url(getScIssueNotificationImageUrl(openIncidentCount));
		slachBean.setText(slackMessagingConstants.getScIssueNotificationDetailMsg());
		slachBean.setTitle(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getScIssueNotificationTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		System.out.println("Json Value: " + gson.toJson(slachAttachments));
		postOnSlack(gson.toJson(slachAttachments));
		return "200";
	}

	@RequestMapping(value = "/postOpenIncidentNotificationOnSlack", method = { RequestMethod.POST })
	public String buildOpenIncidentNotificationMessageForSlack(@RequestParam(value = "openIncidents", required = false) Integer openIncidentCount) {
		
		Gson gson = new Gson();

		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationTitleMsg() + slackMessagingConstants.getOpenIncidentNotificationDetailMsg1() + configObj.getEmptySpace() + openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationDetailMsg2());
		System.out.println("Open incident count: " + openIncidentCount);
		slachBean.setImage_url(getOpenIncidentNotificationImageUrl(openIncidentCount));
		slachBean.setText(slackMessagingConstants.getOpenIncidentNotificationDetailMsg1() + configObj.getEmptySpace() + openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationDetailMsg2());
		slachBean.setTitle(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		System.out.println("Json Value: " + gson.toJson(slachAttachments));
		postOnSlack(gson.toJson(slachAttachments));
		return "200";
	}

	private String getOpenIncidentNotificationImageUrl(int openScIncidentCount) {
		String imageUrl = null;
		if (openScIncidentCount<40) {
			imageUrl = slackMessagingConstants.getOpenIncidentNotificationMemeList().get(0);
		} else {
			imageUrl = slackMessagingConstants.getOpenIncidentNotificationMemeList().get(1);
		}
		return imageUrl;
	}
	
	private String getScIssueNotificationImageUrl(int openScIncidentCount) {
		String imageUrl = null;
		if (openScIncidentCount<3) {
			imageUrl = slackMessagingConstants.getScIssueNotificationMemeList().get(0);
		} else {
			imageUrl = slackMessagingConstants.getScIssueNotificationMemeList().get(1);
		}
		return imageUrl;
	}

	private String getRockstarsImageUrl(int rockstars) {
		// Random randomNumber = new Random();
		// int memeListKey = randomNumber.nextInt(9 - 0 + 1) + 0;
		return configObj.getIncidentRockstarMemeList().get(rockstars-1);
	}

	
	@RequestMapping("/directPostOnSlack")
	public String buildAdhocSlachMessage(@RequestParam(value="name", defaultValue="team") String name) {
		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(name + configObj.getGreatJobTitleMsg() + configObj.getIncidentsResolvedMsg());
		slachBean.setFooter(configObj.getCfd());

		Random randomNumber = new Random();
		int memeListKey = randomNumber.nextInt(0 - 0) + 0;
		slachBean.setImage_url(configObj.getIncidentRockstarMemeList().get(memeListKey));
		slachBean.setText(configObj.getIncidentsResolvedMsg());
		slachBean.setTitle(configObj.getGreatJobTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		Gson gson = new Gson();
		//log.info("Json Value: ", gson.toJson(attachments));
		System.out.println("Json Value: " + gson.toJson(slachAttachments));
		postOnSlack(gson.toJson(slachAttachments));
		//postOnSlack(attachments);
		return "200";
	}

	public void postOnSlack(String requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headerMap);
        String slashUrl = configObj.getSlackService()+configObj.getSlackApiKey();
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        //log.info("Response: ", response);
        System.out.println("Response: " + response);
    }
	
	
	@RequestMapping(value = "/postDorStatus", method = { RequestMethod.POST })
	public String buildSlackMessageForDor(@RequestBody String dorIssuesList) {
		
		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> map = new Gson().fromJson(dorIssuesList, newType);
		
		String title = "Key, Tech Review Complete, Acceptance Criteria Defined, UX Design, 3rd Party Dependency, NFR Requirement considered, Overall Status";
		
		TestBean testBean = new TestBean();
		
		testBean.setTitle(title);
		
		TestAttachments testAttachments = new TestAttachments();
		List<TestBean> testBeanList = new ArrayList<TestBean>(1);
		testBeanList.add(testBean);
		testAttachments.setAttachments(testBeanList);
		
		//log.info("Json Value: ", gson.toJson(attachments));
		System.out.println("Json Value: " + gson.toJson(testAttachments));
		postOnSlack(gson.toJson(testAttachments));
		//postOnSlack(attachments);
		return "200";
	}
}
