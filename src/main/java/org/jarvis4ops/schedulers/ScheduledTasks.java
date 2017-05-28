/**
 * 
 */
package org.jarvis4ops.schedulers;

import org.jarvis4ops.helper.ApiHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author raror3
 *
 */
@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
   
    @Autowired
    private ApiHelper apiHelper;

    /**
     * This method schedules and invokes API to validate previous day rockstars from JIRA and post an update on Slack.
     * Scheduled method to execute at:
     * 0th second, 45th minute of 10th hour on
     * everyday of week
     * in IST Time-zone 
     */
    //@Scheduled(cron="0 45 10 * * *", zone="IST")
	public void getPrevDayRockstarsJiraSch() {
    	apiHelper.invoKeApiService("/getPrevDayJiraRockstars");
    }

    /**
     * This method schedules and invokes API to validate if there's any open SC issue in JIRA and post an update on Slack.
     * Scheduled method to execute at:
     * 0th second, 10th minute of 9th hour, 14th hour, 18th hour, 22nd hour on
     * everyday of week
     * in IST time-zone. 
     */
    //@Scheduled(cron="0 10 9,14,18,22 * * *", zone="IST")
	public void checkOpenScIssuesAndPost() {
    	apiHelper.invoKeApiService("/checkOpenScIssuesAndPost");
    }

    /**
     * This method schedules and invokes API to validate for open incidents from JIRA and post an update on Slack according to business logic.
     * Scheduled method to execute at:
     * 0th second, 10th minute of 10th hour, 16th hour, 21st hour on
     * every Monday - Friday
     * in IST time-zone 
     */
    //@Scheduled(cron="0 10 10,16,21 * * MON-FRI", zone="IST")
	public void getOpenIncidents() {
    	apiHelper.invoKeApiService("/openIncidents");
    }

    /**
     * This method schedules and invokes API to check for issues completing DOR from JIRA and post an update on Slack.
     * Scheduled method to execute at:
     * 0th second, 10th minute of 10th hour on
     * every Monday - Friday
     * in IST time-zone 
     */
    //@Scheduled(cron="0 10 10 * * MON-FRI", zone="IST")
	public void getDorDodJiraForShopC() {

		apiHelper.invoKeApiService("/Shopc/dor");
    }

    /**
     * This method schedules and invokes API to check for WIP limits from JIRA and post an update on Slack.
     * Scheduled method to execute at:
     * 0th second, 10th minute of 9th hour, 15th hour, 20th hour on
     * every Monday - Friday
     * in IST timezone 
     */
    //@Scheduled(cron="0 10 9,15,20 * * MON-FRI", zone="IST")
    public void validateJiraWipLimitAndAlert() {
    	apiHelper.invoKeApiService("/validateWipLimitsAndAlert");
    }

}
