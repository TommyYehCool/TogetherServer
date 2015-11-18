package com.exfantasy.server.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.models.EventRepository;
import com.exfantasy.server.models.UserRepository;
import com.exfantasy.server.vo.Event;
import com.exfantasy.server.vo.OpResult;
import com.exfantasy.server.vo.ResultCode;

@Service
public class EventManagerImpl implements EventManager {
	
	private static final Logger logger = LoggerFactory.getLogger(EventManagerImpl.class);

	@Autowired
	private EventRepository eventDao;
	
	@Autowired
	private UserRepository userDao;
	
	@Override
	public String create(EventEntity eventEntity) {
		try {
			logger.info(">>>>> Prepare to create " + eventEntity);
			
			eventDao.save(eventEntity);

			String succeedMsg = "<<<<< Create " + eventEntity + " succeed!";

			logger.info(succeedMsg);
			
			return succeedMsg;
		} 
		catch (Exception e) {
			String failedMsg = "<<<<< Create " + eventEntity + " failed, err-msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}

	@Override
	public OpResult create(long userId, double latitude, double longitude, String name, String content, int attendeeNum, long time) {
		EventEntity eventEntity = new EventEntity(userId, latitude, longitude, name, content, attendeeNum, time);
		try {
			logger.info(">>>>> Prepare to create " + eventEntity + " by userId: <" + userId + ">");
			
			eventEntity.getUserEntitys().add(userDao.findOne(userId));
			
			eventDao.save(eventEntity);
			
			logger.info("<<<<< Create " + eventEntity + " succeed!");
			
			return new OpResult(ResultCode.SUCCEED);
		} 
		catch (Exception e) {
			String errorMsg = "<<<<< Create " + eventEntity + " failed, err-msg: <" + e.getMessage() + ">";
			
			logger.warn(errorMsg);
			
			return new OpResult(ResultCode.CREATE_EVENT_FAILED, errorMsg);
		}
	}

	@Override
	public Event[] query(double latitude, double longitude) {
		Iterable<EventEntity> allEvents = eventDao.findAll();
		List<Event> lstAllEvents = new ArrayList<Event>();
		for (EventEntity eventEntity : allEvents) {
			lstAllEvents.add(
				new Event(eventEntity.getEventId(), eventEntity.getLatitude(), eventEntity.getLongitude(), 
						  eventEntity.getName(), eventEntity.getContent(), eventEntity.getAttendeeNum(), 
						  eventEntity.getTime())
			);
		}
		return lstAllEvents.toArray(new Event[0]);
	}

}
