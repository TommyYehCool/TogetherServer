package com.exfantasy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.Activity;
import com.exfantasy.server.models.ActivityRepository;

@Service
public class ActivityManagerImpl implements ActivityManager {

	@Autowired
	private ActivityRepository activityDao;
	
	@Override
	public void create(Activity activity) {
		activityDao.save(activity);
	}

	@Override
	public void delete(Activity activity) {
		activityDao.delete(activity);
	}

}
