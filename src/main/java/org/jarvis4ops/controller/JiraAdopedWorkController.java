package org.jarvis4ops.controller;

import org.jarvis4ops.bean.ArrayIssueDetails;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.JiraHelper;
import org.jarvis4ops.helper.ServiceInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JiraAdopedWorkController {

	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private JiraHelper jiraIssueResponseHelper;
	

	@Autowired
	private ServiceInvoker serviceInvoker;

	@RequestMapping(value="/sprintAdoptedWork/{project}" , method = { RequestMethod.GET })
	public void getSprintAdoptedWork(@PathVariable(value = "project", required = true) String project) {

		HttpEntity<String> entity = jiraIssueResponseHelper.setJiraCredDetails();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayIssueDetails> response = restTemplate.exchange(
				configObj.getJiraEndPoint() + configObj.getAdoptedWorkJql() + project, HttpMethod.GET, entity,
				ArrayIssueDetails.class);
		if (null != response.getBody().getIssues() && !response.getBody().getIssues().isEmpty()) {
			log.info("New Adopted Work count : "+ response.getBody().getIssues().size());
			serviceInvoker.invokeAdoptedWorkService(response.getBody(), project);
		}
	}

}
