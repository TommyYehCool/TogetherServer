package com.exfantasy.server.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Value("${store.file.path}")
	private String STORE_FILE_PATH;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public String returnFileUploadPage(Model model) {
		return "file_upload";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String fileUpload(@RequestParam("email") String email, @RequestParam("uploadfile") MultipartFile file) {
		logger.info(">>>>> Processing email: <" + email + "> upload file...");
		
		if (email.isEmpty()) {
			logger.warn("Email: <" + email + "> is empty, cannot upload file");
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
	            
	            logger.info("<<<<< Email: <" + email + "> upload file succeed");
	            
	            return "Upload succeed!";
	        } 
			catch (Exception e) {
				logger.info("<<<<< Email: <" + email + "> upload file failed with exception, msg: " + e.toString());
				
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
			logger.warn("<<<<< Email: <" + email + "> upload empty file");
			
	        return "Upload failed due to empty file";
	    }
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	@ResponseBody
	public byte[] fileDownload(@RequestParam("email") String email, @RequestParam("file-name") String fileName) {
		logger.info(">>>>> Processing Email: <" + email + ">, download file-name: <" + fileName + ">");
		
		if (!STORE_FILE_PATH.endsWith("/")) {
			STORE_FILE_PATH += "/";
		}
		String userFolderPath = STORE_FILE_PATH + email + "/";
		
		String requestFilePath = userFolderPath + fileName;
		File requestFile = new File(requestFilePath);
		if (!requestFile.isFile()) {
			logger.warn("<<<<< Email: <" + email + "> file-name: <" + fileName + "> with path: <" + requestFilePath + "> is not existed");
			return null;
		}
		
		byte[] fileBytes = null;
		Path path = Paths.get(requestFilePath);
		try {
			fileBytes = Files.readAllBytes(path);
			
			logger.warn("<<<<< Email: <" + email + "> file-name: <" + fileName + "> with path: <" + requestFilePath + "> transfer to byte array succeed, length: <" + fileBytes.length + ">");
		} 
		catch (IOException e) {
			logger.warn("<<<<< Email: <" + email + "> file-name: <" + fileName + "> with path: <" + requestFilePath + "> failed with IOException, msg: <" + e.toString() + ">", e);
			return null;
		}
		
		return fileBytes;
	}
}
