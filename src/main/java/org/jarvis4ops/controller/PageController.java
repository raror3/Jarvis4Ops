package org.jarvis4ops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	private static final Logger log = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private Environment environment;
		
	@RequestMapping(value="/index")
	public String index(Model model) {
        model.addAttribute("message", "HELLO");
        return "index.html";
    }
}
