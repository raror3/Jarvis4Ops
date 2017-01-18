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