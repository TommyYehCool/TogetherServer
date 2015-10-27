package com.exfantasy.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.models.User;
import com.exfantasy.server.service.UserManager;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String register(String email, String password, String name) {
		User user = new User(email, password, name);
		try {
			userManager.register(user);
			logger.info("Register " + user + " succeed");
		} catch (Exception ex) {
			logger.warn("Register " + user + " failed, err-msg: <" + ex.getMessage() + ">");
			return ex.getMessage();
		}
		return "User register succeed";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String delete(long id) {
		User user = new User(id);
		userManager.delete(user);
		return "User succesfully deleted!";
	}

	@RequestMapping(value = "/get-by-email", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getByEmail(String email) {
		String userId;
		try {
			User user = userManager.getByEmail(email);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "User not found";
		}
		return "The user id is: " + userId;
	}

}