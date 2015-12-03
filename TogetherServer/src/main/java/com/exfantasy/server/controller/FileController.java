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
	public String handleFileUpload(@RequestParam("email") String email, @RequestParam("uploadfile") MultipartFile file) {
		if (email.isEmpty()) {
			return "Upload failed with empty email";
		}
		
		if (!STORE_FILE_PATH.endsWith("/")) {
			STORE_FILE_PATH += "/";
		}
		String userFolderPath = STORE_FILE_PATH + email + "/";
		
		File folder = new File(userFolderPath);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
		
		if (!file.isEmpty()) {
			BufferedOutputStream stream = null;
			try {
				File fileToStore = new File(userFolderPath + file.getOriginalFilename());
				if (fileToStore.exists()) {
					fileToStore.delete();
				}
				
				byte[] bytes = file.getBytes();
				stream = new BufferedOutputStream(new FileOutputStream(fileToStore));
	            stream.write(bytes);
	            return "Upload succeed!";
	        } 
			catch (Exception e) {
	            return "Upload failed with exception, msg: " + e.toString();
	        }
			finally {
				if (stream != null) {
					try {
						stream.close();
					} 
					catch (IOException e) {
						logger.error("IOException raised while closing IOStream, err-msg: <" + e.getMessage() + ">");
					}
				}
			}
	    } 
		else {
	        return "Upload failed due to empty file";
	    }
	}
}
