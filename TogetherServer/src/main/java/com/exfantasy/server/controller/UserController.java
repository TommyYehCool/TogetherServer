package com.exfantasy.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.models.User;
import com.exfantasy.server.service.UserManager;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String userPage(Model model) {
		model.addAttribute("user", new User());
		return "user";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String register(String email, String password, String name) {
		User user = new User(email, password, name);
		try {
			logger.info("Prepare to register " + user);
			
			userManager.register(user);

			String succeedMsg = "Register " + user + " succeed";
			logger.info(succeedMsg);

			return succeedMsg;
		} 
		catch (Exception ex) {
			String failedMsg = "Register " + user + " failed, err-msg: <" + ex.getMessage() + ">";

			logger.warn(failedMsg);

			return failedMsg;
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String delete(@RequestParam("id") long id) {
		try {
			logger.warn("Prepare to delete user with id: <" + id + ">");
			
			userManager.delete(id);
			
			String succeedMsg = "Delete user with id: <" + id + "> succeed";

			logger.info(succeedMsg);
			
			return succeedMsg;
		}
		catch (Exception e) {
			String failedMsg = "Delete user with id: <" + id + "> failed, msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}

	@RequestMapping(value = "/find-by-email", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public User findByEmail(@RequestParam("email") String email) {
		logger.info("Prepare to find user by email: <" + email + ">");

		User user = userManager.findByEmail(email);
		if (user != null) {
			logger.info("Found: " + user);
		}
		else {
			logger.warn("Not found: " + email);
		}
		return user;
	}
}