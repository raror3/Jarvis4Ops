/**
 * 
 */
package org.jarvis4ops.configurations;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author raror3
 *
 */
@Component
@PropertySource("classpath:slackMessaging.properties")
@ConfigurationProperties(prefix="org.ops4_0.slackMessagingConstants")
public class SlackMessagingConstants {

	private String scIssueNotificationTitleMsg;
	private String scIssueNotificationDetailMsg;
	private List<String> scIssueNotificationMemeList;
	
	private List<String> openIncidentNotificationMemeList;
	private String openIncidentNotificationTitleMsg;
	private String openIncidentNotificationDetailMsg1;
	private String openIncidentNotificationDetailMsg2;
	
	private String dorStatusTitleMsg;
	private String dorPendingStoriesDetailMsg;

	private String jiraMaxWipBreachedImageUrl;
	private String jiraMaxWipBreachedTitleMsg;
	private String jiraMaxWipBreachedDetailMsg2;
	
	private String jiraFoundWorkTitleMsg;

	/**
	 * @return the value of -jiraFoundWorkTitleMsg
	 */
	public String getJiraFoundWorkTitleMsg() {
		return jiraFoundWorkTitleMsg;
	}
	/**
	 * @param jiraFoundWorkTitleMsg
	 */
	public void setJiraFoundWorkTitleMsg(String jiraFoundWorkTitleMsg) {
		this.jiraFoundWorkTitleMsg = jiraFoundWorkTitleMsg;
	}
	/**
	 * @return the jiraMaxWipBreachedTitleMsg
	 */
	public String getJiraMaxWipBreachedTitleMsg() {
		return jiraMaxWipBreachedTitleMsg;
	}
	/**
	 * @param jiraMaxWipBreachedTitleMsg the jiraMaxWipBreachedTitleMsg to set
	 */
	public void setJiraMaxWipBreachedTitleMsg(String jiraMaxWipBreachedTitleMsg) {
		this.jiraMaxWipBreachedTitleMsg = jiraMaxWipBreachedTitleMsg;
	}
	/**
	 * @return the jiraMaxWipBreachedDetailMsg2
	 */
	public String getJiraMaxWipBreachedDetailMsg2() {
		return jiraMaxWipBreachedDetailMsg2;
	}
	/**
	 * @param jiraMaxWipBreachedDetailMsg2 the jiraMaxWipBreachedDetailMsg2 to set
	 */
	public void setJiraMaxWipBreachedDetailMsg2(String jiraMaxWipBreachedDetailMsg2) {
		this.jiraMaxWipBreachedDetailMsg2 = jiraMaxWipBreachedDetailMsg2;
	}
	/**
	 * @return the jiraMaxWipBreachedImageUrl
	 */
	public String getJiraMaxWipBreachedImageUrl() {
		return jiraMaxWipBreachedImageUrl;
	}
	/**
	 * @param jiraMaxWipBreachedImageUrl the jiraMaxWipBreachedImageUrl to set
	 */
	public void setJiraMaxWipBreachedImageUrl(String jiraMaxWipBreachedImageUrl) {
		this.jiraMaxWipBreachedImageUrl = jiraMaxWipBreachedImageUrl;
	}
	/**
	 * @return the dorStatusTitleMsg
	 */
	public String getDorStatusTitleMsg() {
		return dorStatusTitleMsg;
	}
	/**
	 * @param dorStatusTitleMsg the dorStatusTitleMsg to set
	 */
	public void setDorStatusTitleMsg(String dorStatusTitleMsg) {
		this.dorStatusTitleMsg = dorStatusTitleMsg;
	}
	/**
	 * @return the dorPendingStoriesDetailMsg
	 */
	public String getDorPendingStoriesDetailMsg() {
		return dorPendingStoriesDetailMsg;
	}
	/**
	 * @param dorPendingStoriesDetailMsg the dorPendingStoriesDetailMsg to set
	 */
	public void setDorPendingStoriesDetailMsg(String dorPendingStoriesDetailMsg) {
		this.dorPendingStoriesDetailMsg = dorPendingStoriesDetailMsg;
	}
	/**
	 * @return the openIncidentNotificationDetailMsg1
	 */
	public String getOpenIncidentNotificationDetailMsg1() {
		return openIncidentNotificationDetailMsg1;
	}
	/**
	 * @param openIncidentNotificationDetailMsg1 the openIncidentNotificationDetailMsg1 to set
	 */
	public void setOpenIncidentNotificationDetailMsg1(String openIncidentNotificationDetailMsg1) {
		this.openIncidentNotificationDetailMsg1 = openIncidentNotificationDetailMsg1;
	}
	/**
	 * @return the openIncidentNotificationDetailMsg2
	 */
	public String getOpenIncidentNotificationDetailMsg2() {
		return openIncidentNotificationDetailMsg2;
	}
	/**
	 * @param openIncidentNotificationDetailMsg2 the openIncidentNotificationDetailMsg2 to set
	 */
	public void setOpenIncidentNotificationDetailMsg2(String openIncidentNotificationDetailMsg2) {
		this.openIncidentNotificationDetailMsg2 = openIncidentNotificationDetailMsg2;
	}
	/**
	 * @return the scIssueNotificationTitleMsg
	 */
	public String getScIssueNotificationTitleMsg() {
		return scIssueNotificationTitleMsg;
	}
	/**
	 * @param scIssueNotificationTitleMsg the scIssueNotificationTitleMsg to set
	 */
	public void setScIssueNotificationTitleMsg(String scIssueNotificationTitleMsg) {
		this.scIssueNotificationTitleMsg = scIssueNotificationTitleMsg;
	}
	/**
	 * @return the scIssueNotificationDetailMsg
	 */
	public String getScIssueNotificationDetailMsg() {
		return scIssueNotificationDetailMsg;
	}
	/**
	 * @param scIssueNotificationDetailMsg the scIssueNotificationDetailMsg to set
	 */
	public void setScIssueNotificationDetailMsg(String scIssueNotificationDetailMsg) {
		this.scIssueNotificationDetailMsg = scIssueNotificationDetailMsg;
	}
	/**
	 * @return the scIssueNotificationMemeList
	 */
	public List<String> getScIssueNotificationMemeList() {
		return scIssueNotificationMemeList;
	}
	/**
	 * @param scIssueNotificationMemeList the scIssueNotificationMemeList to set
	 */
	public void setScIssueNotificationMemeList(List<String> scIssueNotificationMemeList) {
		this.scIssueNotificationMemeList = scIssueNotificationMemeList;
	}
	/**
	 * @return the openIncidentNotificationMemeList
	 */
	public List<String> getOpenIncidentNotificationMemeList() {
		return openIncidentNotificationMemeList;
	}
	/**
	 * @param openIncidentNotificationMemeList the openIncidentNotificationMemeList to set
	 */
	public void setOpenIncidentNotificationMemeList(List<String> openIncidentNotificationMemeList) {
		this.openIncidentNotificationMemeList = openIncidentNotificationMemeList;
	}
	/**
	 * @return the openIncidentNotificationTitleMsg
	 */
	public String getOpenIncidentNotificationTitleMsg() {
		return openIncidentNotificationTitleMsg;
	}
	/**
	 * @param openIncidentNotificationTitleMsg the openIncidentNotificationTitleMsg to set
	 */
	public void setOpenIncidentNotificationTitleMsg(String openIncidentNotificationTitleMsg) {
		this.openIncidentNotificationTitleMsg = openIncidentNotificationTitleMsg;
	}

}