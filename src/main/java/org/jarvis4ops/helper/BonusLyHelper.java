package org.jarvis4ops.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jarvis4ops.bean.BonusLyResponseBean;
import org.jarvis4ops.bean.BonusLyUserDetailResponseBean;
import org.jarvis4ops.bean.UserProfileBean;
import org.jarvis4ops.configurations.BonusLyConstants;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.configurations.SlackMessagingConstants;
import org.jarvis4ops.mongoRepositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class BonusLyHelper {
	private static final Logger log = LoggerFactory.getLogger(BonusLyHelper.class);

	@Autowired
	private Configurations configObj;

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
	private BonusLyConstants bonusLyConstants;

	@Autowired
	private SlackMessagingConstants slackMessagingConstants;
	
	public String postRewards(String bonusLyJson) {
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(bonusLyJson, headerMap);
        String bonusLyUrl = configObj.getBonusLyEndpointUrl() + configObj.getBonusLyAccessToken();
        //BonusLyResponseBean bonusLyResponse = restTemplate.postForObject(bonusLyUrl, entity, BonusLyResponseBean.class);
        //log.info("Response: " + bonusLyResponse.toString());
        //return bonusLyResponse.toString();
        return "2--";
	}

	public Map<String, String> getRockstarsToBeRewarded(Map<String, String> rockstarJiraIdWithCountMap) {

		Map<String, String> rockstarBonusLyIdAndCountMap = new HashMap<String, String>(1);
		if (null != rockstarJiraIdWithCountMap) {
			for (Map.Entry<String, String> entry : rockstarJiraIdWithCountMap.entrySet())
			{
			    UserProfileBean userProfile = userProfileRepository.findByJiraUsername(entry.getKey());
				if (userProfile != null) {
					rockstarBonusLyIdAndCountMap.put(userProfile.getBonusLyUsername(), entry.getValue());
				}
			}
		}
		return rockstarBonusLyIdAndCountMap;
	}

	public String getEligibleGiverEmail() {
		String giverEmail = null;
		RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>(1);
        headerMap.add("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<String>(headerMap);
        
        for (String giverUserId : configObj.getBonusLyGiverUserIds()) {
        	log.info("Bonusly User API Request: " + configObj.getBonusLyUsersEndpointUrl() + giverUserId + configObj.getBonusLyAccessToken());
        	ResponseEntity<BonusLyUserDetailResponseBean> bonusLyUserDetailResponse = restTemplate.exchange(configObj.getBonusLyUsersEndpointUrl() + giverUserId + configObj.getBonusLyAccessToken(), HttpMethod.GET, entity, BonusLyUserDetailResponseBean.class);
        	if (bonusLyUserDetailResponse.getBody().isSuccess() 
        			&& bonusLyUserDetailResponse.getBody().getResult().getGiving_balance() > 0 
        			&& bonusLyUserDetailResponse.getBody().getResult().isCan_give()) {
        		giverEmail = bonusLyUserDetailResponse.getBody().getResult().getEmail();
        		break;
        	}
        }
		return giverEmail;
	}

	/**
	 * Method to build citation for BonusLy for rockstars resolving good amount of incidents on previous day. 
	 * @param rockstarEntry
	 * @return String
	 */
	public String buildRockstarCitation(Entry<String, String> rockstarEntry) {
		// TODO Auto-generated method stub
		StringBuilder citationSb = new StringBuilder("@");
		citationSb.append(rockstarEntry.getKey());
		citationSb.append(configObj.getEmptySpace());
		citationSb.append(bonusLyConstants.getRockstarRewardCitation().get(0));
		citationSb.append(configObj.getEmptySpace());
		citationSb.append(rockstarEntry.getValue());
		citationSb.append(configObj.getEmptySpace());
		citationSb.append(bonusLyConstants.getRockstarRewardCitation().get(1));
		citationSb.append(configObj.getEmptySpace());
		citationSb.append(slackMessagingConstants.getCfd());
		//String citation = "@"+ rockstarEntry.getKey() + configObj.getEmptySpace() + bonusLyConstants.getRockstarRewardCitation() + configObj.getEmptySpace() + configObj.getCfd());
		return citationSb.toString();
	}
	
}
