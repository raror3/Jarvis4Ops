package org.jarvis4ops.dao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.jarvis4ops.bean.JiraSwimlaneBean;
import org.jarvis4ops.bean.JiraVersionBean;
import org.jarvis4ops.mongoRepositories.JiraVersionBeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JiraControlChartDao {

	@Autowired
	private JiraVersionBeanRepository jiraVersionBeanRepository;


	/**
	 * This method accepts the populated object of JiraVersionBean and persists it in Mongo DB.
	 * @param jiraVersionBean
	 * @return JiraVersionBean populated object as received
	 */
	public JiraVersionBean persistMetricsToDatabase(JiraVersionBean jiraVersionBean) {
		jiraVersionBean = jiraVersionBeanRepository.save(jiraVersionBean);
		return jiraVersionBean;
	}

	/**
	 * This method fetches objects for all metrics for provided project and its swimlane.
	 * @param projectId
	 * @param versionBeanList
	 */
	public List<JiraVersionBean> fetchProjectMetricsforSwimlane(String projectId, Integer swimlaneId) {
		List<JiraVersionBean> versionBeanList = jiraVersionBeanRepository.findByProjectId(projectId);
		if (null != versionBeanList && versionBeanList.size()>0) {
			for (Iterator<JiraVersionBean> iterator = versionBeanList.iterator(); iterator.hasNext();) {
			    JiraVersionBean versionBean = iterator.next();
			    for (Iterator<JiraSwimlaneBean> swimlaneIterator = versionBean.getSwimlane().iterator(); swimlaneIterator.hasNext();) {
			    	JiraSwimlaneBean swimlaneBean = swimlaneIterator.next();
			    	if (!swimlaneId.equals(swimlaneBean.getSwimLaneId())) {
				        // Remove the current element from the iterator and the list.
				    	swimlaneIterator.remove();
				    }
			    }
			}
		}
		JiraVersionBean[] versionBeanArr = versionBeanList.toArray(new JiraVersionBean[versionBeanList.size()]);
		Arrays.sort(versionBeanList.toArray());
		versionBeanList = Arrays.asList(versionBeanArr);

		return versionBeanList;
	}

}
