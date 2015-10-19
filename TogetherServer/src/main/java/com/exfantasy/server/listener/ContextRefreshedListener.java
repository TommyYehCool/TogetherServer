package com.exfantasy.server.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.exfantasy.server.bean.EventHolderBean;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
    private EventHolderBean eventHolderBean;

//    @Autowired
//    public void setEventHolderBean(EventHolderBean eventHolderBean) {
//        this.eventHolderBean = eventHolderBean;
//    }

	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("Context Refreshed Event Received");
		eventHolderBean.setEventFired(true);
	}
}
