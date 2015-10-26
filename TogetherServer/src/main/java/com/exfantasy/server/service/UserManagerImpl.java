package com.exfantasy.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.dao.UserDao;
import com.exfantasy.server.models.User;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
	private UserDao userDao;

	@Override
	public void register(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public User getByEmail(String email) {
		return userDao.getByEmail(email);
	}

	@Override
	public User getById(long id) {
		return userDao.getById(id);
	}
	
}
