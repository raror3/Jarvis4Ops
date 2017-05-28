package org.jarvis4ops.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.JiraControlChartConfigBean;
import org.jarvis4ops.bean.JiraControlChartIssueBean;
import org.jarvis4ops.bean.JiraControlChartResponseBean;
import org.jarvis4ops.bean.JiraControlChartSwimlaneBean;
import org.jarvis4ops.bean.JiraSwimlaneBean;
import org.jarvis4ops.bean.JiraVersionBean;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
import org.jarvis4ops.dao.JiraControlChartDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class JiraControlChartHelper {
	private static final Logger log = LoggerFactory.getLogger(JiraControlChartHelper.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private DateHelper dateHelper;

	@Autowired
	private JiraHelper jiraHelper;
	
	@Autowired
	private JiraConstants jiraConstants;
	
	@Autowired
	private JiraControlChartHelper jiraControlChartHelper;

	@Autowired
	private JiraControlChartDao jiraControlChartDao;


	/**
	 * This method validates whether project name is valid or not.
	 * @param projectName
	 * @return boolean true/false
	 */
	public boolean validateProjectName(String projectName) {
		if(jiraConstants.getEligibleProjects().contains(projectName)) {
			return true;
		}
		return false;
	}

	/**
	 * This method invokes JIRA API and fetches all release/fix versions (array of objects) for provided project name. 
	 * @param projectName
	 * @param entity
	 * @return JiraVersionBean[] array of Version VOs.
	 * @throws RestClientException
	 */
	public JiraVersionBean[] fetchProjectVersionsFromJira(String projectName) throws RestClientException {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
	    headers.set("Content-Type", "application/json");
	    
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		entity = jiraHelper.setJiraCredDetails();
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JiraVersionBean[]> jiraVersionApiResponse = restTemplate.exchange(configObj.getJiraProjectApiEndPoint() + projectName.toUpperCase() + configObj.getVersions(), HttpMethod.GET, entity, JiraVersionBean[].class);

		return jiraVersionApiResponse.getBody();
	}

	/**
	 * This method invokes JIRA API and returns the map object containing all swimlanes for the provided project name.
	 * @param projectName
	 * @return Map<String, Integer> map of swimlanes
	 */
	public Map<Integer, String> fetchSwimlanesMap(String projectName) {
		Map<Integer, String> projectSwimlaneMap = new HashMap<Integer, String>(1);
		HttpEntity<String> entity = jiraHelper.setJiraCredDetails();
	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JiraControlChartConfigBean> jiraControlChartConfigBean = restTemplate.exchange(configObj.getJiraControlChartConfigApi() + 4041, HttpMethod.GET, entity, JiraControlChartConfigBean.class);
		if (null != jiraControlChartConfigBean && null != jiraControlChartConfigBean.getBody()) {
			for (JiraControlChartSwimlaneBean chartSwimlaneBean : jiraControlChartConfigBean.getBody().getCurrentViewConfig().getSwimlanes()) {
				projectSwimlaneMap.put(chartSwimlaneBean.getId(), chartSwimlaneBean.getName());
			}
		}
		return projectSwimlaneMap;
		
	}

	/**
	 * This method iterates across all swimlanes for provided project and calculates control chart metrics and persists in database.
	 * @param arrayOfVersions JiraVersionBean []
	 * @param projectName String
	 * @param swimlaneDesc String
	 * @param swimlaneId Integer
	 * @return JiraVersionBean
	 * @throws ParseException
	 */
	public JiraVersionBean[] processControlChartMetricsForVersions(JiraVersionBean []arrayOfVersions, String projectName, String swimlaneDesc, Integer swimlaneId) throws ParseException {
		
	    RestTemplate restTemplate;
		
		HttpEntity<String> entity = jiraHelper.setJiraCredDetails();

		for (JiraVersionBean versionBean : arrayOfVersions) {

			ResponseEntity<JiraControlChartResponseBean> jiraControlChartResponse = null;

			boolean validBean = validateVersionBean(versionBean);
			if (validBean) {

			    restTemplate = new RestTemplate();
			    StringBuilder queryString = new StringBuilder("");
			    queryString.append("view=reporting&chart=controlChart");
			    queryString.append("&rapidViewId="+4041);
			    queryString.append("&days=custom");
			    queryString.append("&swimlaneId="+swimlaneId);
			    queryString.append("&from="+dateHelper.getDateInFormat(versionBean.getStartDate(),"yyyy-MM-dd"));
			    queryString.append("&to="+dateHelper.getDateInFormat(versionBean.getReleaseDate(),"yyyy-MM-dd"));
			    //queryString.append("&days=14");
				jiraControlChartResponse = restTemplate.exchange(configObj.getJiraControlChartApiEndPoint()+queryString, HttpMethod.GET, entity, JiraControlChartResponseBean.class);
				log.info("List of issues from JIRA: " + jiraControlChartResponse.getBody().getIssues().size());
							
				JiraSwimlaneBean swimlaneBean = jiraControlChartHelper.calculateMetrics(jiraControlChartResponse.getBody().getIssues(), versionBean);
				if (null != swimlaneBean) {
					swimlaneBean.setSwimLaneDesc(swimlaneDesc);
					swimlaneBean.setSwimLaneId(swimlaneId);
					versionBean.getSwimlane().add(swimlaneBean);

					jiraControlChartDao.persistMetricsToDatabase(versionBean);
				}
			}
		}
		return arrayOfVersions;
	}

	/**
	 * This method checks whether versionBean is valid 
	 * @param versionBean
	 * @return
	 * @throws ParseException
	 */
	private boolean validateVersionBean(JiraVersionBean versionBean) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		yesterday = dateFormat.parse(dateFormat.format(yesterday));

		if (null != versionBean && null != versionBean.getReleaseDate()) {
			Date releaseDate = dateFormat.parse(dateFormat.format(versionBean.getReleaseDate()));
			if ("Ops Sprint".equalsIgnoreCase(versionBean.getDescription()) && yesterday.equals(releaseDate)) {
				return true;
			}
		}
		return false;
	}

	public JiraSwimlaneBean calculateMetrics(List<JiraControlChartIssueBean> issues, JiraVersionBean versionBean) {
		StringBuilder controlChartAvailableIssuesSb = new StringBuilder();
		issues.forEach(jiraControlChartIssue -> {
			if (controlChartAvailableIssuesSb.length() == 0) {
				controlChartAvailableIssuesSb.append(jiraControlChartIssue.getKey());
			} else {
				controlChartAvailableIssuesSb.append("," + jiraControlChartIssue.getKey());
			}
		});

		long startTimeEpoch = dateHelper.getEpochTime(14);
		
		final Map<String, Long> calulatedControlChartMetrics = new HashMap<String, Long>();
		int counter = 0;
		for (JiraControlChartIssueBean jiraControlChartIssue : issues) {
			if (startTimeEpoch < jiraControlChartIssue.getLeaveTimes().get(0) && jiraControlChartIssue.getLeaveTimes().get(1) > -1) {
				long issueCycleTime = jiraControlChartIssue.getTotalTime().get(1) + jiraControlChartIssue.getTotalTime().get(2) + jiraControlChartIssue.getTotalTime().get(3);
				long issueLeadTime = issueCycleTime + jiraControlChartIssue.getTotalTime().get(0);
				log.info("ControlChartMetrics for: " + jiraControlChartIssue.getKey() + ": " + issueCycleTime + " & " + issueLeadTime);
				counter++;
				if (calulatedControlChartMetrics.containsKey("averageLeadTime")) {
					calulatedControlChartMetrics.put("averageLeadTime", calulatedControlChartMetrics.get("averageLeadTime")+issueCycleTime);
					calulatedControlChartMetrics.put("averageCycleTime", calulatedControlChartMetrics.get("averageCycleTime") + issueLeadTime);
				} else {
					calulatedControlChartMetrics.put("averageLeadTime", issueCycleTime);
					calulatedControlChartMetrics.put("averageCycleTime", issueLeadTime);
				}
			}
		}
		log.info("Final filtered count:" + counter);

		log.info("ControlChartMetrics for sprint: " + calulatedControlChartMetrics.get("averageLeadTime") + " & " + calulatedControlChartMetrics.get("averageCycleTime"));
		JiraSwimlaneBean swimlaneBean = null;
		if (!issues.isEmpty()) {
			calulatedControlChartMetrics.put("averageLeadTime", calulatedControlChartMetrics.get("averageLeadTime")/issues.size());
			calulatedControlChartMetrics.put("averageCycleTime", calulatedControlChartMetrics.get("averageCycleTime")/issues.size());
			log.info("Calculated ControlChartMetrics for sprint: " + calulatedControlChartMetrics.get("averageLeadTime") + " & " + calulatedControlChartMetrics.get("averageCycleTime"));
			swimlaneBean = new JiraSwimlaneBean();
			swimlaneBean.setAverageCycleTime(calulatedControlChartMetrics.get("averageCycleTime"));
			swimlaneBean.setAverageLeadTime(calulatedControlChartMetrics.get("averageLeadTime"));
			swimlaneBean.setControlChartIssueCount(counter);
		}

		return swimlaneBean;
	}

}
