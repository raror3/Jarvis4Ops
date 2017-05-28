package org.jarvis4ops.bean;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
	private String summary;
	private String created;
	private String resolutiondate;
	private String duedate;
	private String [] labels;
	private String customfield_11001;			//Relates the "Epic Link" attribute
	private Assignee assignee;
	private CustomFieldValue customfield_12735;	//For the field "Work Type"
	private CustomFieldName issuetype; 			//For the field "Type"
	private CustomFieldName priority;			//For the field "Priority"
	
	private CustomFieldValue customfield_13000;	//For the field "Tech Review Complete"
	private CustomFieldValue customfield_13001; //For the field "Acceptance Criteria Defined"
	private CustomFieldValue customfield_13002; //For the field "UX Design"
	private CustomFieldValue customfield_13003; //For the field "3rd Party Dependency"
	private CustomFieldValue customfield_13004; //For the field "NFR Requirement considered"
	private CustomFieldValue customfield_12203; //For the field "DBCR Type"
	private CustomFieldValue customfield_12306; //For Backward Compatibility of DBCR
	private CustomFieldValue customfield_12307; //For Workspace changes of DBCR
	private CustomFieldValue customfield_12230;	//For tokenization required of DBCR
	private CustomFieldValue customfield_12204;	//For Env type of DBCR
	private String [] customfield_12308;		//For affected tables for DBCR
	private String [] customfield_10005; // For Sprint name

	public Fields() {
		super();
	}
	
	
	/**
	 * @return the customfield_12203
	 */
	public CustomFieldValue getCustomfield_12203() {
		return customfield_12203;
	}


	/**
	 * @param customfield_12203 the customfield_12203 to set
	 */
	public void setCustomfield_12203(CustomFieldValue customfield_12203) {
		this.customfield_12203 = customfield_12203;
	}


	/**
	 * @return the customfield_12306
	 */
	public CustomFieldValue getCustomfield_12306() {
		return customfield_12306;
	}


	/**
	 * @param customfield_12306 the customfield_12306 to set
	 */
	public void setCustomfield_12306(CustomFieldValue customfield_12306) {
		this.customfield_12306 = customfield_12306;
	}


	/**
	 * @return the customfield_12307
	 */
	public CustomFieldValue getCustomfield_12307() {
		return customfield_12307;
	}


	/**
	 * @param customfield_12307 the customfield_12307 to set
	 */
	public void setCustomfield_12307(CustomFieldValue customfield_12307) {
		this.customfield_12307 = customfield_12307;
	}


	/**
	 * @return the customfield_12230
	 */
	public CustomFieldValue getCustomfield_12230() {
		return customfield_12230;
	}


	/**
	 * @param customfield_12230 the customfield_12230 to set
	 */
	public void setCustomfield_12230(CustomFieldValue customfield_12230) {
		this.customfield_12230 = customfield_12230;
	}


	/**
	 * @return the customfield_12204
	 */
	public CustomFieldValue getCustomfield_12204() {
		return customfield_12204;
	}


	/**
	 * @param customfield_12204 the customfield_12204 to set
	 */
	public void setCustomfield_12204(CustomFieldValue customfield_12204) {
		this.customfield_12204 = customfield_12204;
	}


	/**
	 * @return the customfield_12308
	 */
	public String[] getCustomfield_12308() {
		return customfield_12308;
	}


	/**
	 * @param customfield_12308 the customfield_12308 to set
	 */
	public void setCustomfield_12308(String[] customfield_12308) {
		this.customfield_12308 = customfield_12308;
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



	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
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

	public CustomFieldValue getCustomfield_13000() {
		return customfield_13000;
	}

	public void setCustomfield_13000(CustomFieldValue customfield_13000) {
		this.customfield_13000 = customfield_13000;
	}

	public CustomFieldValue getCustomfield_13001() {
		return customfield_13001;
	}

	public void setCustomfield_13001(CustomFieldValue customfield_13001) {
		this.customfield_13001 = customfield_13001;
	}

	public CustomFieldValue getCustomfield_13002() {
		return customfield_13002;
	}

	public void setCustomfield_13002(CustomFieldValue customfield_13002) {
		this.customfield_13002 = customfield_13002;
	}

	public CustomFieldValue getCustomfield_13003() {
		return customfield_13003;
	}

	public void setCustomfield_13003(CustomFieldValue customfield_13003) {
		this.customfield_13003 = customfield_13003;
	}

	public CustomFieldValue getCustomfield_13004() {
		return customfield_13004;
	}

	public void setCustomfield_13004(CustomFieldValue customfield_13004) {
		this.customfield_13004 = customfield_13004;
	}

	@Override
	public String toString() {
		return "Fields [created="+created+", resolutiondate=" + resolutiondate + ", duedate=" + duedate + ", labels="
				+ Arrays.toString(labels) + ", customfield_11001=" + customfield_11001 + ", assignee=" + assignee
				+ ", customfield_12735=" + customfield_12735 + ", issuetype=" + issuetype + ", priority=" + priority
				+ "]";
	}
	
	public String txtDorDod()
	{
		return "Tech Review Complete=" + customfield_13000 + ", Acceptance Criteria Defined=" + customfield_13000
				+", UX Design=" + customfield_13002 + ", NFR Requirement considered=" + customfield_13002
				+", 3rd Party Dependency=" + customfield_13003;
	}


	/**
	 * @return the customfield_10005
	 */
	public String[] getCustomfield_10005() {
		return customfield_10005;
	}


	/**
	 * @param customfield_10005 the customfield_10005 to set
	 */
	public void setCustomfield_10005(String[] customfield_10005) {
		this.customfield_10005 = customfield_10005;
	}


}
