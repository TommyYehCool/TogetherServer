package com.exfantasy.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.models.ActivityEntity;
import com.exfantasy.server.service.ActivityManager;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	private ActivityManager activityManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String activityPage(Model model) {
		model.addAttribute("activity", new ActivityEntity());
		return "activity";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String createActivity(@ModelAttribute ActivityEntity activity, Model model) {
		try {
			logger.info("Prepare to create " + activity);
			
			activityManager.create(activity);
			
			String succeedMsg = "Create " + activity + " succeed!";

			logger.info(succeedMsg);
			
			return succeedMsg;
		} 
		catch (Exception e) {
			String failedMsg = "Create " + activity + " failed, err-msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}
}
