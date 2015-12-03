package com.exfantasy.server.service;

import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.vo.LoginResult;
import com.exfantasy.server.vo.OpResult;

public interface UserManager {
	public OpResult register(String email, String password, String name);
	
	public LoginResult login(String email, String password);

	public UserEntity findByEmail(String email);
	
	public UserEntity[] findAllUsers();
}