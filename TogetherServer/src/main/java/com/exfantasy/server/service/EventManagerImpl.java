package com.exfantasy.server.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.models.EventRepository;
import com.exfantasy.server.vo.Event;

@Service
public class EventManagerImpl implements EventManager {
	
	private static final Logger logger = LoggerFactory.getLogger(EventManagerImpl.class);

	@Autowired
	private EventRepository eventDao;
	
	@Override
	public String create(EventEntity eventEntity) {
		try {
			logger.info("Prepare to create " + eventEntity);
			
			eventDao.save(eventEntity);

			String succeedMsg = "Create " + eventEntity + " succeed!";

			logger.info(succeedMsg);
			
			return succeedMsg;
		} 
		catch (Exception e) {
			String failedMsg = "Create " + eventEntity + " failed, err-msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}

	@Override
	public Event create(double latitude, double longitude, String name, String content, int attendeeNum, long time) {
		EventEntity eventEntity = new EventEntity(latitude, longitude, name, content, attendeeNum, time);
		try {
			logger.info("Prepare to create " + eventEntity);
			
			eventDao.save(eventEntity);

			logger.info("Create " + eventEntity + " succeed!");
			
			return new Event(latitude, longitude, name, content, attendeeNum, time);
		} 
		catch (Exception e) {
			logger.warn("Create " + eventEntity + " failed, err-msg: <" + e.getMessage() + ">");
			
			return null;
		}
	}

	@Override
	public List<Event> query(double latitude, double longitude) {
		// TODO 查詢附近事件
		return null;
	}

}
