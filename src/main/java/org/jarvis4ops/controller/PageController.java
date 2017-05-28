package org.jarvis4ops.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	private static final Logger log = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private Environment environment;
		
//	@RequestMapping(value="/index")
//	public String index(@RequestParam(value="name", required=false, defaultValue="jarvis4ops") String name, Model model) {
//        model.addAttribute("message", "name");
//        log.info(model.toString());
//        //return "index.html";
//        return "jarvisHome.html";
//    }
	
	@RequestMapping(value="/index")
	public ModelAndView index(@RequestParam(value="name") String name, HttpServletRequest request) {
        ModelAndView result = new ModelAndView();
        result.addObject("purpose", "Jira Control Chart Demp page");
        log.info("name: ", name);
        //result.addObject("message", request.getAttribute("name"));
        result.setViewName("jarvisHome");
        return result;
    }
}
