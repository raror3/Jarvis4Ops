package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jarvis4ops.bean.IssueDetails;
import org.springframework.stereotype.Component;

@Component
public class JiraIssueResponseHelper {
	
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

}
