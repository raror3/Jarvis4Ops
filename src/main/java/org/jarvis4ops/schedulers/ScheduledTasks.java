/**
 * 
 */
package org.jarvis4ops.schedulers;

import org.jarvis4ops.configurations.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
	private Configurations configObj;

    //@Scheduled(fixedRate = 43200000)
	public void getPrevDayRockstarsJiraSch() {

		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        String apiUrl = configObj.getHost()+configObj.getPort()+"/getPrevDayJiraRockstars";
        String response = restTemplate.getForObject(apiUrl, String.class);
        //log.info("Response: ", response);
        System.out.println("Response: " + response);
    }

}
