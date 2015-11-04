package com.exfantasy.server.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/jQueryTest")
public class JQueryTestController {

	@RequestMapping(method = RequestMethod.GET)
	public String greetingForm() {
		return "jQueryTest";
	}

	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
	@ResponseBody
	public String greetingSubmit(HttpServletRequest request) {
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = (String) parameterNames.nextElement();
			String value = (String) request.getParameter(key);
			System.out.println("key = " + key + ", value = " + value);
		}
		return "success";
	}

}