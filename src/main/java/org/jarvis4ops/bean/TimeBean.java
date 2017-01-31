package org.jarvis4ops.bean;

public class TimeBean {
	
	String issueId;
	String assignee;
	String slackId;
	int timeDiff;
	/**
	 * @return the issueId
	 */
	public String getIssueId() {
		return issueId;
	}
	/**
	 * @param issueId the issueId to set
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}
	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	/**
	 * @return the timeDiff
	 */
	public int getTimeDiff() {
		return timeDiff;
	}
	/**
	 * @param timeDiff the timeDiff to set
	 */
	public void setTimeDiff(int timeDiff) {
		this.timeDiff = timeDiff;
	}
	/**
	 * @return the slackId
	 */
	public String getSlackId() {
		return slackId;
	}
	/**
	 * @param slackId the slackId to set
	 */
	public void setSlackId(String slackId) {
		this.slackId = slackId;
	}
	
}
