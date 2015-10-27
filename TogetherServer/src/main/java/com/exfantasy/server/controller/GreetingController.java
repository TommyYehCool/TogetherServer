package com.exfantasy.server.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.exfantasy.server.vo.Greeting;

@Controller
public class GreetingController {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greetingForm(Model model) {
		model.addAttribute("greeting", new Greeting());
		return "greeting";
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
		model.addAttribute("greeting", greeting);
		return "result";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String fileUpload(Model model) {
		return "upload";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("D:/Exfantasy/"+name+".jpg")));
	            stream.write(bytes);
	            stream.close();
	            return "You successfully uploaded " + name + "!";
	        } catch (Exception e) {
	            return "You failed to upload " + name + " => " + e.getMessage();
	        }
	    } else {
	        return "You failed to upload " + name + " because the file was empty.";
	    }
	}
	
}