/**
 * 
 */
package org.jarvis4ops.bean;

/**
 * @author raror3
 * This class contains bean properties, setters and getters to cater to Bonusly Api response.
 */
public class BonusLyResponseBean {
	private boolean success;
	private String message;

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
