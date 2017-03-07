package org.jarvis4ops.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.JiraControlChartIssueBean;
import org.jarvis4ops.bean.JiraControlChartResponseBean;
import org.jarvis4ops.bean.JiraVersionBean;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
import org.jarvis4ops.helper.DateHelper;
import org.jarvis4ops.helper.JiraHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class JiraControlChartController {
	private static final Logger log = LoggerFactory.getLogger(JiraControlChartController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraHelper jiraIssueResponseHelper;
	
	@Autowired
	private JiraConstants jiraConstants;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DateHelper dateHelper;

	@RequestMapping(path="/{projectName}/loadPreviousSprintLeadTime", method = { RequestMethod.GET })
	public String invokeJiraControlChartApi(@PathVariable String projectName) throws ParseException {

		boolean isProjectNameValid = validateProjectName(projectName);
		ResponseEntity<JiraControlChartResponseBean> jiraControlChartResponse = null;
		Gson gson = new Gson();

		if (isProjectNameValid) {
			
			JiraVersionBean versionBean = fetchRecentlyReleasedVersion(projectName);
			
			if (null != versionBean) {
				HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

			    RestTemplate restTemplate = new RestTemplate();
			    StringBuilder queryString = new StringBuilder("");
			    queryString.append("view=reporting&chart=controlChart");
			    queryString.append("&rapidViewId=4041");
			    queryString.append("&days=custom");
			    queryString.append("&swimlaneId=4548");
			    queryString.append("&from="+dateHelper.getDateInFormat(versionBean.getStartDate(),"yyyy-MM-dd"));
			    queryString.append("&to="+dateHelper.getDateInFormat(versionBean.getReleaseDate(),"yyyy-MM-dd"));
			    //queryString.append("&days=14");
				jiraControlChartResponse = restTemplate.exchange(configObj.getJiraControlChartApiEndPoint()+queryString, HttpMethod.GET, entity, JiraControlChartResponseBean.class);
				log.info("List of issues from JIRA: " + jiraControlChartResponse.getBody().getIssues().size());
							
				calculateAverageLeadTime(jiraControlChartResponse.getBody().getIssues(), versionBean);
				persistMetricsToDatabase(versionBean);

				return gson.toJson(versionBean);
			}
			return "WARN: No recently closed release version";
		}
		return "Error: Invalid Project name OR invalid request";
    }

	private JiraVersionBean fetchRecentlyReleasedVersion(String projectName) throws ParseException {
		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();
	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JiraVersionBean[]> jiraSprintApiResponse = restTemplate.exchange(configObj.getJiraProjectApiEndPoint() + projectName.toUpperCase() + configObj.getVersions(), HttpMethod.GET, entity, JiraVersionBean[].class);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd");
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		yesterday = dateFormat.parse(dateFormat.format(yesterday));

		JiraVersionBean []jiraVersionBeanArr = jiraSprintApiResponse.getBody();

		for (JiraVersionBean versionBean : jiraVersionBeanArr) {

			/*if (null != versionBean.getReleaseDate() && "Ops Sprints".equalsIgnoreCase(versionBean.getDescription()) && versionBean.getReleaseDate().equals(yesterday)) {
				return versionBean;
			}*/
			if (null != versionBean.getName() && versionBean.getName().equals("Embury")) {
				return versionBean;
			}
		}
		return null;
	}

	private void persistMetricsToDatabase(JiraVersionBean calulatedControlChartMetrics) {
		// TODO Auto-generated method stub

	}

	private JiraVersionBean calculateAverageLeadTime(List<JiraControlChartIssueBean> issues, JiraVersionBean versionBean) {
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
		
		calulatedControlChartMetrics.put("averageLeadTime", calulatedControlChartMetrics.get("averageLeadTime")/issues.size());
		calulatedControlChartMetrics.put("averageCycleTime", calulatedControlChartMetrics.get("averageCycleTime")/issues.size());
		log.info("Calculated ControlChartMetrics for sprint: " + calulatedControlChartMetrics.get("averageLeadTime") + " & " + calulatedControlChartMetrics.get("averageCycleTime"));
		versionBean.setAverageCycleTime(calulatedControlChartMetrics.get("averageCycleTime"));
		versionBean.setAverageLeadTime(calulatedControlChartMetrics.get("averageLeadTime"));
		versionBean.setControlChartIssueCount(counter);
		return versionBean;
	}

	private boolean validateProjectName(String projectName) {
		if(jiraConstants.getEligibleProjects().contains(projectName)) {
			return true;
		}
		return false;
	}

}
