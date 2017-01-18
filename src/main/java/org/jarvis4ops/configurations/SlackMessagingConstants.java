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

}