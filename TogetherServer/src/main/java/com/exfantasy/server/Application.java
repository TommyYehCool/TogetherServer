package com.exfantasy.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.exfantasy.server.bean.EventHolderBean;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		EventHolderBean bean = ctx.getBean(EventHolderBean.class);

		System.out.println("Event processed ?? - " + bean.getEventFired());
		
//		System.out.println(System.getProperty("file.encoding"));
	}

}
