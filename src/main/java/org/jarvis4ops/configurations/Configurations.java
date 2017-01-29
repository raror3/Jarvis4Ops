/**
 * 
 */
package org.jarvis4ops.configurations;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author raror3
 *
 */
@Component
@ConfigurationProperties(prefix="org.ops4_0.constants")
public class Configurations {

	private String port;
	private String host;
	private String schedulerHostUrl;

	private String greatJobTitleMsg;
	private String incidentsResolvedMsg;
	private String cfd;
	private List<String> incidentRockstarMemeList;
	private String teamWork;
	private String emptySpace = " ";

	private String jiraCreds;

	private String jiraEndPoint;
	private String prevDayIncidentRockstarJql;
	private String sampleJiraEndPoint;
	private String openSiteConfidenceIncidentsJql;
	private String openIncidentsJql;
	private String DorDodJql;
	private String totalInDevItemsJql;
	private String totalToDoItemsJql;
	private String totalInTestItemsJql;

	private String slackService;
	private String slackApiKeyJiraBots;
	private String slackApiKeyOperations;
	private String slackApiKeyOpsLeads;
	private String jiraSupportKanbanBoardUrl;
	
	private String cloudinaryApiKey;
	private String cloudinaryApiSecKey;
	private String cloudName;
	
	private Integer jiraInDevWipMaxLimit;
	private Integer jiraInTestWipMaxLimit;
	private Integer jiraToDoWipMaxLimit;
	private Integer jiraIndividualAppreciationPrevDayIncCount;

	/**
	 * @return the schedulerHostUrl
	 */
	public String getSchedulerHostUrl() {
		return schedulerHostUrl;
	}

	/**
	 * @param schedulerHostUrl the schedulerHostUrl to set
	 */
	public void setSchedulerHostUrl(String schedulerHostUrl) {
		this.schedulerHostUrl = schedulerHostUrl;
	}

	/**
	 * @return the jiraSupportKanbanBoardUrl
	 */
	public String getJiraSupportKanbanBoardUrl() {
		return jiraSupportKanbanBoardUrl;
	}

	/**
	 * @param jiraSupportKanbanBoardUrl the jiraSupportKanbanBoardUrl to set
	 */
	public void setJiraSupportKanbanBoardUrl(String jiraSupportKanbanBoardUrl) {
		this.jiraSupportKanbanBoardUrl = jiraSupportKanbanBoardUrl;
	}

	/**
	 * @return the slackApiKeyJiraBots
	 */
	public String getSlackApiKeyJiraBots() {
		return slackApiKeyJiraBots;
	}

	/**
	 * @param slackApiKeyJiraBots the slackApiKeyJiraBots to set
	 */
	public void setSlackApiKeyJiraBots(String slackApiKeyJiraBots) {
		this.slackApiKeyJiraBots = slackApiKeyJiraBots;
	}

	/**
	 * @return the slackApiKeyOperations
	 */
	public String getSlackApiKeyOperations() {
		return slackApiKeyOperations;
	}

	/**
	 * @param slackApiKeyOperations the slackApiKeyOperations to set
	 */
	public void setSlackApiKeyOperations(String slackApiKeyOperations) {
		this.slackApiKeyOperations = slackApiKeyOperations;
	}

	/**
	 * @return the slackApiKeyOpsLeads
	 */
	public String getSlackApiKeyOpsLeads() {
		return slackApiKeyOpsLeads;
	}

	/**
	 * @param slackApiKeyOpsLeads the slackApiKeyOpsLeads to set
	 */
	public void setSlackApiKeyOpsLeads(String slackApiKeyOpsLeads) {
		this.slackApiKeyOpsLeads = slackApiKeyOpsLeads;
	}

	/**
	 * @return the jiraInDevWipMaxLimit
	 */
	public Integer getJiraInDevWipMaxLimit() {
		return jiraInDevWipMaxLimit;
	}

	/**
	 * @param jiraInDevWipMaxLimit the jiraInDevWipMaxLimit to set
	 */
	public void setJiraInDevWipMaxLimit(Integer jiraInDevWipMaxLimit) {
		this.jiraInDevWipMaxLimit = jiraInDevWipMaxLimit;
	}

	/**
	 * @return the jiraInTestWipMaxLimit
	 */
	public Integer getJiraInTestWipMaxLimit() {
		return jiraInTestWipMaxLimit;
	}

