package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.DorParameters;
import org.jarvis4ops.bean.IssueDetails;
import org.jarvis4ops.controller.JiraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DorDodIssuesHelper {
	
	public Map<String, DorParameters> issuesDorDodList(List <IssueDetails> paramIssueList)
	{
		Map <String, DorParameters> issuesMap = new HashMap<String, DorParameters>();;
		
		StringBuffer sbDor = new StringBuffer();
		
		for (IssueDetails issue : paramIssueList)
		{
			String issueKey = issue.getKey() ;
			sbDor.delete(0, sbDor.toString().length());
			
			DorParameters dorParameters = new DorParameters();
			
			int dorCounter = 0;
	
			if(issue.getFields().getCustomfield_13000() != null)
			{  
				dorCounter++;
				dorParameters.setTechReview(issue.getFields().getCustomfield_13000().getValue() );
			}
			else
			{
				dorParameters.setTechReview("No");
			}
			
			if(issue.getFields().getCustomfield_13001() != null)
			{  
				dorCounter++;
				dorParameters.setAcceptanceCriteria(issue.getFields().getCustomfield_13001().getValue() );
			}
			else
			{
				dorParameters.setAcceptanceCriteria("No");
			}
			
			if(issue.getFields().getCustomfield_13002() != null)
			{  
				dorCounter++;
				dorParameters.setUxDesign(issue.getFields().getCustomfield_13002().getValue());
				
			}
			else
			{
				dorParameters.setUxDesign("No");
			}
			
			if(issue.getFields().getCustomfield_13003() != null)
			{  
				dorCounter++;
				dorParameters.setThirdParty(issue.getFields().getCustomfield_13002().getValue());
			}
			else
			{
				dorParameters.setThirdParty("No");
			}
			
			if(issue.getFields().getCustomfield_13004() != null)
			{  
				dorCounter++;
				dorParameters.setNfrRequirement(issue.getFields().getCustomfield_13002().getValue());
			}
			else
			{
				dorParameters.setNfrRequirement("No");
			}
			
			//Appending the overall status
			
			if (dorCounter<5)
			{
				dorParameters.setOverallStatus("N");
			}
			else
			{
				dorParameters.setOverallStatus("Y");
			}
						
			issuesMap.put(issueKey, dorParameters);
			
		}

		return issuesMap;
	}
	
	public String issuesNotCoveredList(Map <String, DorParameters> issuesMap)
	{
		StringBuffer sbfListNotCovered = new StringBuffer();
		int countNonCovered = 0;

//		issuesMap.forEach( (issue, dorList)-> {
//			if (dorList.getOverallStatus().equals("N"))
//			{
//				sbfListNotCovered.append(issue + ":");
//				
//			}
//			countNonCovered++;
//		});
		
		for (String element : issuesMap.keySet()) {
			
			if(issuesMap.get(element).getOverallStatus().equals("N")){
				
				sbfListNotCovered.append(element + ":");
				countNonCovered++;
			}			
		}
		
		sbfListNotCovered.append(countNonCovered);
		return sbfListNotCovered.toString();
	}
}
