package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraControlChartResponseBean {
	
	private List<JiraControlChartIssueBean> issues;
	private List<IdNameBean> columns;
	private String currentTime;
	private JiraWorkRateDataBean workRateData;

	/**
	 * @return the issues
	 */
	public List<JiraControlChartIssueBean> getIssues() {
		return issues;
	}
	/**
	 * @param issues the issues to set
	 */
	public void setIssues(List<JiraControlChartIssueBean> issues) {
		this.issues = issues;
	}
	/**
	 * @return the columns
	 */
	public List<IdNameBean> getColumns() {
		return columns;
	}
	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<IdNameBean> columns) {
		this.columns = columns;
	}
	/**
	 * @return the currentTime
	 */
	public String getCurrentTime() {
		return currentTime;
	}
	/**
	 * @param currentTime the currentTime to set
	 */
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	/**
	 * @return the workRateData
	 */
	public JiraWorkRateDataBean getWorkRateData() {
		return workRateData;
	}
	/**
	 * @param workRateData the workRateData to set
	 */
	public void setWorkRateData(JiraWorkRateDataBean workRateData) {
		this.workRateData = workRateData;
	}

}