	/**
	 * @param jiraInTestWipMaxLimit the jiraInTestWipMaxLimit to set
	 */
	public void setJiraInTestWipMaxLimit(Integer jiraInTestWipMaxLimit) {
		this.jiraInTestWipMaxLimit = jiraInTestWipMaxLimit;
	}

	/**
	 * @return the jiraToDoWipMaxLimit
	 */
	public Integer getJiraToDoWipMaxLimit() {
		return jiraToDoWipMaxLimit;
	}

	/**
	 * @param jiraToDoWipMaxLimit the jiraToDoWipMaxLimit to set
	 */
	public void setJiraToDoWipMaxLimit(Integer jiraToDoWipMaxLimit) {
		this.jiraToDoWipMaxLimit = jiraToDoWipMaxLimit;
	}

	/**
	 * @return the jiraIndividualAppreciationPrevDayIncCount
	 */
	public Integer getJiraIndividualAppreciationPrevDayIncCount() {
		return jiraIndividualAppreciationPrevDayIncCount;
	}

	/**
	 * @param jiraIndividualAppreciationPrevDayIncCount the jiraIndividualAppreciationPrevDayIncCount to set
	 */
	public void setJiraIndividualAppreciationPrevDayIncCount(Integer jiraIndividualAppreciationPrevDayIncCount) {
		this.jiraIndividualAppreciationPrevDayIncCount = jiraIndividualAppreciationPrevDayIncCount;
	}

	/**
	 * @return the totalInDevItemsJql
	 */
	public String getTotalInDevItemsJql() {
		return totalInDevItemsJql;
	}

	/**
	 * @param totalInDevItemsJql the totalInDevItemsJql to set
	 */
	public void setTotalInDevItemsJql(String totalInDevItemsJql) {
		this.totalInDevItemsJql = totalInDevItemsJql;
	}

	/**
	 * @return the totalToDoItemsJql
	 */
	public String getTotalToDoItemsJql() {
		return totalToDoItemsJql;
	}

	/**
	 * @param totalToDoItemsJql the totalToDoItemsJql to set
	 */
	public void setTotalToDoItemsJql(String totalToDoItemsJql) {
		this.totalToDoItemsJql = totalToDoItemsJql;
	}

	/**
	 * @return the totalInTestItemsJql
	 */
	public String getTotalInTestItemsJql() {
		return totalInTestItemsJql;
	}

	/**
	 * @param totalInTestItemsJql the totalInTestItemsJql to set
	 */
	public void setTotalInTestItemsJql(String totalInTestItemsJql) {
		this.totalInTestItemsJql = totalInTestItemsJql;
	}

	/**
	 * @return the cloudName
	 */
	public String getCloudName() {
		return cloudName;
	}

	/**
	 * @param cloudName the cloudName to set
	 */
	public void setCloudName(String cloudName) {
		this.cloudName = cloudName;
	}

	/**
	 * @return the cloudinaryApiKey
	 */
	public String getCloudinaryApiKey() {
		return cloudinaryApiKey;
	}

	/**
	 * @param cloudinaryApiKey the cloudinaryApiKey to set
	 */
	public void setCloudinaryApiKey(String cloudinaryApiKey) {
		this.cloudinaryApiKey = cloudinaryApiKey;
	}

	/**
	 * @return the cloudinaryApiSecKey
	 */
	public String getCloudinaryApiSecKey() {
		return cloudinaryApiSecKey;
	}

	/**
	 * @param cloudinaryApiSecKey the cloudinaryApiSecKey to set
	 */
	public void setCloudinaryApiSecKey(String cloudinaryApiSecKey) {
		this.cloudinaryApiSecKey = cloudinaryApiSecKey;
	}

	/**
	 * @return the openIncidentsJql
	 */
	public String getOpenIncidentsJql() {
		return openIncidentsJql;
	}

	/**
	 * @param openIncidentsJql the openIncidentsJql to set
	 */
	public void setOpenIncidentsJql(String openIncidentsJql) {
		this.openIncidentsJql = openIncidentsJql;
	}

	/**
	 * @return the emptySpace
	 */
	public String getEmptySpace() {
		return emptySpace;
	}

	/**
	 * @param emptySpace the emptySpace to set
	 */
	public void setEmptySpace(String emptySpace) {
		this.emptySpace = emptySpace;
	}

