package com.exfantasy.server.service;

import com.exfantasy.server.models.ActivityEntity;

public interface ActivityManager {
	public void create(ActivityEntity activity);
	
	public void delete(ActivityEntity activity);
}
