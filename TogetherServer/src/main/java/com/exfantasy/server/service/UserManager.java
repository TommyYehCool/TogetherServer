package com.exfantasy.server.service;

import com.exfantasy.server.models.User;
import com.exfantasy.server.vo.OpResult;

public interface UserManager {
	public OpResult register(String email, String password, String name);
	
	public OpResult login(String email, String password);

	public User findByEmail(String email);
}