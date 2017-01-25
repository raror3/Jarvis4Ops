package org.jarvis4ops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
	private static final Logger log = LoggerFactory.getLogger(EnvController.class);

	@Autowired
	private Environment environment;
	
	@RequestMapping(path="/getValuesFromEnv")
	public String getValuesFromEnv() {

		log.info("Slack Service from System: " + System.getenv("slackService"));
		log.info("Slack Service from Environment: " + environment.getProperty("slackService"));
		log.info("Server port from Environment: " + environment.getProperty("server.port"));
		return "200";
    }

}
