package org.jarvis4ops.controller;

import java.text.ParseException;
import java.util.Map;

import org.jarvis4ops.bean.JiraVersionBean;
import org.jarvis4ops.dao.JiraControlChartDao;
import org.jarvis4ops.helper.JiraControlChartHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class JiraControlChartController {
	private static final Logger log = LoggerFactory.getLogger(JiraControlChartController.class);

	@Autowired
	private JiraControlChartHelper jiraControlChartHelper;
	
	@Autowired
	private JiraControlChartDao jiraControlChartDao;
	
	@RequestMapping(path="/{projectName}/loadControlChartMetrics", method = { RequestMethod.POST })
	public String invokeJiraControlChartApi(@PathVariable String projectName) throws ParseException {

		boolean isProjectNameValid = jiraControlChartHelper.validateProjectName(projectName);
		if (isProjectNameValid) {
			
			Map<Integer, String> projectSwimlaneMap = jiraControlChartHelper.fetchSwimlanesMap(projectName);
			
			JiraVersionBean []jiraVersionBeanArr = jiraControlChartHelper.fetchProjectVersionsFromJira(projectName);
			
			if (null != projectSwimlaneMap && projectSwimlaneMap.size() > 0) {
				for (Map.Entry<Integer, String> entry : projectSwimlaneMap.entrySet())
				{
					jiraVersionBeanArr = jiraControlChartHelper.processControlChartMetricsForVersions(jiraVersionBeanArr, projectName, entry.getValue(), entry.getKey());
				}
				return new Gson().toJson(jiraVersionBeanArr);
			}
			return "WARN: No recently closed release version";
		}
		return "Error: Invalid Project name OR invalid request";
    }

	@RequestMapping(path="/{projectId}/controlChartMetrics/{swimlaneId}", method = { RequestMethod.GET })
	public String processJiraControlChartApi(@PathVariable(value="projectId") String projectId, @PathVariable(value="swimlaneId") Integer swimlaneId) throws ParseException {

		return new Gson().toJson(jiraControlChartDao.fetchProjectMetricsforSwimlane(projectId, swimlaneId));
    }

}
