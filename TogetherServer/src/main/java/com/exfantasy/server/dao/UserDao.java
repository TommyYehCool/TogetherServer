package com.exfantasy.server.dao;

import java.util.List;

import com.exfantasy.server.models.User;

public interface UserDao {
	public void save(User user);

	public void update(User user);
	
	public void delete(User user);
	
	public List<User> getAll();
	
	public User getByEmail(String email);
	
	public User getById(long id);
	
}