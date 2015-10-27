package com.exfantasy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.User;
import com.exfantasy.server.models.UserRepository;

@Service
public class UserManagerImpl implements UserManager {
	
	@Autowired
	private UserRepository userDao;

	@Override
	public void register(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.save(user);
	}

	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
}
