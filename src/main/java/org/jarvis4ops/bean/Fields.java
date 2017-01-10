package org.jarvis4ops.bean;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
	private String resolutiondate;
	private String duedate;
	private String [] labels;
	private String customfield_11001;			//Relates the "Epic Link" attribute
	private Assignee assignee;
	private CustomFieldValue customfield_12735;	//For the field "Work Type"
	private CustomFieldName issuetype; 			//For the field "Type"
	private CustomFieldName priority;			//For the field "Priority"

	public Fields() {
		super();
	}

	public String getResolutiondate() {
		return resolutiondate;
	}

	public void setResolutiondate(String resolutiondate) {
		this.resolutiondate = resolutiondate;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	
	public String getCustomfield_11001() {
		return customfield_11001;
	}

	public void setCustomfield_11001(String customfield_11001) {
		this.customfield_11001 = customfield_11001;
	}
	
	public Assignee getAssignee() {
		return assignee;
	}

	public void setAssignee(Assignee assignee) {
		this.assignee = assignee;
	}

	public CustomFieldValue getCustomfield_12735() {
		return customfield_12735;
	}

	public void setCustomfield_12735(CustomFieldValue customfield_12735) {
		this.customfield_12735 = customfield_12735;
	}

	public CustomFieldName getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(CustomFieldName issuetype) {
		this.issuetype = issuetype;
	}
	
	public CustomFieldName getPriority() {
		return priority;
	}

	public void setPriority(CustomFieldName priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "Fields [resolutiondate=" + resolutiondate + ", duedate=" + duedate + ", labels="
				+ Arrays.toString(labels) + ", customfield_11001=" + customfield_11001 + ", assignee=" + assignee
				+ ", customfield_12735=" + customfield_12735 + ", issuetype=" + issuetype + ", priority=" + priority
				+ "]";
	}	
}
