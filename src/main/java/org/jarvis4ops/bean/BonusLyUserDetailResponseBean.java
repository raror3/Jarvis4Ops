/**
 * 
 */
package org.jarvis4ops.bean;

/**
 * @author raror3
 * This class contains bean properties, setters and getters to cater to Bonusly Api response.
 */
public class BonusLyUserDetailResponseBean {
	private boolean success;
	private BonusLyUserDetailBean result;

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
	 * @return the result
	 */
	public BonusLyUserDetailBean getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(BonusLyUserDetailBean result) {
		this.result = result;
	}

}
