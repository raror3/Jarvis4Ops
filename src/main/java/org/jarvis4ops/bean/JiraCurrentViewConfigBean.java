package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraCurrentViewConfigBean {
	private int id;
	private String name;
	private boolean canEdit;
	private boolean sprintSupportEnabled;
	private boolean showDaysInColumn;
	private List<JiraControlChartSwimlaneBean> swimlanes;

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
	 * @return the canEdit
	 */
	public boolean isCanEdit() {
		return canEdit;
	}
	/**
	 * @param canEdit the canEdit to set
	 */
	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	/**
	 * @return the sprintSupportEnabled
	 */
	public boolean isSprintSupportEnabled() {
		return sprintSupportEnabled;
	}
	/**
	 * @param sprintSupportEnabled the sprintSupportEnabled to set
	 */
	public void setSprintSupportEnabled(boolean sprintSupportEnabled) {
		this.sprintSupportEnabled = sprintSupportEnabled;
	}
	/**
	 * @return the showDaysInColumn
	 */
	public boolean isShowDaysInColumn() {
		return showDaysInColumn;
	}
	/**
	 * @param showDaysInColumn the showDaysInColumn to set
	 */
	public void setShowDaysInColumn(boolean showDaysInColumn) {
		this.showDaysInColumn = showDaysInColumn;
	}
	/**
	 * @return the swimlanes
	 */
	public List<JiraControlChartSwimlaneBean> getSwimlanes() {
		return swimlanes;
	}
	/**
	 * @param swimlanes the swimlanes to set
	 */
	public void setSwimlanes(List<JiraControlChartSwimlaneBean> swimlanes) {
		this.swimlanes = swimlanes;
	}

}
