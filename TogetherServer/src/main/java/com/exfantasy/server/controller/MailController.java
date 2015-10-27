package com.exfantasy.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/mail")
public class MailController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	@ResponseBody
	public String sendMail() {
		logger.info(">>>>>>> In MailController");
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("bensn11@gmail.com");
		mail.setSubject("This is a test from SpringBoot");
		mail.setText("Fuck you benson");
		mailSender.send(mail);
		return "Send mail succeed";
	}
}
