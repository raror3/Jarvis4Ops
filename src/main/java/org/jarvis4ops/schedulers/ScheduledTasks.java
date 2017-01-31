/**
 * 
 */
package org.jarvis4ops.schedulers;

import org.jarvis4ops.configurations.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author raror3
 *
 */
@Component
public class ScheduledTasks {
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
	private Configurations configObj;

    /**
     * Scheduled method to execute at:
     * 0th second, 45th minute of 10th hour on
     * everyday of week
     * in IST timezone 
     */
    @Scheduled(cron="0 45 10 * * *", zone="IST")
	public void getPrevDayRockstarsJiraSch() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/getPrevDayJiraRockstars";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task to check for Previous Day Rockstars: ", response);
    }

    /**
     * Scheduled method to execute at:
     * 0th second, 10th minute of 9th hour, 14th hour, 18th hour, 22nd hour on
     * everyday of week
     * in IST timezone 
     */
    @Scheduled(cron="0 10 9,14,18,22 * * *", zone="IST")
	public void checkOpenScIssuesAndPost() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/checkOpenScIssuesAndPost";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task to check for Open SC issues: ", response);
    }

    /**
     * Scheduled method to execute at:
     * 0th second, 10th minute of 10th hour, 16th hour, 21st hour on
     * every Monday - Friday
     * in IST timezone 
     */
    @Scheduled(cron="0 10 10,16,21 * * MON-FRI", zone="IST")
	public void getOpenIncidents() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/openIncidents";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task for DOR status SHOPC: ", response);
    }

    /**
     * Scheduled method to execute at:
     * 0th second, 10th minute of 10th hour on
     * every Monday - Friday
     * in IST timezone 
     */
    @Scheduled(cron="0 10 10 * * MON-FRI", zone="IST")
	public void getDorDodJiraForShopC() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/getDorDodJira";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for DOR status for Scheduled Task for SHOPC: ", response);
    }

    //@Scheduled(fixedRate = 86400000, initialDelay = 7200000)
    /**
     * Scheduled method to execute at:
     * 0th second, 10th minute of 9th hour, 15th hour, 20th hour on
     * every Monday - Friday
     * in IST timezone 
     */
    @Scheduled(cron="0 10 9,15,20 * * MON-FRI", zone="IST")
    public void validateJiraWipLimitAndAlert() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/validateWipLimitsAndAlert";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task for WIP Limit: ", response);
    }

}
