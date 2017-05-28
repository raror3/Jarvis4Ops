package org.jarvis4ops.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "jiraControlChart")
public class JiraVersionBean implements Comparable<JiraVersionBean> {
	@Id ObjectId databaseId;
	private int id;
	private String self;
	private String description;
	private String name;
	private boolean archived;
	private boolean released;
	private Date startDate;
	private Date releaseDate;
	private boolean overdue;
	private String userStartDate;
	private String userReleaseDate;
	@Field("projectId") private String projectId;
	private List<JiraSwimlaneBean> swimlane = new ArrayList<JiraSwimlaneBean>(1);

	public ObjectId getDatabaseId() {
		return databaseId;
	}
	public void setDatabaseId(ObjectId databaseId) {
		this.databaseId = databaseId;
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
	/**
	 * @return the swimlane
	 */
	public List<JiraSwimlaneBean> getSwimlane() {
		return swimlane;
	}
	/**
	 * @param swimlane the swimlane to set
	 */
	public void setSwimlane(List<JiraSwimlaneBean> swimlane) {
		this.swimlane = swimlane;
	}

	@Override
	public int compareTo(JiraVersionBean versionBean2) {
		if (null != getReleaseDate() && null != versionBean2.getReleaseDate())
		{
			return getReleaseDate().compareTo(versionBean2.getReleaseDate());
		} else {
			return -1;
		}
	}
}
