package org.jarvis4ops.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlackResponse {
	private String ok;
	
	
	public String getOk() {
		return ok;
	}


	public void setOk(String ok) {
		this.ok = ok;
	}


	@Override
	public String toString() {
		return "SlackResponse [ok=" + ok +  "]";
	}
	
}
