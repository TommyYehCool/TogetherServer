package com.exfantasy.server.service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.vo.Event;

public interface EventManager {
	public String create(EventEntity event);

	public Event create(double latitude, double longitude, String name, String content, int attendeeNum, long time);

}
