package com.exfantasy.server.service;

import com.exfantasy.server.models.MessageEntity;
import com.exfantasy.server.vo.OpResult;

public interface MessageManager {
	public String leaveMessage(MessageEntity messageEntity);

	public OpResult leaveMessage(long eventId, long createUserId, String content);

}
