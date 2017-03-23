package org.jarvis4ops.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActiveSprintResponse {
	
	private List<ActiveSprintDetails> values;
	
	public List<ActiveSprintDetails> getValues() {
		return values;
	}

	public void setValues(List<ActiveSprintDetails> values) {
		this.values = values;
	}

	
	
}
