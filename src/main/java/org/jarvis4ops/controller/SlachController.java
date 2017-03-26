/**
 * 
 */
package org.jarvis4ops.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.bean.DorParameters;
import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.bean.SlachAttachments;
import org.jarvis4ops.bean.SlachBean;
import org.jarvis4ops.bean.SlackFields;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
import org.jarvis4ops.configurations.SlackMessagingConstants;
import org.jarvis4ops.helper.DorDodIssuesHelper;
import org.jarvis4ops.helper.ImageManipulation;
import org.jarvis4ops.helper.SlackHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author raror3
 *
 */
@RestController
public class SlachController {
	private static final Logger log = LoggerFactory.getLogger(SlachController.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private SlackMessagingConstants slackMessagingConstants;
	
	@Autowired
	private DorDodIssuesHelper dorIssuesHelper;

	@Autowired
	private ImageManipulation imageManipulation;

	@Autowired
	private SlackHelper slackHelper;
	
	@Autowired
	private JiraConstants jiraConstants;

	@RequestMapping(value = "/postRockstarsOnSlack", method = { RequestMethod.POST })
	public String buildSlackMessageForRockstars(@RequestBody String rockstarsWithCountFixed) {
		
		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> map = new Gson().fromJson(rockstarsWithCountFixed, newType);
		
		String rockstars = slackHelper.getRockstarNames(map.keySet());
		SlachBean slachBean = new SlachBean();
		//slachBean.setFallback("@here: " + rockstars + " " + slackMessagingConstants.getGreatJobTitleMsg() + slackMessagingConstants.getIncidentsResolvedMsg().get(0) + configObj.getEmptySpace() + jiraConstants.getPrevDayJiraRockstarThreshold() + configObj.getEmptySpace() + slackMessagingConstants.getIncidentsResolvedMsg().get(1));
		
		if (null != map.keySet() && map.keySet().size() == 1) {
			slachBean.setFooter(slackMessagingConstants.getCfd());
		} else if (null != map.keySet() && map.keySet().size() > 1) {
			slachBean.setFooter(slackMessagingConstants.getCfd() + " " + slackMessagingConstants.getTeamWork());
		}

		slachBean.setImage_url(slackHelper.getRockstarsImageUrl(map.keySet().size()));
		String slackText = slackHelper.buildPrevDayRockstarMessage(rockstars);
		slachBean.setFallback(slackText);
		slachBean.setText(slackText);
		slachBean.setTitle(rockstars + " " + slackMessagingConstants.getGreatJobTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		//log.info("Json Value: ", gson.toJson(attachments));
		log.info("Json Value: " + gson.toJson(slachAttachments));
		return slackHelper.postOnSlack(gson.toJson(slachAttachments), "operations");
	}

	@RequestMapping(value = "/postScNotificationOnSlack", method = { RequestMethod.POST })
	public String buildSCNotificationMessageForSlack(@RequestParam(value = "openIncidents", required = false) Integer openIncidentCount) {
		
		Gson gson = new Gson();
		
		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getScIssueNotificationTitleMsg() + slackMessagingConstants.getScIssueNotificationDetailMsg());
		log.info("Open incident count: " + openIncidentCount);
		slachBean.setImage_url(slackHelper.getScIssueNotificationImageUrl(openIncidentCount));
		slachBean.setText(slackMessagingConstants.getScIssueNotificationDetailMsg());
		slachBean.setTitle(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getScIssueNotificationTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		log.info("Json Value: " + gson.toJson(slachAttachments));
		slackHelper.postOnSlack(gson.toJson(slachAttachments), "operations");
		return "200";
	}

	@RequestMapping(value = "/postOpenIncidentNotificationOnSlack", method = { RequestMethod.POST })
	public String buildOpenIncidentNotificationMessageForSlack(@RequestParam(value = "openIncidents", required = false) Integer openIncidentCount) {
		
		Gson gson = new Gson();

		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationTitleMsg() + slackMessagingConstants.getOpenIncidentNotificationDetailMsg1() + configObj.getEmptySpace() + openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationDetailMsg2());
		log.info("Open incident count: " + openIncidentCount);
		slachBean.setImage_url(slackHelper.getOpenIncidentNotificationImageUrl(openIncidentCount));
		slachBean.setText(slackMessagingConstants.getOpenIncidentNotificationDetailMsg1() + configObj.getEmptySpace() + openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationDetailMsg2());
		slachBean.setTitle(openIncidentCount + configObj.getEmptySpace() + slackMessagingConstants.getOpenIncidentNotificationTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		log.info("Json Value: " + gson.toJson(slachAttachments));
		slackHelper.postOnSlack(gson.toJson(slachAttachments), "operations");
		return "200";
	}

	@RequestMapping(value = "/postDorStatus", method = { RequestMethod.POST })
	public String buildSlackMessageForDor(@RequestBody String dorIssuesList) throws IOException {

		Gson gson = new Gson();
		SlachBean slachBean = new SlachBean();
		Type mapType = new TypeToken<HashMap<String, DorParameters>>(){}.getType();
		HashMap<String,DorParameters> dorIssuesMap = new Gson().fromJson(dorIssuesList, mapType);
		String[] sprintNam=dorIssuesList.split("sprintName");
		String[] sprintName=sprintNam[1].split("}");
		sprintName[0] = sprintName[0].replaceAll("^\"|\":$", "");
		slachBean.setFallback(dorIssuesHelper.countOfissuesDorImcomplete(dorIssuesMap) + slackMessagingConstants.getDorStatusTitleMsg());
		slachBean.setTitle(dorIssuesHelper.countOfissuesDorImcomplete(dorIssuesMap) + configObj.getEmptySpace() + slackMessagingConstants.getDorStatusTitleMsg() + sprintName[0] +" "+ slackMessagingConstants.getDorStatusTitleMsg2());
		//slachBean.setText(slackMessagingConstants.getDorPendingStoriesDetailMsg() + configObj.getEmptySpace() + dorIssuesHelper.getIncompleteDorStoryList(dorIssuesMap));
		SlackFields slackField = new SlackFields();
		slackField.setTitle(slackMessagingConstants.getDorPendingStoriesDetailMsg());
		slackField.setValue(dorIssuesHelper.getIncompleteDorStoryList(dorIssuesMap));
		List<SlackFields> fields = new ArrayList<SlackFields>(1);
		fields.add(slackField);
		slachBean.setFields(fields);
		slachBean.setColor("#F35A00");

		Cloudinary cloudinary = new Cloudinary();
		Map<String, String> options = new HashMap<String, String>(2);
		options.put("api_key", configObj.getCloudinaryApiKey());
		options.put("api_secret", configObj.getCloudinaryApiSecKey());
		options.put("cloud_name", configObj.getCloudName());
		Map<String, String> result = cloudinary.uploader().upload(imageManipulation.manipulateImage(dorIssuesHelper.populateDataforDorImage(dorIssuesMap), dorIssuesMap.size()), options);

		slachBean.setImage_url(result.get("url"));

		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);

	//	log.info("Json Value: " + gson.toJson(slachAttachments));
		slackHelper.postOnSlack(gson.toJson(slachAttachments), "jiraBots");

		return "200";
	}

	@RequestMapping(value = "/postMaxWipBreachedSlack", method = { RequestMethod.POST })
	public String postMaxWipBreachedSlack(@RequestBody String jiraMaxWipCountRequest) {
		
		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> jiraMaxWipCountMap = new Gson().fromJson(jiraMaxWipCountRequest, newType);
		
		SlachBean slachBean = new SlachBean();

		slachBean.setImage_url(slackMessagingConstants.getJiraMaxWipBreachedImageUrl());
		slachBean.setText(slackHelper.composeWipBreachedMsg(jiraMaxWipCountMap));
		slachBean.setTitle(slackMessagingConstants.getJiraMaxWipBreachedTitleMsg());
		slachBean.setTitle_link(configObj.getJiraSupportKanbanBoardUrl());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		log.info("Json Value: " + gson.toJson(slachAttachments));
		slackHelper.postOnSlack(gson.toJson(slachAttachments), "jiraBots");
		return "200";
	}
	
	@RequestMapping(value = "/postFoundWorkSlack", method = { RequestMethod.POST })
	public String postFoundWorkSlack(@RequestBody String jirafoundWorkCountRequest) {
		
		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> jiraFoundWorkCountMap = new Gson().fromJson(jirafoundWorkCountRequest, newType);
		
		SlachBean slachBean = new SlachBean();
		slachBean.setTitle(slackMessagingConstants.getJiraFoundWorkTitleMsg() + slackHelper.composeFoundWorkMsg(jiraFoundWorkCountMap));
		slachBean.setImage_url(slackMessagingConstants.getJiraFoundWorkImageUrl());
		
		final String jiraIds = jiraFoundWorkCountMap.get("storyId").toString();
		final String[] jiraList = jiraIds.split(",");
		List<SlackFields> fields = new ArrayList<SlackFields>(jiraList.length);
		for (int i=0;i<jiraList.length;i++) {
			SlackFields slackField = new SlackFields();
			final String[] jiraDetail = jiraList[i].split("::::");
			slackField.setTitle(jiraDetail[0]);
			slackField.setValue(jiraDetail[1]);
			fields.add(slackField);
		}
		slachBean.setFields(fields);
		slachBean.setColor("#7CD197");

		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		final String slackChannel = slackHelper.fetchSlackChannel(jiraFoundWorkCountMap);
		log.info("Json Value: " + gson.toJson(slachAttachments));
		slackHelper.postOnSlack(gson.toJson(slachAttachments), slackChannel);
		return "200";
	}

	@RequestMapping(value="/postAdoptedWorkOnSlack/{project}" , method = { RequestMethod.POST })
	public String sendAdoptedWorkNotification(@PathVariable(value = "project", required = true) String project,
			@RequestBody String jiraAdoptedWorkRequest) {
		Gson gson = new Gson();
		ArrayIssueDetails jiraAdoptedWorkIssues = new Gson().fromJson(jiraAdoptedWorkRequest, ArrayIssueDetails.class);

		SlachBean slachBean = new SlachBean();
		slachBean.setTitle(String.valueOf(jiraAdoptedWorkIssues.getTotal()).concat(" ")
				.concat(slackMessagingConstants.getJiraAdoptedWorkTitleMsg()));
		slachBean.setImage_url(slackMessagingConstants.getJiraAdoptedWorkImageUrl());

		final List<IssueDetails> issuesDetails = jiraAdoptedWorkIssues.getIssues();
		List<SlackFields> fields = new ArrayList<SlackFields>(issuesDetails.size());
		for (int i = 0; i < issuesDetails.size(); i++) {
			SlackFields slackField = new SlackFields();
			slackField.setTitle("Jira Id : " + issuesDetails.get(i).getKey());
			slackField.setValue("Summary : ".concat(issuesDetails.get(i).getFields().getSummary()).concat("  ").concat("Assignee : " + issuesDetails.get(i).getFields().getAssignee().getName()));
			fields.add(slackField);
		}
		slachBean.setFields(fields);
		slachBean.setColor("#7CD197");
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		log.info("Json Value: " + gson.toJson(slachAttachments));
		slackHelper.postOnSlack(gson.toJson(slachAttachments), "jiraBots");
		return "200";
	}

}
