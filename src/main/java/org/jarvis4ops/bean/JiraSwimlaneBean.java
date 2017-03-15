package org.jarvis4ops.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraSwimlaneBean {
	private Integer swimlaneId;
	private String swimlaneDesc;
	private Long averageLeadTime;
	private Long averageCycleTime;
	private int controlChartIssueCount;

	/**
	 * @return the swimLaneId
	 */
	public Integer getSwimLaneId() {
		return swimlaneId;
	}
	/**
	 * @param swimLaneId the swimLaneId to set
	 */
	public void setSwimLaneId(Integer swimLaneId) {
		this.swimlaneId = swimLaneId;
	}
	/**
	 * @return the swimLaneDesc
	 */
	public String getSwimLaneDesc() {
		return swimlaneDesc;
	}
	/**
	 * @param swimLaneDesc the swimLaneDesc to set
	 */
	public void setSwimLaneDesc(String swimLaneDesc) {
		this.swimlaneDesc = swimLaneDesc;
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

}
