package org.jarvis4ops.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraVersionBean {
	private String self;
	private int id;
	private String description;
	private String name;
	private boolean archived;
	private boolean released;
	private Date startDate;
	private Date releaseDate;
	private boolean overdue;
	private String userStartDate;
	private String userReleaseDate;
	private String projectId;
	private Long averageLeadTime;
	private Long averageCycleTime;
	private int controlChartIssueCount;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the controlChartIssueCount
	 */
	public int getControlChartIssueCount() {
		return controlChartIssueCount;
	}
	/**
	 * @param controlChartIssueCount the controlChartIssueCount to set
	 */
	public void setControlChartIssueCount(int controlChartIssueCount) {
		this.controlChartIssueCount = controlChartIssueCount;
	}
	/**
	 * @return the averageLeadTime
	 */
	public Long getAverageLeadTime() {
		return averageLeadTime;
	}
	/**
	 * @param averageLeadTime the averageLeadTime to set
	 */
	public void setAverageLeadTime(Long averageLeadTime) {
		this.averageLeadTime = averageLeadTime;
	}
	/**
	 * @return the averageCycleTime
	 */
	public Long getAverageCycleTime() {
		return averageCycleTime;
	}
	/**
	 * @param averageCycleTime the averageCycleTime to set
	 */
	public void setAverageCycleTime(Long averageCycleTime) {
		this.averageCycleTime = averageCycleTime;
	}
	/**
	 * @return the self
	 */
	public String getSelf() {
		return self;
	}
	/**
	 * @param self the self to set
	 */
	public void setSelf(String self) {
		this.self = self;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the archived
	 */
	public boolean isArchived() {
		return archived;
	}
	/**
	 * @param archived the archived to set
	 */
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	/**
	 * @return the released
	 */
	public boolean isReleased() {
		return released;
	}
	/**
	 * @param released the released to set
	 */
	public void setReleased(boolean released) {
		this.released = released;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	/**
	 * @param releaseDate the releaseDate to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * @return the overdue
	 */
	public boolean isOverdue() {
		return overdue;
	}
	/**
	 * @param overdue the overdue to set
	 */
	public void setOverdue(boolean overdue) {
		this.overdue = overdue;
	}
	/**
	 * @return the userStartDate
	 */
	public String getUserStartDate() {
		return userStartDate;
	}
	/**
	 * @param userStartDate the userStartDate to set
	 */
	public void setUserStartDate(String userStartDate) {
		this.userStartDate = userStartDate;
	}
	/**
	 * @return the userReleaseDate
	 */
	public String getUserReleaseDate() {
		return userReleaseDate;
	}
	/**
	 * @param userReleaseDate the userReleaseDate to set
	 */
	public void setUserReleaseDate(String userReleaseDate) {
		this.userReleaseDate = userReleaseDate;
	}
	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
