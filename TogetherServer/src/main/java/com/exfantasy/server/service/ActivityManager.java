package com.exfantasy.server.service;

import com.exfantasy.server.models.Activity;

public interface ActivityManager {
	public void create(Activity activity);
	
	public void delete(Activity activity);
}
