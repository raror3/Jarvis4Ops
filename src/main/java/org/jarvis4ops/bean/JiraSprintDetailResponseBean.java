package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraSprintDetailResponseBean {
	
	private int maxResults;
	private int startAt;
	private int total;
	private boolean isLast;
	private List<JiraSprintValuesBean> values;

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}
	/**
	 * @param maxResults the maxResults to set
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
	/**
	 * @return the startAt
	 */
	public int getStartAt() {
		return startAt;
	}
	/**
	 * @param startAt the startAt to set
	 */
	public void setStartAt(int startAt) {
		this.startAt = startAt;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the isLast
	 */
	public boolean isLast() {
		return isLast;
	}
	/**
	 * @param isLast the isLast to set
	 */
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	/**
	 * @return the values
	 */
	public List<JiraSprintValuesBean> getValues() {
		return values;
	}
	/**
	 * @param values the values to set
	 */
	public void setValues(List<JiraSprintValuesBean> values) {
		this.values = values;
	}
}
