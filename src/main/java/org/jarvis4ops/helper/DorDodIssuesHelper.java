package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.controller.JiraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DorDodIssuesHelper {
	
	public Map<String, Integer> issuesDorDodList(List <IssueDetails> paramIssueList)
	{
		Map <String, Integer> issuesMap = new HashMap<String, Integer>();;
		
		for (IssueDetails issue : paramIssueList)
		{
			String issueKey = issue.getKey() + ":" +issue.getFields().getSummary();
			
			int dorCounter = 0;
	
			if(issue.getFields().getCustomfield_13000() != null)
			{  
				dorCounter++;
			}
			
			if(issue.getFields().getCustomfield_13001() != null)
			{  
				dorCounter++;
			}
			
			if(issue.getFields().getCustomfield_13002() != null)
			{  
				dorCounter++;
			}
			
			if(issue.getFields().getCustomfield_13003() != null)
			{  
				dorCounter++;
			}
			
			if(issue.getFields().getCustomfield_13004() != null)
			{  
				dorCounter++;
			}
			
				issuesMap.put(issueKey, dorCounter);
			
		}

		return issuesMap;
	}
	
	public boolean isNull(String paramNull)
	{
		if(null == paramNull || paramNull.length() == 0)
			return true;
		else
			return false;
	}
	
	public void printTable(Map<String, Integer> paramIssuesList)
	{
		
	}


}
