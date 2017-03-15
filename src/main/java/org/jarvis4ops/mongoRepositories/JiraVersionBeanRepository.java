/**
 * 
 */
package org.jarvis4ops.mongoRepositories;

import java.util.List;

import org.jarvis4ops.bean.JiraVersionBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raror3
 *
 */
public interface JiraVersionBeanRepository extends MongoRepository<JiraVersionBean, String> {

	public JiraVersionBean findById(int id);
	public List<JiraVersionBean> findByProjectId(String projectId);
	public List<JiraVersionBean> findByReleased(boolean released);
	public List<JiraVersionBean> findByOverdue(boolean overdue);
	public List<JiraVersionBean> findByName(String name);
}
