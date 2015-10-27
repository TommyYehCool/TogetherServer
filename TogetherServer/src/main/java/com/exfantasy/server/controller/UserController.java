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
		String succeedMsg = "Register " + user + " succeed";
		try {
			userManager.register(user);
			logger.info(succeedMsg);
		} 
		catch (Exception ex) {
			logger.warn("Register " + user + " failed, err-msg: <" + ex.getMessage() + ">");
			return ex.getMessage();
		}
		return succeedMsg;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String delete(@RequestParam("id") long id) {
		userManager.delete(id);
		return "User succesfully deleted!";
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
			logger.info("Not found");
		}
		return user;
	}
}