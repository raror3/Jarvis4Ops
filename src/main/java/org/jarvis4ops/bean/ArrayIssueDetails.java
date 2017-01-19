package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayIssueDetails {
	
	private int total;
	private List<IssueDetails> issues;
	
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

	public ArrayIssueDetails() {
		super();
	}

	public  List<IssueDetails> getIssues() {
		return issues;
	}

	public void setIssues( List<IssueDetails> issues) {
		this.issues = issues;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArrayIssueDetails [total Jira incidents =" + total+ "]";
	}
	
	

}
