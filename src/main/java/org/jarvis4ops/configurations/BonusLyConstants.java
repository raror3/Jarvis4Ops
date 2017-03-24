/**
 * 
 */
package org.jarvis4ops.configurations;

import java.util.List;

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
	private List<String> rockstarRewardCitation;

	/**
	 * @return the rockstarRewardCitation
	 */
	public List<String> getRockstarRewardCitation() {
		return rockstarRewardCitation;
	}

	/**
	 * @param rockstarRewardCitation the rockstarRewardCitation to set
	 */
	public void setRockstarRewardCitation(List<String> rockstarRewardCitation) {
		this.rockstarRewardCitation = rockstarRewardCitation;
	}
	
}