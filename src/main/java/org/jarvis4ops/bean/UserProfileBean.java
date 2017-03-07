package org.jarvis4ops.bean;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userProfile")
public class UserProfileBean {
	
	@Id
	private String _id;
	private String jiraUsername;
	private String sapientSlackUsername;
	private String msSlackUsername;
	private String bonusLyUsername;
	
	@Email
	private String email;
	private String firstName;
	private String lastName;

	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the jiraUsername
	 */
	public String getJiraUsername() {
		return jiraUsername;
	}
	/**
	 * @param jiraUsername the jiraUsername to set
	 */
	public void setJiraUsername(String jiraUsername) {
		this.jiraUsername = jiraUsername;
	}
	/**
	 * @return the sapientSlackUsername
	 */
	public String getSapientSlackUsername() {
		return sapientSlackUsername;
	}
	/**
	 * @param sapientSlackUsername the sapientSlackUsername to set
	 */
	public void setSapientSlackUsername(String sapientSlackUsername) {
		this.sapientSlackUsername = sapientSlackUsername;
	}
	/**
	 * @return the msSlackUsername
	 */
	public String getMsSlackUsername() {
		return msSlackUsername;
	}
	/**
	 * @param msSlackUsername the msSlackUsername to set
	 */
	public void setMsSlackUsername(String msSlackUsername) {
		this.msSlackUsername = msSlackUsername;
	}
	/**
	 * @return the bonusLyUsername
	 */
	public String getBonusLyUsername() {
		return bonusLyUsername;
	}
	/**
	 * @param bonusLyUsername the bonusLyUsername to set
	 */
	public void setBonusLyUsername(String bonusLyUsername) {
		this.bonusLyUsername = bonusLyUsername;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
