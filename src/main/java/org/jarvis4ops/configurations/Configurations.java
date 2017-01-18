/**
 * 
 */
package org.jarvis4ops.configurations;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author raror3
 *
 */
@Component
@ConfigurationProperties(prefix="org.ops4_0.constants")
public class Configurations {

	private String port;
	private String host;
	private String greatJobTitleMsg;
	private String incidentsResolvedMsg;
	private String cfd;
	private String jiraCreds;
	private List<String> incidentRockstarMemeList;
	private String jiraEndPoint;
	private String prevDayIncidentRockstarJql;
	private String sampleJiraEndPoint;
	private String slackApiKey;
	private String slackService;
	private String teamWork;
	private String openSiteConfidenceIncidentsJql;
	private String emptySpace = " ";

	/**
	 * @return the emptySpace
	 */
	public String getEmptySpace() {
		return emptySpace;
	}

	/**
	 * @param emptySpace the emptySpace to set
	 */
	public void setEmptySpace(String emptySpace) {
		this.emptySpace = emptySpace;
	}

	/**
	 * @return the openSiteConfidenceIncidentsJql
	 */
	public String getOpenSiteConfidenceIncidentsJql() {
		return openSiteConfidenceIncidentsJql;
	}

	/**
	 * @param openSiteConfidenceIncidentsJql the openSiteConfidenceIncidentsJql to set
	 */
	public void setOpenSiteConfidenceIncidentsJql(String openSiteConfidenceIncidentsJql) {
		this.openSiteConfidenceIncidentsJql = openSiteConfidenceIncidentsJql;
	}

	/**
	 * @return the teamWork
	 */
	public String getTeamWork() {
		return teamWork;
	}

	/**
	 * @param teamWork the teamWork to set
	 */
	public void setTeamWork(String teamWork) {
		this.teamWork = teamWork;
	}

	/**
	 * @return the slackApiKey
	 */
	public String getSlackApiKey() {
		return slackApiKey;
	}

	/**
	 * @param slackApiKey the slackApiKey to set
	 */
	public void setSlackApiKey(String slackApiKey) {
		this.slackApiKey = slackApiKey;
	}

	/**
	 * @return the slackService
	 */
	public String getSlackService() {
		return slackService;
	}

	/**
	 * @param slackService the slackService to set
	 */
	public void setSlackService(String slackService) {
		this.slackService = slackService;
	}

	/**
	 * @return the sampleJiraEndPoint
	 */
	public String getSampleJiraEndPoint() {
		return sampleJiraEndPoint;
	}

	/**
	 * @param sampleJiraEndPoint the sampleJiraEndPoint to set
	 */
	public void setSampleJiraEndPoint(String sampleJiraEndPoint) {
		this.sampleJiraEndPoint = sampleJiraEndPoint;
	}

	/**
	 * @return the jiraEndPoint
	 */
	public String getJiraEndPoint() {
		return jiraEndPoint;
	}

	/**
	 * @param jiraEndPoint the jiraEndPoint to set
	 */
	public void setJiraEndPoint(String jiraEndPoint) {
		this.jiraEndPoint = jiraEndPoint;
	}

	/**
	 * @return the prevDayIncidentRockstarJql
	 */
	public String getPrevDayIncidentRockstarJql() {
		return prevDayIncidentRockstarJql;
	}

	/**
	 * @param prevDayIncidentRockstarJql the prevDayIncidentRockstarJql to set
	 */
	public void setPrevDayIncidentRockstarJql(String prevDayIncidentRockstarJql) {
		this.prevDayIncidentRockstarJql = prevDayIncidentRockstarJql;
	}

	/**
	 * @return the jiraCreds
	 */
	public String getJiraCreds() {
		return jiraCreds;
	}

	/**
	 * @param jiraCreds the jiraCreds to set
	 */
	public void setJiraCreds(String jiraCreds) {
		this.jiraCreds = jiraCreds;
	}

	/**
	 * @return the incidentRockstarMemeList
	 */
	public List<String> getIncidentRockstarMemeList() {
		return incidentRockstarMemeList;
	}

	/**
	 * @param incidentRockstarMemeList the incidentRockstarMemeList to set
	 */
	public void setIncidentRockstarMemeList(List<String> incidentRockstarMemeList) {
		this.incidentRockstarMemeList = incidentRockstarMemeList;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the greatJobTitleMsg
	 */
	public String getGreatJobTitleMsg() {
		return greatJobTitleMsg;
	}

	/**
	 * @param greatJobTitleMsg the greatJobTitleMsg to set
	 */
	public void setGreatJobTitleMsg(String greatJobTitleMsg) {
		this.greatJobTitleMsg = greatJobTitleMsg;
	}

	/**
	 * @return the incidentsResolvedMsg
	 */
	public String getIncidentsResolvedMsg() {
		return incidentsResolvedMsg;
	}

	/**
	 * @param incidentsResolvedMsg the incidentsResolvedMsg to set
	 */
	public void setIncidentsResolvedMsg(String incidentsResolvedMsg) {
		this.incidentsResolvedMsg = incidentsResolvedMsg;
	}

	/**
	 * @return the cfd
	 */
	public String getCfd() {
		return cfd;
	}

	/**
	 * @param cfd the cfd to set
	 */
	public void setCfd(String cfd) {
		this.cfd = cfd;
	}

}
