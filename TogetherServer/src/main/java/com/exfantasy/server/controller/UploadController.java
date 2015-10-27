package com.exfantasy.server.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	private final String PREFIX_PATH = "D:/Exfantasy/";

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String fileUpload(Model model) {
		return "upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			BufferedOutputStream stream = null;
			try {
				byte[] bytes = file.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(new File(PREFIX_PATH + name + ".jpg")));
	            stream.write(bytes);
	            return "You successfully uploaded " + name + "!";
	        } 
			catch (Exception e) {
	            return "You failed to upload " + name + " => " + e.getMessage();
	        }
			finally {
				try {
					stream.close();
				} 
				catch (IOException e) {
					logger.error("IOException raised while closing IOStream, err-msg: <" + e.getMessage() + ">");
				}
			}
	    } 
		else {
	        return "You failed to upload " + name + " because the file was empty.";
	    }
	}
}
