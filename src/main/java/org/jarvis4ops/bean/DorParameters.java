package org.jarvis4ops.bean;

public class DorParameters {
	
	private String techReview;	//For the field "Tech Review Complete"
	private String acceptanceCriteria; //For the field "Acceptance Criteria Defined"
	private String uxDesign; //For the field "UX Design"
	private String thirdParty; //For the field "3rd Party Dependency"
	private String nfrRequirement; //For the field "NFR Requirement considered"
	
	private String overallStatus;
	
	/**
	 * @return the techReview
	 */
	public String getTechReview() {
		return techReview;
	}
	/**
	 * @param techReview the techReview to set
	 */
	public void setTechReview(String techReview) {
		this.techReview = techReview;
	}
	/**
	 * @return the acceptanceCriteria
	 */
	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}
	/**
	 * @param acceptanceCriteria the acceptanceCriteria to set
	 */
	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}
	/**
	 * @return the uxDesign
	 */
	public String getUxDesign() {
		return uxDesign;
	}
	/**
	 * @param uxDesign the uxDesign to set
	 */
	public void setUxDesign(String uxDesign) {
		this.uxDesign = uxDesign;
	}
	/**
	 * @return the thirdParty
	 */
	public String getThirdParty() {
		return thirdParty;
	}
	/**
	 * @param thirdParty the thirdParty to set
	 */
	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}
	/**
	 * @return the nfrRequirement
	 */
	public String getNfrRequirement() {
		return nfrRequirement;
	}
	/**
	 * @param nfrRequirement the nfrRequirement to set
	 */
	public void setNfrRequirement(String nfrRequirement) {
		this.nfrRequirement = nfrRequirement;
	}
	/**
	 * @return the overallStatus
	 */
	public String getOverallStatus() {
		return overallStatus;
	}
	/**
	 * @param overallStatus the overallStatus to set
	 */
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return techReview + ", " + acceptanceCriteria.trim() + ", "+ uxDesign.trim() + ", " + thirdParty.trim() + ", " + nfrRequirement.trim() + ", "+ overallStatus.trim();
	}	
}
