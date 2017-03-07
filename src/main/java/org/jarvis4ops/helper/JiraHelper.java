package org.jarvis4ops.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class JiraHelper {
	private static final Logger log = LoggerFactory.getLogger(JiraHelper.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JiraConstants jiraConstants;
	
	@Autowired
	private ApiHelper apiHelper;

	/**
	 * @return
	 */
	public HttpEntity<String> setJiraCredDetails() {

		String plainCreds = configObj.getJiraCreds();
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.getEncoder().encode(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic "+base64Creds);
		headers.set("Accept", "application/json");
	    headers.set("Content-Type", "application/json");
	    
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}

	public Map<String, Integer> maxIssueCount(List <IssueDetails> paramIssueList)
	{
		Map <String, Integer> issuesMap = new HashMap<String, Integer>();;
		
		for (IssueDetails issue : paramIssueList)
		{
			String nameKey = issue.getFields().getAssignee().getName();
			
			if(issuesMap.containsKey(nameKey))
			{  
				int i = issuesMap.get(issue.getFields().getAssignee().getName());
				issuesMap.replace(nameKey, ++i);
			}
			else
			{
				issuesMap.put(nameKey, 1);
			}
		}

		return updateMapWithRockstars(issuesMap);
	}

	private Map<String, Integer> updateMapWithRockstars(Map<String, Integer> issuesMap) {
		
		issuesMap.entrySet().removeIf(entry -> entry.getValue() < jiraConstants.getPrevDayJiraRockstarThreshold());
		
		return issuesMap;
	}

	/**
	 * @param jiraIssuesResponseMap
	 * @param rockStar
	 */
	private void getRockstars(Map<String, Integer> jiraIssuesResponseMap, StringBuffer rockStar) {
		Set<String> assigneeSet;
		assigneeSet = jiraIssuesResponseMap.keySet();
		
		for (String assignee : assigneeSet)
		{
			log.info(assignee + " has fixed: " + jiraIssuesResponseMap.get(assignee));
			if(jiraIssuesResponseMap.get(assignee) > 2)
				{
					if (rockStar.length() == 0)
					{
					rockStar.append(assignee);
					}
					else
					{
						rockStar.append("," + assignee);
					}		
							
				}
		}
	}
	
	public Map<String, Integer> issuesTimeElapsed(List <IssueDetails> paramIssueList)
	{
		Map <String, Integer> yesterdaysIssuesMap = new HashMap<String, Integer>();;
		
		for (IssueDetails issue : paramIssueList)
		{
			String jiraDatePattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
			
			SimpleDateFormat sdf = new SimpleDateFormat(jiraDatePattern);
			
			String issueKey = issue.getKey();
			String nameKey = issue.getFields().getAssignee().getName();
			
			Calendar issueCreated = Calendar.getInstance();
			Calendar issueResolved = Calendar.getInstance();
			try {
				issueCreated.setTime(sdf.parse(issue.getFields().getCreated()));
			
			
				
				issueResolved.setTime(sdf.parse(issue.getFields().getResolutiondate()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long createTimeInMiliSeconds = issueCreated.getTimeInMillis();
			long resolvedTimeInMiliSeconds = issueResolved.getTimeInMillis();
				
			long timeDiff = resolvedTimeInMiliSeconds - createTimeInMiliSeconds;
				
			log.info(issueKey+ " - " + nameKey +" - " + timeDiff/(1000*60));
				
			yesterdaysIssuesMap.put(issueKey, (int) timeDiff/(1000*60));
		
		}

		return yesterdaysIssuesMap;
	}

	/**
	 * @param jiraMaxwipCounts
	 */
	public boolean isJiraMaxWipLimitBreached(Map<String, Integer> jiraMaxWipCoints) {
		boolean maxWipLimitBreached = false;
		if (jiraMaxWipCoints.get("InDev") > configObj.getJiraInDevWipMaxLimit()) {
			maxWipLimitBreached = true;
		} else if (jiraMaxWipCoints.get("InTest") > configObj.getJiraInDevWipMaxLimit()) {
			maxWipLimitBreached = true;
		} else if (jiraMaxWipCoints.get("ToDo") > configObj.getJiraInDevWipMaxLimit()) {
			maxWipLimitBreached = true;
		}
		return maxWipLimitBreached;
	}

	public void invokeSlackPostWipBreachedInfo(Map<String, Integer> jiraMaxWipCountMap) {

		Gson gson = new Gson();
		log.info("Response JSON for WIP limits from JIRA: " + gson.toJson(jiraMaxWipCountMap));
		String jiraMaxWipCountJson = gson.toJson(jiraMaxWipCountMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(jiraMaxWipCountJson, headerMap);
        String slackUrl = configObj.getHost() + environment.getProperty("server.port") + "/postMaxWipBreachedSlack";
        String response = restTemplate.postForObject(slackUrl, entity, String.class);
        log.info("Response: " + response);
    }
	
	public void invokeSlackPostFoundWork(Map<String, Integer> jiraFoundWorkCountMap) {

		Gson gson = new Gson();
		log.info("Response JSON for Found Work from JIRA: " + gson.toJson(jiraFoundWorkCountMap));
		String jiraFoundWorkCountJson = gson.toJson(jiraFoundWorkCountMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(jiraFoundWorkCountJson, headerMap);
        String slackUrl = configObj.getHost() + environment.getProperty("server.port") + "/postFoundWorkSlack";
        String response = restTemplate.postForObject(slackUrl, entity, String.class);
        log.info("Response: " + response);
    }

	public String fetchProjectDorJql(String projectName) {
		String projectNameJql = null;

		if(jiraConstants.getEligibleProjects().contains(projectName)) {
			try {
				Class classObj = jiraConstants.getClass();
				Method gs1Method = classObj.getMethod("get"+projectName+"DorJql");
				projectNameJql = (String) gs1Method.invoke(jiraConstants, new Object[] {});

			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				log.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (SecurityException e) {
				log.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				log.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				log.error(e.getMessage(), e.fillInStackTrace());
				e.printStackTrace();
			}
			
		}
		return projectNameJql;
	}

}
