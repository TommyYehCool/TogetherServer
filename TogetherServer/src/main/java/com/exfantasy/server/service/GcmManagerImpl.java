package com.exfantasy.server.service;

import org.springframework.stereotype.Service;

import com.exfantasy.server.util.GcmUtil;

@Service
public class GcmManagerImpl implements GcmManager {

	@Override
	public String sendGcmMsg(String topic, String message) {
		boolean result = GcmUtil.sendGcmMessage(topic, message);
		if (result) {
			return "Send gcm notification succeed";
		}
		else {
			return "Send gcm notification failed";
		}
	}

}
