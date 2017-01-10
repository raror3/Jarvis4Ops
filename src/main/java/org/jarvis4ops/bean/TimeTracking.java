package org.jarvis4ops.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//For tracking the "Logged" Field

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeTracking {
	private String timeSpent;
	private String timeSpentSeconds;
	
	public TimeTracking() {
		super();
	}
	
	public String getTimeSpent() {
		return timeSpent;
	}
	
	public void setTimeSpent(String timeSpent) {
		this.timeSpent = timeSpent;
	}
	
	public String getTimeSpentSeconds() {
		return timeSpentSeconds;
	}
	
	public void setTimeSpentSeconds(String timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}
	
	@Override
	public String toString() {
		return "TimeTracking [timeSpent=" + timeSpent + ", timeSpentSeconds=" + timeSpentSeconds + "]";
	}
}
