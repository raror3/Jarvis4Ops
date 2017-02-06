package org.jarvis4ops.controller;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.bean.DorParameters;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.HttpConstants;
import org.jarvis4ops.helper.ApiHelper;
import org.jarvis4ops.helper.DorDodIssuesHelper;
import org.jarvis4ops.helper.JiraHelper;
import org.jarvis4ops.helper.ServiceInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class JiraController {
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraHelper jiraIssueResponseHelper;
	
	@Autowired
	private DorDodIssuesHelper dorIssuesHelper;

	@Autowired
	private Environment environment;

	@Autowired
	private HttpConstants httpConstants;

	@Autowired
	private ServiceInvoker serviceInvoker;

	@Autowired
	private ApiHelper apiHelper;
	
	@RequestMapping(path="/getPrevDayJiraRockstars")
	public String getPrevDayJiraRockstars() {
    	HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getPrevDayIncidentRockstarJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);

		Map<String, Integer> rockstarsJiraIssueCountMap = jiraIssueResponseHelper.maxIssueCount(response.getBody().getIssues());

		if (null != rockstarsJiraIssueCountMap && rockstarsJiraIssueCountMap.size()>0) {
			if (httpConstants.getStatusOk().equals(serviceInvoker.invokeSlackServiceToPost(rockstarsJiraIssueCountMap))) {
				serviceInvoker.invokeBonusLyServiceToPost(rockstarsJiraIssueCountMap);
			}
		}
		return rockstarsJiraIssueCountMap.keySet().toString();
    }


	/**
	 * This method invokes the JIRA API to get the details of DOR for specific project.
	 */
	//@RequestMapping(path="/getDorDodJira")
	@RequestMapping(path="/{projectName}/dor")
	public String checkProjectSpecificDorStatus(@PathVariable String projectName)
	{
		String returnString = null;
		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

	    RestTemplate restTemplate = new RestTemplate();
	    String projectNameDorJql = jiraIssueResponseHelper.fetchProjectDorJql(projectName);
		//ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint()+configObj.getDorDodJql(), HttpMethod.GET, entity, ArrayIssueDetails.class);
	    if (StringUtils.isNotEmpty(projectNameDorJql)) {
	    	ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(configObj.getJiraEndPoint() + projectNameDorJql, HttpMethod.GET, entity, ArrayIssueDetails.class);
	    	Map<String, DorParameters> dorIssuesMap = dorIssuesHelper.issuesDorDodList(response.getBody().getIssues());
			
			if (null != dorIssuesMap && dorIssuesMap.size()>0) {
				returnString = invokeSlackServiceDor(dorIssuesMap);
			}
	    }
	    return returnString;
	}

	public String invokeSlackServiceDor(Map<String, DorParameters> dorJiraIssuesMap) {

		Gson gson = new Gson();
		String jiraIssuesJson = gson.toJson(dorJiraIssuesMap);
		
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        apiHelper.setApiAuthHeader(headerMap);
        HttpEntity<String> entity = new HttpEntity<String>(jiraIssuesJson, headerMap);
        String slashUrl = configObj.getHost()+environment.getProperty("server.port")+"/postDorStatus";
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        
        log.info("Response: " + response);
        return response;
    }

}
