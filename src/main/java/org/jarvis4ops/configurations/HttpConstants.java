/**
 * 
 */
package org.jarvis4ops.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This class consists of all constants related to HTTP response codes.
 * @author raror3
 */
@Component
@PropertySource("classpath:httpConstants.properties")
@ConfigurationProperties(prefix="org.ops4_0.httpConstants")
public class HttpConstants {

	private String statusOk;

	/**
	 * @return the statusOk
	 */
	public String getStatusOk() {
		return statusOk;
	}

	/**
	 * @param statusOk the statusOk to set
	 */
	public void setStatusOk(String statusOk) {
		this.statusOk = statusOk;
	}

}