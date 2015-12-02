package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.service.GcmManager;

@Controller
@RequestMapping(value = "/gcm")
public class GcmController {
	
	@Autowired
	private GcmManager gcmManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String returnGcmPage(Model model) {
		return "gcm";
	}
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public String sendGcmMsg(String topic, String message) {
		return gcmManager.sendGcmMsg(topic, message);
	}
}
