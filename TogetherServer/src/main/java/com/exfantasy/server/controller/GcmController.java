package com.exfantasy.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.util.GcmUtil;

@Controller
@RequestMapping(value = "/gcm")
public class GcmController {
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public String sendGcm() {
		boolean sendResult = GcmUtil.sendGcmMessage("global", "It's time to go home!!");
		if (sendResult) {
			return "Send GCM notification message succeed";
		}
		else {
			return "Send GCM notification message failed";
		}
	}
}
