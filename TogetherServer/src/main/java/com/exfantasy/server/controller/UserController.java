package com.exfantasy.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.service.UserManager;
import com.exfantasy.server.vo.LoginResult;
import com.exfantasy.server.vo.OpResult;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserManager userManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String userPage(Model model) {
		model.addAttribute("user", new UserEntity());
		return "user";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public OpResult register(String email, String password, String name) {
		return userManager.register(email, password, name);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public LoginResult login(String email, String password) {
		return userManager.login(email, password);
	}
	
	@RequestMapping(value = "/find-by-email", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public UserEntity findByEmail(@RequestParam("email") String email) {
		return userManager.findByEmail(email);
	}
}