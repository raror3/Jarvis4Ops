package org.jarvis4ops.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraControlChartConfigBean {
	private JiraCurrentViewConfigBean currentViewConfig;

	/**
	 * @return the currentViewConfig
	 */
	public JiraCurrentViewConfigBean getCurrentViewConfig() {
		return currentViewConfig;
	}

	/**
	 * @param currentViewConfig the currentViewConfig to set
	 */
	public void setCurrentViewConfig(JiraCurrentViewConfigBean currentViewConfig) {
		this.currentViewConfig = currentViewConfig;
	}

}
