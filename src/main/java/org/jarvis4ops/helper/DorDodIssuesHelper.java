package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jarvis4ops.bean.DorParameters;
import org.jarvis4ops.bean.IssueDetails;
import org.springframework.stereotype.Component;

@Component
public class DorDodIssuesHelper {
	
	public Map<String, DorParameters> issuesDorDodList(List <IssueDetails> paramIssueList)
	{
		Map <String, DorParameters> issuesMap = new HashMap<String, DorParameters>();;
		
		StringBuffer sbDor = new StringBuffer();
		
		for (IssueDetails issue : paramIssueList)
		{
			String issueKey = issue.getKey();
			sbDor.delete(0, sbDor.toString().length());
			
			DorParameters dorParameters = new DorParameters();
			
			int dorCounter = 0;
	
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
	
	public String populateDataforDorImage(Map <String, DorParameters> issuesMap) {
		
		StringBuilder dataStringBuilder = new StringBuilder("\n");
		dataStringBuilder.append(String.format("%-25s", "JIRA ID"));
		dataStringBuilder.append(String.format("%-25s", "TechReviewComplete"));
		dataStringBuilder.append(String.format("%-25s", "AcceptanceCriteria"));
		dataStringBuilder.append(String.format("%-25s", "UXDesign"));
		dataStringBuilder.append(String.format("%-25s", "3rdParty"));
		dataStringBuilder.append(String.format("%-25s", "NFR"));
		dataStringBuilder.append("\n");
		
		for (Map.Entry<String, DorParameters> mapKey : issuesMap.entrySet()) {

			dataStringBuilder.append("\n");
			dataStringBuilder.append(String.format("%-20s", mapKey.getKey()));
			DorParameters storyDorParameters = mapKey.getValue();
			if("Yes".equalsIgnoreCase(storyDorParameters.getTechReview())) {  
				dataStringBuilder.append(String.format("%-37s", storyDorParameters.getTechReview()));
			} else {
				dataStringBuilder.append(String.format("%-38s", "No"));
			}
			
			if("Yes".equalsIgnoreCase(storyDorParameters.getAcceptanceCriteria())) {  
				dataStringBuilder.append(String.format("%-40s", storyDorParameters.getAcceptanceCriteria()));
			} else {
				dataStringBuilder.append(String.format("%-41s", "No"));
			}
			
			if("Yes".equalsIgnoreCase(storyDorParameters.getUxDesign())) {  
				dataStringBuilder.append(String.format("%-30s", storyDorParameters.getUxDesign()));
			} else {
				dataStringBuilder.append(String.format("%-30s", "No"));
			}
			
			if("Yes".equalsIgnoreCase(storyDorParameters.getThirdParty()))
			{  
				dataStringBuilder.append(String.format("%-23s", storyDorParameters.getThirdParty()));
			} else {
				dataStringBuilder.append(String.format("%-24s", "No"));
			}
			
			if("Yes".equalsIgnoreCase(storyDorParameters.getNfrRequirement())) {
				dataStringBuilder.append(String.format("%-24s", storyDorParameters.getNfrRequirement()));
			} else {
				dataStringBuilder.append(String.format("%-25s", "No"));
			}
		}
		return dataStringBuilder.toString();
	}
	
	public String countOfissuesDorImcomplete(Map <String, DorParameters> issuesMap)
	{
		int countNonCovered = 0;
		for (String element : issuesMap.keySet()) {
			if(issuesMap.get(element).getOverallStatus().equals("N")){
				countNonCovered++;
			}			
		}
		return (Integer.toString(countNonCovered) + "/" + Integer.toString(issuesMap.size()));
	}

	public String getIncompleteDorStoryList(Map <String, DorParameters> issuesMap)
	{
		StringBuilder sbfListNotCovered = new StringBuilder();
		for (String element : issuesMap.keySet()) {
			if(issuesMap.get(element).getOverallStatus().equals("N")){
				sbfListNotCovered.append(element + ":");
			}
		}
		sbfListNotCovered.toString().substring(0, sbfListNotCovered.length() - 2);
		return sbfListNotCovered.toString();
	}

}
