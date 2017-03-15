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

	private String emptySpace = " ";

	private String jiraCreds;

	private String jiraEndPoint;
	private String sampleJiraEndPoint;
	private String jiraControlChartApiEndPoint;
	private String jiraProjectApiEndPoint;
	private String jiraControlChartConfigApi;
	private String versions;

	private String prevDayIncidentRockstarJql;
	private String openSiteConfidenceIncidentsJql;
	private String openIncidentsJql;
	private String DorDodJql;
	private String totalInDevItemsJql;
	private String totalToDoItemsJql;
	private String totalInTestItemsJql;
	private String foundWorkJql;

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
	private Integer jiraOpenIncidentsShout;
	private Integer openScIncidentsShout;

	private List<String> bonusLyGiverEmails;
	private String bonusLyEndpointUrl;
	private String bonusLyAccessToken;
	private String bonusLyUsersEndpointUrl;
	private List<String> bonusLyGiverUserIds;

	/**
	 * @return the jiraControlChartConfigApi
	 */
	public String getJiraControlChartConfigApi() {
		return jiraControlChartConfigApi;
	}
	/**
	 * @param jiraControlChartConfigApi the jiraControlChartConfigApi to set
	 */
	public void setJiraControlChartConfigApi(String jiraControlChartConfigApi) {
		this.jiraControlChartConfigApi = jiraControlChartConfigApi;
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
	 * @return the jiraControlChartApiEndPoint
	 */
	public String getJiraControlChartApiEndPoint() {
		return jiraControlChartApiEndPoint;
	}
	/**
	 * @param jiraControlChartApiEndPoint the jiraControlChartApiEndPoint to set
	 */
	public void setJiraControlChartApiEndPoint(String jiraControlChartApiEndPoint) {
		this.jiraControlChartApiEndPoint = jiraControlChartApiEndPoint;
	}
	/**
	 * @return the jiraProjectApiEndPoint
	 */
	public String getJiraProjectApiEndPoint() {
		return jiraProjectApiEndPoint;
	}
	/**
	 * @param jiraProjectApiEndPoint the jiraProjectApiEndPoint to set
	 */
	public void setJiraProjectApiEndPoint(String jiraProjectApiEndPoint) {
		this.jiraProjectApiEndPoint = jiraProjectApiEndPoint;
	}
	/**
	 * @return the versions
	 */
	public String getVersions() {
		return versions;
	}
	/**
	 * @param versions the versions to set
	 */
	public void setVersions(String versions) {
		this.versions = versions;
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
	 * @return the foundWorkJql
	 */
	public String getFoundWorkJql() {
		return foundWorkJql;
	}
	/**
	 * @param foundWorkJql the foundWorkJql to set
	 */
	public void setFoundWorkJql(String foundWorkJql) {
		this.foundWorkJql = foundWorkJql;
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
	 * @return the jiraOpenIncidentsShout
	 */
	public Integer getJiraOpenIncidentsShout() {
		return jiraOpenIncidentsShout;
	}
	/**
	 * @param jiraOpenIncidentsShout the jiraOpenIncidentsShout to set
	 */
	public void setJiraOpenIncidentsShout(Integer jiraOpenIncidentsShout) {
		this.jiraOpenIncidentsShout = jiraOpenIncidentsShout;
	}
	/**
	 * @return the openScIncidentsShout
	 */
	public Integer getOpenScIncidentsShout() {
		return openScIncidentsShout;
	}
	/**
	 * @param openScIncidentsShout the openScIncidentsShout to set
	 */
	public void setOpenScIncidentsShout(Integer openScIncidentsShout) {
		this.openScIncidentsShout = openScIncidentsShout;
	}
	/**
	 * @return the bonusLyGiverEmails
	 */
	public List<String> getBonusLyGiverEmails() {
		return bonusLyGiverEmails;
	}
	/**
	 * @param bonusLyGiverEmails the bonusLyGiverEmails to set
	 */
	public void setBonusLyGiverEmails(List<String> bonusLyGiverEmails) {
		this.bonusLyGiverEmails = bonusLyGiverEmails;
	}
	/**
	 * @return the bonusLyEndpointUrl
	 */
	public String getBonusLyEndpointUrl() {
		return bonusLyEndpointUrl;
	}
	/**
	 * @param bonusLyEndpointUrl the bonusLyEndpointUrl to set
	 */
	public void setBonusLyEndpointUrl(String bonusLyEndpointUrl) {
		this.bonusLyEndpointUrl = bonusLyEndpointUrl;
	}
	/**
	 * @return the bonusLyAccessToken
	 */
	public String getBonusLyAccessToken() {
		return bonusLyAccessToken;
	}
	/**
	 * @param bonusLyAccessToken the bonusLyAccessToken to set
	 */
	public void setBonusLyAccessToken(String bonusLyAccessToken) {
		this.bonusLyAccessToken = bonusLyAccessToken;
	}
	/**
	 * @return the bonusLyUsersEndpointUrl
	 */
	public String getBonusLyUsersEndpointUrl() {
		return bonusLyUsersEndpointUrl;
	}
	/**
	 * @param bonusLyUsersEndpointUrl the bonusLyUsersEndpointUrl to set
	 */
	public void setBonusLyUsersEndpointUrl(String bonusLyUsersEndpointUrl) {
		this.bonusLyUsersEndpointUrl = bonusLyUsersEndpointUrl;
	}
	/**
	 * @return the bonusLyGiverUserIds
	 */
	public List<String> getBonusLyGiverUserIds() {
		return bonusLyGiverUserIds;
	}
	/**
	 * @param bonusLyGiverUserIds the bonusLyGiverUserIds to set
	 */
	public void setBonusLyGiverUserIds(List<String> bonusLyGiverUserIds) {
		this.bonusLyGiverUserIds = bonusLyGiverUserIds;
	}

}
