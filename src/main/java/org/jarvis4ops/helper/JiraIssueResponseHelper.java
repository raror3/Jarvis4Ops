package org.jarvis4ops.helper;

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
import org.jarvis4ops.controller.JiraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JiraIssueResponseHelper {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	@Autowired
	private Configurations configObj;
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
		
		issuesMap.entrySet().removeIf(entry -> entry.getValue() < 5);
		
		return issuesMap;
		/*issuesMap.forEach((user,countOfFixed)->{
			if (countOfFixed <5) {
				issuesMap.remove(user);
			}
		});*/
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
			System.out.println(assignee + " has fixed: " + jiraIssuesResponseMap.get(assignee));
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


}
