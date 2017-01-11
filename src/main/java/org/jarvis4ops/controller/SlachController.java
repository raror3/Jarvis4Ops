/**
 * 
 */
package org.jarvis4ops.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.jarvis4ops.configurations.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author raror3
 *
 */
@RestController
public class SlachController {
	//private static final Logger log = LoggerFactory.getLogger(SlachController.class);

	@Autowired
	private Configurations configObj;

	@RequestMapping(value = "/postOnSlack", method = { RequestMethod.POST })
	
	public String buildSlackMessage(@RequestBody String rockstarsWithCountFixed) {
		
		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> map = new Gson().fromJson(rockstarsWithCountFixed, newType);
		
		String rockstars = getRockstarNames(map.keySet());
		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(rockstars + " " + configObj.getGreatJobTitleMsg() + configObj.getIncidentsResolvedMsg());
		
		if (null != map.keySet() && map.keySet().size() == 1) {
			slachBean.setFooter(configObj.getCfd());
		} else if (null != map.keySet() && map.keySet().size() > 1) {
			slachBean.setFooter(configObj.getCfd() + " " + configObj.getTeamWork());
		}

		slachBean.setImage_url(getImageUrl(map.keySet().size()));
		slachBean.setText(configObj.getIncidentsResolvedMsg());
		slachBean.setTitle(rockstars + " " + configObj.getGreatJobTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		
		//log.info("Json Value: ", gson.toJson(attachments));
		System.out.println("Json Value: " + gson.toJson(slachAttachments));
		postOnSlack(gson.toJson(slachAttachments));
		//postOnSlack(attachments);
		return "200";
	}	

	private String getRockstarNames(Set<String> rockStarJiraIds) {
		Map<String, String> rockstarMap = new HashMap<String, String>(10);
		rockstarMap.put("agupta", "Akash");
		rockstarMap.put("htomar", "Himanshu");
		rockstarMap.put("hmisra", "Hari Om");
		rockstarMap.put("pkumar1", "Prabhat");
		rockstarMap.put("dkhandelwal", "Divyansh");
		rockstarMap.put("rarora", "Raghav");
		rockstarMap.put("bkaur", "Bikran");

		StringBuilder builder = new StringBuilder();
		rockStarJiraIds.forEach(rockStar->{
			if(builder.length()==0) {
				builder.append(rockstarMap.get(rockStar));
			} else {
				builder.append(", " + rockstarMap.get(rockStar));
			}
		});

		return builder.toString();
	}

	private String getImageUrl(int rockstars) {
		// Random randomNumber = new Random();
		// int memeListKey = randomNumber.nextInt(9 - 0 + 1) + 0;
		return configObj.getIncidentRockstarMemeList().get(rockstars-1);
	}

	@RequestMapping("/directPostOnSlack")
	public String buildAdhocSlachMessage(@RequestParam(value="name", defaultValue="team") String name) {
		SlachBean slachBean = new SlachBean();
		slachBean.setFallback(name + configObj.getGreatJobTitleMsg() + configObj.getIncidentsResolvedMsg());
		slachBean.setFooter(configObj.getCfd());
		
		Random randomNumber = new Random();
		int memeListKey = randomNumber.nextInt(0 - 0) + 0;
		slachBean.setImage_url(configObj.getIncidentRockstarMemeList().get(memeListKey));
		slachBean.setText(configObj.getIncidentsResolvedMsg());
		slachBean.setTitle(configObj.getGreatJobTitleMsg());
		SlachAttachments slachAttachments = new SlachAttachments();
		List<SlachBean> slachBeanList = new ArrayList<SlachBean>(1);
		slachBeanList.add(slachBean);
		slachAttachments.setAttachments(slachBeanList);
		Gson gson = new Gson();
		//log.info("Json Value: ", gson.toJson(attachments));
		System.out.println("Json Value: " + gson.toJson(slachAttachments));
		postOnSlack(gson.toJson(slachAttachments));
		//postOnSlack(attachments);
		return "200";
	}

	public void postOnSlack(String requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(requestBody, headerMap);
        String slashUrl = configObj.getSlackService()+configObj.getSlackApiKey();
        String response = restTemplate.postForObject(slashUrl, entity, String.class);
        //log.info("Response: ", response);
        System.out.println("Response: " + response);
    }
}
