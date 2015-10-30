package com.exfantasy.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ResourceConfig extends WebMvcConfigurerAdapter {
	
	@Value("${store.file.path}")
	private String STORE_FILE_PATH;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!STORE_FILE_PATH.endsWith("/")) {
			STORE_FILE_PATH += "/";
		}
		registry.addResourceHandler("/photos/**").addResourceLocations("file:" + STORE_FILE_PATH);
	}
}
