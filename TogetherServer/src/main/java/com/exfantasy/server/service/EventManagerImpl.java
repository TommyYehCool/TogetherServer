package com.exfantasy.server.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.models.EventRepository;
import com.exfantasy.server.models.MessageEntity;
import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.models.UserRepository;
import com.exfantasy.server.vo.Event;
import com.exfantasy.server.vo.Message;
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
			Event event =
					new Event(eventEntity.getEventId(), eventEntity.getCreateUserId(), 
							  eventEntity.getLatitude(), eventEntity.getLongitude(), 
							  eventEntity.getName(), eventEntity.getContent(), eventEntity.getAttendeeNum(), 
							  eventEntity.getDate(), eventEntity.getTime()); 

			// 加入參加者
			Iterator<UserEntity> itUser = eventEntity.getUserEntitys().iterator();
			while (itUser.hasNext()) {
				UserEntity userEntity = itUser.next();
				
				User user 
					= new User(userEntity.getUserId(), userEntity.getEmail(), userEntity.getName());
				
				event.addUser(user);
			}
			
			// 加入留言
			Iterator<MessageEntity> itMsg = eventEntity.getMessageEntitys().iterator();
			while (itMsg.hasNext()) {
				MessageEntity msgEntity = itMsg.next();
				
				Message msg
					= new Message(msgEntity.getMessageId(), msgEntity.getCreateUserId(), msgEntity.getContent(), msgEntity.getDate(), msgEntity.getTime());
				
				event.addMessage(msg);
			}
			
			lstAllEvents.add(event);
		}
		return lstAllEvents.toArray(new Event[0]);
	}

	@Override
	public OpResult join(long joinUserId, long eventId) {
		try {
			logger.info(">>>>> Prepare to let userId: <" + joinUserId + "> join event with eventId: <" + eventId + ">");
			
			// ---------- Process event logic ----------
			EventEntity event = eventDao.findOne(eventId);
			
			if (event == null) {
				logger.error("<<<<< Cannot find mapping event with eventId: <" + eventId + ">");
				return new OpResult(ResultCode.JOIN_EVENT_FAILED_WITH_EVENT_IS_NULL);
			}

			logger.info("Found " + event);
			
			long createUserId = event.getCreateUserId();
			if (createUserId == joinUserId) {
				logger.error("<<<<< Cannot join event with createUserId: <" + createUserId + "> equals joinUserId: <" + joinUserId + ">");
				return new OpResult(ResultCode.JOIN_EVENT_FAILED_WITH_JOIN_USER_CREATED);
			}
			
			Set<UserEntity> joinedUsers = event.getUserEntitys();
			for (UserEntity joinedUser : joinedUsers) {
				if (joinedUser.getUserId() == joinUserId) {
					logger.error("<<<<< Cannot join event that joinUserId: <" + joinUserId + "> already joined");
					return new OpResult(ResultCode.JOIN_EVENT_FAILED_WITH_ALREADY_JOINED);
				}
			}
			
			// ---------- Process user logic ----------
			UserEntity user = userDao.findOne(joinUserId);
			if (user == null) {
				logger.error("<<<<< Cannot find mapping user with userId: <" + joinUserId + ">");
				return new OpResult(ResultCode.JOIN_EVENT_FAILED_WITH_USER_IS_NULL);
			}

			logger.info("Found " + user);
			
			// ---------- Event add User ----------
			event.getUserEntitys().add(user);
			eventDao.save(event);
			
			logger.info("<<<<< UserId: <" + joinUserId + "> join event with eventId: <" + eventId + "> succeed");
			
			return new OpResult(ResultCode.SUCCEED);
		} 
		catch (Exception e) {
			String errorMsg = "<<<<< UserId: <" + joinUserId + "> join event with eventId: <" + eventId + "> failed, err-msg: <" + e.getMessage() + ">";
			
			logger.warn(errorMsg);
			
			return new OpResult(ResultCode.JOIN_EVENT_FAILED_WITH_EXCEPTION, errorMsg);
		}
	}

}
