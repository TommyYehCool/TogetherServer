package com.exfantasy.server.service;

import java.util.List;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.vo.Event;
import com.exfantasy.server.vo.OpResult;

public interface EventManager {
	public String create(EventEntity event);

	public OpResult create(double latitude, double longitude, String name, String content, int attendeeNum, long time);

	public List<Event> query(double latitude, double longitude);

}
