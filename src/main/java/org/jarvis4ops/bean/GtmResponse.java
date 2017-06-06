package org.jarvis4ops.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GtmResponse {
	
	private String name;
	private int prod;
	private int burst;
	private int dr;
	private String lastModified;
	/**
	 * @return the lastModified
	 */
	public String getLastModified() {
		return lastModified;
	}
	/**
	 * @param lastModified the lastModified to set
	 */
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	/**
	 * @return the prod
	 */
	public int getProd() {
		return prod;
	}
	/**
	 * @param prod the prod to set
	 */
	public void setProd(int prod) {
		this.prod = prod;
	}
	/**
	 * @return the burst
	 */
	public int getBurst() {
		return burst;
	}
	/**
	 * @param burst the burst to set
	 */
	public void setBurst(int burst) {
		this.burst = burst;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the dr
	 */
	public int getDr() {
		return dr;
	}
	/**
	 * @param dr the dr to set
	 */
	public void setDr(int dr) {
		this.dr = dr;
	}
	
	
}