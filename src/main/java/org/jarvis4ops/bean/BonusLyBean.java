/**
 * 
 */
package org.jarvis4ops.bean;

/**
 * @author raror3
 * This class contains bean properties, setters and getters to generate VO for Bonusly Api invocation.
 */
public class BonusLyBean {
	private String giver_email;
	private String reason;

	/**
	 * @return the giver_email
	 */
	public String getGiver_email() {
		return giver_email;
	}
	/**
	 * @param giver_email the giver_email to set
	 */
	public void setGiver_email(String giver_email) {
		this.giver_email = giver_email;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

}
