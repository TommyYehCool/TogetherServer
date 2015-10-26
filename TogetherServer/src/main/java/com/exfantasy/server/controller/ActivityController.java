package com.exfantasy.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/activity")
public class ActivityController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	public String create() {
		return "Return from ActivityController create !!";
	}

}