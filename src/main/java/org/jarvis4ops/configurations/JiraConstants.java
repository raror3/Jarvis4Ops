/**
 * 
 */
package org.jarvis4ops.configurations;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * This class consists of all constants related to HTTP response codes.
 * @author raror3
 */
@Component
@PropertySource("classpath:jiraConstants.properties")
@ConfigurationProperties(prefix="org.ops4_0.jiraConstants")
public class JiraConstants {

	private List<String> eligibleProjects;
	private String shopcDorJql;
	private String shopbDorJql;

	private String supportDorJql;
	private String shopcActiveSprint;
	private String shopbActiveSprint;
	private String supportActiveSprint;

	private Integer prevDayJiraRockstarThreshold;

	/**
	 * @return the prevDayJiraRockstarThreshold
	 */
	public Integer getPrevDayJiraRockstarThreshold() {
		return prevDayJiraRockstarThreshold;
	}

	/**
	 * @param prevDayJiraRockstarThreshold the prevDayJiraRockstarThreshold to set
	 */
	public void setPrevDayJiraRockstarThreshold(Integer prevDayJiraRockstarThreshold) {
		this.prevDayJiraRockstarThreshold = prevDayJiraRockstarThreshold;
	}

	/**
	 * @return the shopcDorJql
	 */
	public String getShopcDorJql() {
		return shopcDorJql;
	}

	/**
	 * @param shopcDorJql the shopcDorJql to set
	 */
	public void setShopcDorJql(String shopcDorJql) {
		this.shopcDorJql = shopcDorJql;
	}

	/**
	 * @return the shopbDorJql
	 */
	public String getShopbDorJql() {
		return shopbDorJql;
	}

	/**
	 * @param shopbDorJql the shopbDorJql to set
	 */
	public void setShopbDorJql(String shopbDorJql) {
		this.shopbDorJql = shopbDorJql;
	}

	/**
	 * @return the eligibleProjects
	 */
	public List<String> getEligibleProjects() {
		return eligibleProjects;
	}

	/**
	 * @param eligibleProjects the eligibleProjects to set
	 */
	public void setEligibleProjects(List<String> eligibleProjects) {
		this.eligibleProjects = eligibleProjects;
	}
	
	/**
	 * @return shopcActiveSprint
	 */
	public String getShopcActiveSprint(){
		return shopcActiveSprint;
	}
	
	/**
	 * 
	 * @param shopcActiveSprint
	 */
	public void setShopcActiveSprint(String shopcActiveSprint){
		
	this.shopcActiveSprint=shopcActiveSprint;
	}
	/**
	 * @return shopbActiveSprint
	 */
	public String getShopbActiveSprint(){
		return shopbActiveSprint;
	}
	
	/**
	 * 
	 * @param shopbActiveSprint
	 */
	public void setShopbActiveSprint(String shopbActiveSprint){
		
	this.shopbActiveSprint=shopbActiveSprint;
	}
	/**
	 * @return supportActiveSprint
	 */
	public String getSupportActiveSprint(){
		return supportActiveSprint;
	}
	
	/**
	 * 
	 * @param supportActiveSprint
	 */
	public void setSupportActiveSprint(String supportActiveSprint){
		
	this.supportActiveSprint=supportActiveSprint;
	}

	/**
	 * @return the supportDorJql
	 */
	public String getSupportDorJql() {
		return supportDorJql;
	}

	/**
	 * @param supportDorJql the supportDorJql to set
	 */
	public void setSupportDorJql(String supportDorJql) {
		this.supportDorJql = supportDorJql;
	}

}