package com.exfantasy.server.service;

import com.exfantasy.server.models.User;

public interface UserManager {
	public void register(User user);
	
	public void update(User user);
	
	public void delete(long id);

	public User findByEmail(String email);
}