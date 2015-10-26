package com.exfantasy.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Activity {

	@RequestMapping(value = "/activity", method = RequestMethod.POST)
	public String activitySubmit() {
		return "This is activity controller";
	}

}