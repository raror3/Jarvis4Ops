package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrayIssueDetails {
	
	private String total;
	private List<IssueDetails> issues;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
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

	@Override
	public String toString() {
		return "ArrayIssueDetails [issues="+"hello"+"]";
	}

	
}
