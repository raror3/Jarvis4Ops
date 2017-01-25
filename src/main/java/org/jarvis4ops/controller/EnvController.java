package org.jarvis4ops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
	private static final Logger log = LoggerFactory.getLogger(EnvController.class);

	@RequestMapping(path="/getValuesFromEnv")
	public String getValuesFromEnv() {
		log.info("Slack Service from System: " + System.getenv("slackService"));
		System.out.println("Slack service identified: " + System.getenv("slackService"));

		return "200";
    }

}
