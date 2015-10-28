package com.exfantasy.server.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/file")
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Value("${store.file.path}")
	private String STORE_FILE_PATH;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String fileUpload(Model model) {
		return "file_upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
		if (!STORE_FILE_PATH.endsWith("/")) {
			STORE_FILE_PATH += "/";
		}
		
		if (!file.isEmpty()) {
			BufferedOutputStream stream = null;
			try {
				String originalFilename = file.getOriginalFilename();
				String extName = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
				
				byte[] bytes = file.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(new File(STORE_FILE_PATH + name + extName)));
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
