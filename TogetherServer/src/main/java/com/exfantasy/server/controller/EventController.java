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

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.service.EventManager;

@Controller
@RequestMapping(value = "/event")
public class EventController {

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private EventManager eventManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String returnEventPage(Model model) {
		model.addAttribute("event", new EventEntity());
		return "event";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String createEvent(@ModelAttribute EventEntity event, Model model) {
		try {
			logger.info("Prepare to create " + event);
			
			eventManager.create(event);
			
			String succeedMsg = "Create " + event + " succeed!";

			logger.info(succeedMsg);
			
			return succeedMsg;
		} 
		catch (Exception e) {
			String failedMsg = "Create " + event + " failed, err-msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}
}
