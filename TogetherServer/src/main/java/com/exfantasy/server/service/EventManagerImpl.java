package com.exfantasy.server.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.models.EventRepository;
import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.models.UserRepository;
import com.exfantasy.server.vo.Event;
import com.exfantasy.server.vo.OpResult;
import com.exfantasy.server.vo.ResultCode;
import com.exfantasy.server.vo.User;

@Service
public class EventManagerImpl implements EventManager {
	
	private static final Logger logger = LoggerFactory.getLogger(EventManagerImpl.class);

	@Autowired
	private EventRepository eventDao;
	
	@Autowired
	private UserRepository userDao;
	
	@Override
	public String create(EventEntity eventEntity) {
		String strEvent = eventEntity.toString();
		try {
			logger.info(">>>>> Prepare to create " + strEvent);
			
			eventDao.save(eventEntity);

			String succeedMsg = "<<<<< Create " + strEvent + " succeed!";

			logger.info(succeedMsg);
			
			return succeedMsg;
		} 
		catch (Exception e) {
			String failedMsg = "<<<<< Create " + strEvent + " failed, err-msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}

	@Override
	public OpResult create(long createUserId, double latitude, double longitude, String name, String content, int attendeeNum, int date, int time) {
		EventEntity eventEntity = new EventEntity(createUserId, latitude, longitude, name, content, attendeeNum, date, time);
		
		String strEvent = eventEntity.toString();
		try {
			logger.info(">>>>> Prepare to create " + strEvent + " by userId: <" + createUserId + ">");
			
			eventEntity.getUserEntitys().add(userDao.findOne(createUserId));
			
			eventDao.save(eventEntity);
			
			logger.info("<<<<< Create " + strEvent + " succeed!");
			
			return new OpResult(ResultCode.SUCCEED);
		} 
		catch (Exception e) {
			String errorMsg = "<<<<< Create " + strEvent + " failed, err-msg: <" + e.getMessage() + ">";
			
			logger.warn(errorMsg);
			
			return new OpResult(ResultCode.CREATE_EVENT_FAILED, errorMsg);
		}
	}

	@Override
	public Event[] query(double latitude, double longitude) {
		Iterable<EventEntity> allEvents = eventDao.findAll();

		List<Event> lstAllEvents = new ArrayList<Event>();
		for (EventEntity eventEntity : allEvents) {
			Iterator<UserEntity> it = eventEntity.getUserEntitys().iterator();
			
			Event event =
					new Event(eventEntity.getEventId(), eventEntity.getCreateUserId(), 
							  eventEntity.getLatitude(), eventEntity.getLongitude(), 
							  eventEntity.getName(), eventEntity.getContent(), eventEntity.getAttendeeNum(), 
							  eventEntity.getDate(), eventEntity.getTime()); 
			
			while (it.hasNext()) {
				UserEntity userEntity = it.next();
				
				User user 
					= new User(userEntity.getUserId(), userEntity.getEmail(), userEntity.getName());
				
				event.addUser(user);
			}
			
			lstAllEvents.add(event);
		}
		return lstAllEvents.toArray(new Event[0]);
	}

}
