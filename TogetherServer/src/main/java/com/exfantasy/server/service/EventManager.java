package com.exfantasy.server.service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.vo.Event;
import com.exfantasy.server.vo.OpResult;

public interface EventManager {
	public String create(EventEntity event);

	public OpResult create(long userId, double latitude, double longitude, String name, String content, int attendeeNum, int date, int time);

	public Event[] query(double latitude, double longitude);

}
