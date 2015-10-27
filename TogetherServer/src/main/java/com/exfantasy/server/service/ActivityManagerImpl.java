package com.exfantasy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.dao.ActivityDao;

@Service
public class ActivityManagerImpl implements ActivityManager {
	
	@Autowired
	private ActivityDao activityDao;
}
