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
import com.exfantasy.server.vo.Event;
import com.exfantasy.server.vo.OpResult;

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
	
	@RequestMapping(value = "/create-from-web", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String createEvent(@ModelAttribute EventEntity event, Model model) {
		return eventManager.create(event);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public OpResult createEvent(double latitude, double longitude, String name, String content, int attendeeNum, long time) {
		return eventManager.create(latitude, longitude, name, content, attendeeNum, time);
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Event[] queryEvents(double latitude, double longitude) {
		return eventManager.query(latitude, longitude);
	}
}
