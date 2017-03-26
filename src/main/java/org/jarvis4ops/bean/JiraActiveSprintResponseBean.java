package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraActiveSprintResponseBean {
	
	private List<SprintDetailBean> values;
	
	public List<SprintDetailBean> getValues() {
		return values;
	}

	public void setValues(List<SprintDetailBean> values) {
		this.values = values;
	}

	
	
}
