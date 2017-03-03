package org.jarvis4ops.controller;

import org.jarvis4ops.bean.UserProfileBean;
import org.jarvis4ops.mongoRepositories.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class UserProfileController {
	private static final Logger log = LoggerFactory.getLogger(UserProfileController.class);

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@RequestMapping("/addUser")
	public String populateUser(@RequestBody String userProfObj) {

		UserProfileBean userProfile = new UserProfileBean();
		Gson gsonObj = new Gson();
		userProfile = gsonObj.fromJson(userProfObj, UserProfileBean.class);
		userProfile = userProfileRepository.save(userProfile);
		return "200";
    }

}
