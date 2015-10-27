package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/sendmail")
public class MailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String sendMail() {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("tommy.yeh1112@gmail.com");
		mail.setSubject("Test from SpringBoot");
		mail.setText("This is a test from SpringBoot");
		mailSender.send(mail);
		return "Send mail succeed";
	}
}
