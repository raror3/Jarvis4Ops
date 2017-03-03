/**
 * 
 */
package org.jarvis4ops.mongoRepositories;

import java.util.List;

import org.jarvis4ops.bean.UserProfileBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author raror3
 *
 */
public interface UserProfileRepository extends MongoRepository<UserProfileBean, String> {

	public UserProfileBean findByJiraUsername(String jiraUsername);
    public UserProfileBean findByMsSlackUsername(String msSlackUsername);
    public UserProfileBean findBySapientSlackUsername(String sapientSlackUsername);
    public UserProfileBean findByBonusLyUsername(String bonusLyUsername);
    public UserProfileBean findByEmail(String email);
    public List<UserProfileBean> findByFirstNameAndLastName(String firstName, String lastName);

}
