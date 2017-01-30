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

    @Scheduled(fixedRate = 86400000, initialDelay = 7200000)
	public void getPrevDayRockstarsJiraSch() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/getPrevDayJiraRockstars";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task to check for Previous Day Rockstars: ", response);
    }

    @Scheduled(fixedRate = 10800000, initialDelay = 10800000)
	public void checkOpenScIssuesAndPost() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/checkOpenScIssuesAndPost";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task to check for Open SC issues: ", response);
    }

    @Scheduled(fixedRate = 14400000, initialDelay = 14400000)
	public void getOpenIncidents() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/openIncidents";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task for DOR status SHOPC: ", response);
    }

    @Scheduled(fixedRate = 86400000, initialDelay = 7200000)
	public void getDorDodJiraForShopC() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/getDorDodJira";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for DOR status for Scheduled Task for SHOPC: ", response);
    }

    @Scheduled(fixedRate = 86400000, initialDelay = 7200000)
	public void validateJiraWipLimitAndAlert() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getSchedulerHostUrl()+"/validateWipLimitsAndAlert";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response for Scheduled Task for WIP Limit: ", response);
    }

}
