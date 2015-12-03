package com.exfantasy.server.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.service.UserManager;

@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {
	
	@Value("${store.file.path}")
	private String STORE_FILE_PATH;
	
	@Autowired
	private UserManager userManager;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!STORE_FILE_PATH.endsWith("/")) {
			STORE_FILE_PATH += "/";
		}
		
		UserEntity[] allUsers = userManager.findAllUsers();
		
		for (UserEntity user : allUsers) {
			String userEmail = user.getEmail();
			
			// FIXME 這邊再看看要不要用個亂碼取代 userEmail, 免得被破解
			// FIXME 還有要考慮新註冊的要怎麼加 ResourceHandlerRegistry
			String userFolderPath = STORE_FILE_PATH + userEmail + "/";
			File userFolder = new File(userFolderPath);
			if (!userFolder.isDirectory()) {
				userFolder.mkdirs();
			}
			registry.addResourceHandler("/" + userEmail + "/**").addResourceLocations("file:" + userFolderPath);
		}
	}
}
