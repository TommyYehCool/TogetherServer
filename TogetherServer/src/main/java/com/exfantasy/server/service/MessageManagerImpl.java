package com.exfantasy.server.service;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.models.EventRepository;
import com.exfantasy.server.models.MessageEntity;
import com.exfantasy.server.models.MessageRepository;
import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.models.UserRepository;
import com.exfantasy.server.util.DateTimeUtil;
import com.exfantasy.server.vo.OpResult;
import com.exfantasy.server.vo.ResultCode;

@Service
public class MessageManagerImpl implements MessageManager {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageManagerImpl.class);
	
	@Autowired
	private MessageRepository msgDao;
	
	@Autowired
	private EventRepository eventDao;
	
	@Autowired
	private UserRepository userDao;

	@Override
	public String leaveMessage(MessageEntity messageEntity) {
		String strMessage = messageEntity.toString();
		try {
			logger.info(">>>>> Prepare to leave " + strMessage);
			
			msgDao.save(messageEntity);

			String succeedMsg = "<<<<< Leave " + strMessage + " succeed!";

			logger.info(succeedMsg);
			
			return succeedMsg;
		} 
		catch (Exception e) {
			String failedMsg = "<<<<< Leave " + strMessage + " failed, err-msg: <" + e.getMessage() + ">";

			logger.warn(failedMsg);
			
			return failedMsg;
		}
	}

	@Override
	public OpResult leaveMessage(long eventId, long createUserId, String content) {
		Calendar cal = Calendar.getInstance();
		int date = DateTimeUtil.dateValue(cal);
		int time = DateTimeUtil.timeValue(cal);

		MessageEntity msgEntity = new MessageEntity(createUserId, content, date, time);
		
		try {
			logger.info(">>>>> Prepare to let userId: <" + createUserId + "> to leave message: <" + content + "> to eventId: <" + eventId + ">");
			
			// ---------- Process Event logic ----------
			EventEntity event = eventDao.findOne(eventId);
			if (event == null) {
				logger.error("<<<<< Cannot find mapping event with eventId: <" + eventId + ">");
				return new OpResult(ResultCode.LEAVE_MSG_FAILED_WITH_EVENT_IS_NULL);
			}
			
			logger.info("Found " + event);
			
			// ---------- Process User logic ----------
			UserEntity user = userDao.findOne(createUserId);
			if (user == null) {
				logger.error("<<<<< Cannot find mapping user with createUserId: <" + createUserId + ">");
				return new OpResult(ResultCode.LEAVE_MSG_FAILED_WITH_USER_IS_NULL);
			}
			
			logger.info("Found " + user);
			
			// ---------- Save Message ----------
			msgDao.save(msgEntity);
			logger.info("Save " + msgEntity + " succeed");
			
			// ---------- Event add Message ----------
			event.getMessageEntitys().add(msgEntity);
			eventDao.save(event);
			
			logger.info("<<<<< UserId: <" + createUserId + "> leave message: <" + content + "> to eventId: <" + eventId + "> succeed");
			
			return new OpResult(ResultCode.SUCCEED);
		}
		catch (Exception e) {
			String errorMsg = "<<<<< UserId: <" + createUserId + "> leave message: <" + content + "> to eventId: <" + eventId + "> failed, err-msg: <" + e.getMessage() + ">";
			
			logger.warn(errorMsg);
			
			return new OpResult(ResultCode.LEAVE_MSG_FAILED_WITH_EXCEPTION);
		}
	}
}
