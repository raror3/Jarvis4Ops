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
import org.jarvis4ops.bean.JiraSprintDetailResponseBean;
import org.jarvis4ops.bean.JiraSprintValuesBean;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
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

	@RequestMapping(path="/{projectName}/loadPreviousSprintLeadTime", method = { RequestMethod.GET })
	public String invokeJiraControlChartApi(@PathVariable String projectName) throws ParseException {

		boolean isProjectNameValid = validateProjectName(projectName);
		if (isProjectNameValid) {
			
			fetchRecentlyReleasedVersion(projectName);
			
			HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();
	
		    RestTemplate restTemplate = new RestTemplate();
		    StringBuilder queryString = new StringBuilder("");
		    queryString.append("view=reporting&chart=controlChart");
		    queryString.append("&rapidViewId=4041");
		    queryString.append("&swimlaneId=4548");
		    queryString.append("&days=14");
		    //queryString.append("&days=custom&from=2017-02-09&to=2017-02-21");
			ResponseEntity<JiraControlChartResponseBean> jiraControlChartResponse = restTemplate.exchange(configObj.getJiraControlChartApiEndPoint()+queryString, HttpMethod.GET, entity, JiraControlChartResponseBean.class);
			System.out.println("List of issues from JIRA: " + jiraControlChartResponse.getBody().getIssues().size());
						
			Map<String, Long> calulatedControlChartMetrics = calculateAverageLeadTime(jiraControlChartResponse.getBody().getIssues());
			persistMetricsToDatabase(calulatedControlChartMetrics);
			
			Gson gson = new Gson();
			return gson.toJson(jiraControlChartResponse);
		}
		return "Error: Invalid Project name OR invalid request";
    }

	private void fetchRecentlyReleasedVersion(String projectName) throws ParseException {
		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();
	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<JiraSprintDetailResponseBean> jiraSprintApiResponse = restTemplate.exchange(configObj.getJiraAgileBoardEndPoint() + "4041" + configObj.getRecentlyClosedSprintPath() + "closed", HttpMethod.GET, entity, JiraSprintDetailResponseBean.class);

		for (JiraSprintValuesBean sprintValuesBean : jiraSprintApiResponse.getBody().getValues()) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
			Date sprintEndDate = dateFormat.parse(sprintValuesBean.getEndDate());
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -1);
			Date yesterday = calendar.getTime();
			yesterday = dateFormat.parse(dateFormat.format(yesterday));
			
			calendar.add(Calendar.DATE, +1);
			Date tomorrow = calendar.getTime();
			tomorrow = dateFormat.parse(dateFormat.format(tomorrow));

			if (sprintEndDate.after(yesterday) && sprintEndDate.before(tomorrow)) {
				// TODO Auto-generated method stub
			}
		}
	}

	private void persistMetricsToDatabase(Map<String, Long> calulatedControlChartMetrics) {
		// TODO Auto-generated method stub
		
	}

	private Map<String, Long> calculateAverageLeadTime(List<JiraControlChartIssueBean> issues) {
		StringBuilder controlChartAvailableIssuesSb = new StringBuilder();
		issues.forEach(jiraControlChartIssue -> {
			if (controlChartAvailableIssuesSb.length() == 0) {
				controlChartAvailableIssuesSb.append(jiraControlChartIssue.getKey());
			} else {
				controlChartAvailableIssuesSb.append("," + jiraControlChartIssue.getKey());
			}
		});

		long startTimeEpoch = getEpochTime(14);
		
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
		
		// TODO Auto-generated method stub
		return calulatedControlChartMetrics;
	}

	private long getEpochTime(int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, -days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
		Date forteenDaysAgo = calendar.getTime();
		System.out.println("Date comes out to be: " + forteenDaysAgo);
		long epoch = forteenDaysAgo.getTime();
		System.out.println("Date comes out to be: " + epoch);
		return epoch;
	}

	private boolean validateProjectName(String projectName) {
		if(jiraConstants.getEligibleProjects().contains(projectName)) {
			return true;
		}
		return false;
	}

}
