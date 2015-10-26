package com.exfantasy.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.models.User;
import com.exfantasy.server.service.UserManager;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "/create")
	@ResponseBody
	public String create(String email, String name, int age) {
		User user = new User(email, name, age);
		try {
			userManager.save(user);
			logger.info("Create " + user + " succeed");
		} catch (Exception ex) {
			logger.warn("Create " + user + " failed, err-msg: <" + ex.getMessage() + ">");
			return ex.getMessage();
		}
		return "User succesfully saved!";
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(long id) {
		User user = new User(id);
		userManager.delete(user);
		return "User succesfully deleted!";
	}

	@RequestMapping(value = "/get-by-email")
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