	/**
	 * @return the openSiteConfidenceIncidentsJql
	 */
	public String getOpenSiteConfidenceIncidentsJql() {
		return openSiteConfidenceIncidentsJql;
	}

	/**
	 * @param openSiteConfidenceIncidentsJql the openSiteConfidenceIncidentsJql to set
	 */
	public void setOpenSiteConfidenceIncidentsJql(String openSiteConfidenceIncidentsJql) {
		this.openSiteConfidenceIncidentsJql = openSiteConfidenceIncidentsJql;
	}

	/**
	 * @return the teamWork
	 */
	public String getTeamWork() {
		return teamWork;
	}

	/**
	 * @param teamWork the teamWork to set
	 */
	public void setTeamWork(String teamWork) {
		this.teamWork = teamWork;
	}

	/**
	 * @return the slackService
	 */
	public String getSlackService() {
		return slackService;
	}

	/**
	 * @param slackService the slackService to set
	 */
	public void setSlackService(String slackService) {
		this.slackService = slackService;
	}

	/**
	 * @return the sampleJiraEndPoint
	 */
	public String getSampleJiraEndPoint() {
		return sampleJiraEndPoint;
	}

	/**
	 * @param sampleJiraEndPoint the sampleJiraEndPoint to set
	 */
	public void setSampleJiraEndPoint(String sampleJiraEndPoint) {
		this.sampleJiraEndPoint = sampleJiraEndPoint;
	}

	/**
	 * @return the jiraEndPoint
	 */
	public String getJiraEndPoint() {
		return jiraEndPoint;
	}

	/**
	 * @param jiraEndPoint the jiraEndPoint to set
	 */
	public void setJiraEndPoint(String jiraEndPoint) {
		this.jiraEndPoint = jiraEndPoint;
	}

	/**
	 * @return the prevDayIncidentRockstarJql
	 */
	public String getPrevDayIncidentRockstarJql() {
		return prevDayIncidentRockstarJql;
	}

	/**
	 * @param prevDayIncidentRockstarJql the prevDayIncidentRockstarJql to set
	 */
	public void setPrevDayIncidentRockstarJql(String prevDayIncidentRockstarJql) {
		this.prevDayIncidentRockstarJql = prevDayIncidentRockstarJql;
	}

	/**
	 * @return the jiraCreds
	 */
	public String getJiraCreds() {
		return jiraCreds;
	}

	/**
	 * @param jiraCreds the jiraCreds to set
	 */
	public void setJiraCreds(String jiraCreds) {
		this.jiraCreds = jiraCreds;
	}

	/**
	 * @return the incidentRockstarMemeList
	 */
	public List<String> getIncidentRockstarMemeList() {
		return incidentRockstarMemeList;
	}

	/**
	 * @param incidentRockstarMemeList the incidentRockstarMemeList to set
	 */
	public void setIncidentRockstarMemeList(List<String> incidentRockstarMemeList) {
		this.incidentRockstarMemeList = incidentRockstarMemeList;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the greatJobTitleMsg
	 */
	public String getGreatJobTitleMsg() {
		return greatJobTitleMsg;
	}

	/**
	 * @param greatJobTitleMsg the greatJobTitleMsg to set
	 */
	public void setGreatJobTitleMsg(String greatJobTitleMsg) {
		this.greatJobTitleMsg = greatJobTitleMsg;
	}

	/**
	 * @return the incidentsResolvedMsg
	 */
	public String getIncidentsResolvedMsg() {
		return incidentsResolvedMsg;
	}

	/**
	 * @param incidentsResolvedMsg the incidentsResolvedMsg to set
	 */
	public void setIncidentsResolvedMsg(String incidentsResolvedMsg) {
		this.incidentsResolvedMsg = incidentsResolvedMsg;
	}

	/**
	 * @return the cfd
	 */
	public String getCfd() {
		return cfd;
	}

	/**
	 * @param cfd the cfd to set
	 */
	public void setCfd(String cfd) {
		this.cfd = cfd;
	}

	/**
	 * @return the dorDodJql
	 */
	public String getDorDodJql() {
		return DorDodJql;
	}

	/**
	 * @param dorDodJql the dorDodJql to set
	 */
	public void setDorDodJql(String dorDodJql) {
		DorDodJql = dorDodJql;
	}
	
	

}
