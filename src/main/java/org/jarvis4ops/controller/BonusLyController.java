/**
 * 
 */
package org.jarvis4ops.controller;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.jarvis4ops.bean.BonusLyBean;
import org.jarvis4ops.configurations.BonusLyConstants;
import org.jarvis4ops.configurations.Configurations;
import org.jarvis4ops.helper.BonusLyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author raror3
 *
 */
@RestController
public class BonusLyController {
	private static final Logger log = LoggerFactory.getLogger(BonusLyController.class);

	@Autowired
	private Configurations configObj;
	
	@Autowired
	private BonusLyHelper bonusLyHelper;

	@Autowired
	private BonusLyConstants bonusLyConstants;

	@RequestMapping(value = "/rewardRockstars", method = { RequestMethod.POST })
	public String buildBonusLyMessageForRockstars(@RequestBody String rockstarsWithCountFixed) {

		Gson gson = new Gson();
		Type newType = new TypeToken<HashMap<String, String>>(){}.getType();
		HashMap<String,String> map = new Gson().fromJson(rockstarsWithCountFixed, newType);
		
		Map<String, String> rockstarBonusLyIdAndCountMap = bonusLyHelper.getRockstarsToBeRewarded(map);
		
		for (Map.Entry<String, String> rockstarEntry : rockstarBonusLyIdAndCountMap.entrySet()) {
			final BonusLyBean bonusLyBean = new BonusLyBean();
			final String giverEmail = bonusLyHelper.getEligibleGiverEmail();
			if (StringUtils.isEmpty(giverEmail)) {
				log.info("All giver emails are out of giver points : Please contact IT Support");
			} else {
				bonusLyBean.setGiver_email(giverEmail);
				String citation = bonusLyHelper.buildRockstarCitation(rockstarEntry);
				//bonusLyBean.setReason("@"+ rockstarEntry.getKey() + configObj.getEmptySpace() + bonusLyConstants.getRockstarRewardCitation() + configObj.getEmptySpace() + configObj.getCfd());
				bonusLyBean.setReason(citation);
				log.info("BonusLy Json Value: " + gson.toJson(bonusLyBean));
				bonusLyHelper.postRewards(gson.toJson(bonusLyBean));
			}
		}
		return "200";
	}

}
