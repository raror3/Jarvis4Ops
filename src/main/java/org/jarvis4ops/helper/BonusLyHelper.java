package org.jarvis4ops.helper;

import java.util.List;
import java.util.Set;

import org.jarvis4ops.configurations.Configurations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BonusLyHelper {
	private static final Logger log = LoggerFactory.getLogger(BonusLyHelper.class);

	@Autowired
	private Configurations configObj;

	public String postRewards(String bonusLyJson) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getRockstarsToBeRewarded(Set<String> keySet) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
