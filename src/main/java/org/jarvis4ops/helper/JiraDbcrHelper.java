package org.jarvis4ops.helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jarvis4ops.bean.SprintDetailBean;
import org.jarvis4ops.bean.JiraActiveSprintResponseBean;
import org.jarvis4ops.bean.DorParameters;
import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.JiraConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@Component
public class JiraDbcrHelper {
	private static final Logger log = LoggerFactory.getLogger(JiraDbcrHelper.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JiraConstants jiraConstants;
	
	@Autowired
	private ApiHelper apiHelper;

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
	
	public Map<String, DorParameters> extractDbcrFields(List <IssueDetails> paramIssueList)
	{
		Map <String, DorParameters> issuesMap = new HashMap<String, DorParameters>();;
		
		StringBuffer sbDbcrs = new StringBuffer();
		String[] sprintString=null;
		String[] sprintName=null; 
		String[] sprintNam=null;
		for (IssueDetails issue : paramIssueList)
		{
			String issueKey = issue.getKey();
			sbDbcrs.delete(0, sbDbcrs.toString().length());
			
			DorParameters dorParameters = new DorParameters();
			
			int dorCounter = 0;
			if(issue.getFields().getCustomfield_10005() != null) { 				
		    sprintString=issue.getFields().getCustomfield_10005();
			sprintNam =sprintString[0].split("name=");
			sprintName= sprintNam[1].split(",");
			dorParameters.setSprintName(sprintName[0]);
			}
			if(issue.getFields().getCustomfield_13000() != null) {  
				dorCounter++;
				dorParameters.setTechReview(issue.getFields().getCustomfield_13000().getValue() );
			} else {
				dorParameters.setTechReview("No");
			}
			
			if(issue.getFields().getCustomfield_13001() != null) {  
				dorCounter++;
				dorParameters.setAcceptanceCriteria(issue.getFields().getCustomfield_13001().getValue() );
			} else {
				dorParameters.setAcceptanceCriteria("No");
			}
			
			if(issue.getFields().getCustomfield_13002() != null) {  
				dorCounter++;
				dorParameters.setUxDesign(issue.getFields().getCustomfield_13002().getValue());
			} else {
				dorParameters.setUxDesign("No");
			}
			
			if(issue.getFields().getCustomfield_13003() != null)
			{  
				dorCounter++;
				dorParameters.setThirdParty(issue.getFields().getCustomfield_13003().getValue());
			} else {
				dorParameters.setThirdParty("No");
			}
			
			if(issue.getFields().getCustomfield_13004() != null) {
				dorCounter++;
				dorParameters.setNfrRequirement(issue.getFields().getCustomfield_13004().getValue());
			} else {
				dorParameters.setNfrRequirement("No");
			}
			
			//Appending the overall status
			if (dorCounter<5) {
				dorParameters.setOverallStatus("N");
			} else {
				dorParameters.setOverallStatus("Y");
			}

			issuesMap.put(issueKey, dorParameters);

		}

		return issuesMap;
	}
		
}
