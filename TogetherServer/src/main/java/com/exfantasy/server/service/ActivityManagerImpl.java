package com.exfantasy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.ActivityEntity;
import com.exfantasy.server.models.ActivityRepository;

@Service
public class ActivityManagerImpl implements ActivityManager {

	@Autowired
	private ActivityRepository activityDao;
	
	@Override
	public void create(ActivityEntity activity) {
		activityDao.save(activity);
	}

	@Override
	public void delete(ActivityEntity activity) {
		activityDao.delete(activity);
	}

}
