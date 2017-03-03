/**
 * 
 */
package org.jarvis4ops.bean;

import java.util.List;

/**
 * @author raror3
 * This class contains bean properties, setters and getters to generate VO for Bonusly Api invocation.
 */
public class BonusLyUserDetailBean {
	private String id;
	private String short_name;
	private String username;
	private String display_name;
	private String email;
	private boolean can_give;
	//private List<Integer> give_amounts;
	private String status;
	private int earning_balance;
	private int giving_balance;
	private String location;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the short_name
	 */
	public String getShort_name() {
		return short_name;
	}
	/**
	 * @param short_name the short_name to set
	 */
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the display_name
	 */
	public String getDisplay_name() {
		return display_name;
	}
	/**
	 * @param display_name the display_name to set
	 */
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the can_give
	 */
	public boolean isCan_give() {
		return can_give;
	}
	/**
	 * @param can_give the can_give to set
	 */
	public void setCan_give(boolean can_give) {
		this.can_give = can_give;
	}
	/**
	 * @return the give_amounts
	 *//*
	public List<Integer> getGive_amounts() {
		return give_amounts;
	}
	*//**
	 * @param give_amounts the give_amounts to set
	 *//*
	public void setGive_amounts(List<Integer> give_amounts) {
		this.give_amounts = give_amounts;
	}*/
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the earning_balance
	 */
	public int getEarning_balance() {
		return earning_balance;
	}
	/**
	 * @param earning_balance the earning_balance to set
	 */
	public void setEarning_balance(int earning_balance) {
		this.earning_balance = earning_balance;
	}
	/**
	 * @return the giving_balance
	 */
	public int getGiving_balance() {
		return giving_balance;
	}
	/**
	 * @param giving_balance the giving_balance to set
	 */
	public void setGiving_balance(int giving_balance) {
		this.giving_balance = giving_balance;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
}
