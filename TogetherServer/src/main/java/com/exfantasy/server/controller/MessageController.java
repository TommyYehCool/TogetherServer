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

import com.exfantasy.server.models.MessageEntity;
import com.exfantasy.server.service.MessageManager;
import com.exfantasy.server.vo.OpResult;

@Controller
@RequestMapping(value = "/message")
public class MessageController {
	
	@Autowired
	private MessageManager msgManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String returnMsgPage(Model model) {
		model.addAttribute("message", new MessageEntity());
		return "message";
	}
	
	@RequestMapping(value = "/leave-from-web", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String leaveMessage(@ModelAttribute MessageEntity msg, Model model) {
		return msgManager.leaveMessage(msg);
	}
	
	@RequestMapping(value = "/leave-message", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public OpResult leaveMessage(long eventId, long createUserId, String content) {
		return msgManager.leaveMessage(eventId, createUserId, content);
	}
}
