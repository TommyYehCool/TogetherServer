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

import com.exfantasy.server.models.Activity;
import com.exfantasy.server.service.ActivityManager;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	private ActivityManager activityManager;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		model.addAttribute("activity", new Activity());
		return "activity";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String create(@ModelAttribute Activity activity, Model model) {
		return "你成功建立活動, " + activity;
	}

}