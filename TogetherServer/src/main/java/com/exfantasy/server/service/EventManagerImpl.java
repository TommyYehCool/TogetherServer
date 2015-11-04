package com.exfantasy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.EventEntity;
import com.exfantasy.server.models.EventRepository;

@Service
public class EventManagerImpl implements EventManager {

	@Autowired
	private EventRepository eventDao;
	
	@Override
	public void create(EventEntity event) {
		eventDao.save(event);
	}

}
