/**
 * 
 */
package org.jarvis4ops.schedulers;

import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.controller.JiraController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	private static final Logger log = LoggerFactory.getLogger(JiraController.class);

    @Autowired
	private Configurations configObj;
    
    @Autowired
	private Environment environment;

    @Scheduled(fixedRate = 86400000)
	public void getPrevDayRockstarsJiraSch() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getHost()+environment.getProperty("server.port")+"/getPrevDayJiraRockstars";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response: ", response);
    }

    @Scheduled(fixedRate = 7200000)
	public void checkOpenScIssuesAndPost() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getHost()+environment.getProperty("server.port")+"/checkOpenScIssuesAndPost";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response: ", response);
    }

    @Scheduled(fixedRate = 7200000)
	public void getOpenIncidents() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getHost()+environment.getProperty("server.port")+"/openIncidents";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response: ", response);
    }

    @Scheduled(fixedRate = 86400000)
	public void getDorDodJiraForShopC() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getHost()+environment.getProperty("server.port")+"/getDorDodJira";
        String response = restTemplate.getForObject(apiUrl, String.class);
        log.info("Response: ", response);
    }

}
