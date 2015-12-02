package com.exfantasy.server.service;

public interface GcmManager {
	public String sendGcmMsg(String topic, String message);
}
