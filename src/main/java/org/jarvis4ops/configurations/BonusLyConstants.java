/**
 * 
 */
package org.jarvis4ops.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author raror3
 *
 */
@Component
@PropertySource("classpath:bonusLyConstants.properties")
@ConfigurationProperties(prefix="org.ops4_0.bonusLyConstants")
public class BonusLyConstants {
	private String rockstarRewardCitation;

	/**
	 * @return the rockstarRewardCitation
	 */
	public String getRockstarRewardCitation() {
		return rockstarRewardCitation;
	}

	/**
	 * @param rockstarRewardCitation the rockstarRewardCitation to set
	 */
	public void setRockstarRewardCitation(String rockstarRewardCitation) {
		this.rockstarRewardCitation = rockstarRewardCitation;
	}
	
}