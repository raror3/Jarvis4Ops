package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraControlChartIssueBean {
	private List<Long> totalTime;
	private List<Long> workingTime;
	private List<Long> leaveTimes;
	private String key;
	private String summary;
	private int swimlaneId;

	/**
	 * @return the totalTime
	 */
	public List<Long> getTotalTime() {
		return totalTime;
	}
	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(List<Long> totalTime) {
		this.totalTime = totalTime;
	}
	/**
	 * @return the workingTime
	 */
	public List<Long> getWorkingTime() {
		return workingTime;
	}
	/**
	 * @param workingTime the workingTime to set
	 */
	public void setWorkingTime(List<Long> workingTime) {
		this.workingTime = workingTime;
	}
	/**
	 * @return the leaveTimes
	 */
	public List<Long> getLeaveTimes() {
		return leaveTimes;
	}
	/**
	 * @param leaveTimes the leaveTimes to set
	 */
	public void setLeaveTimes(List<Long> leaveTimes) {
		this.leaveTimes = leaveTimes;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	/**
	 * @return the swimlaneId
	 */
	public int getSwimlaneId() {
		return swimlaneId;
	}
	/**
	 * @param swimlaneId the swimlaneId to set
	 */
	public void setSwimlaneId(int swimlaneId) {
		this.swimlaneId = swimlaneId;
	}
}
