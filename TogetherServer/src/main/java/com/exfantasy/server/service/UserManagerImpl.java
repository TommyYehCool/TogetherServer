package com.exfantasy.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.User;
import com.exfantasy.server.models.UserRepository;
import com.exfantasy.server.vo.OpResult;
import com.exfantasy.server.vo.ResultCode;

@Service
public class UserManagerImpl implements UserManager {
	
	private static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);
	
	@Autowired
	private UserRepository userDao;

	@Override
	public OpResult register(String email, String password, String name) {
		logger.info("Processing user register...");
		
		User user = new User(email, password, name);
		try {
			logger.info("Prepare to register " + user);
			
			User existUser = userDao.findByEmail(email);
			if (existUser != null) {
				String errMsg = "Email: <" + email + "> already used";
				return new OpResult(ResultCode.REGISTER_FAEILD_EMAIL_ALREADY_USED, errMsg);
			}
			
			userDao.save(user);

			String succeedMsg = "Register " + user + " succeed";
			logger.info(succeedMsg);

			return new OpResult(ResultCode.SUCCEED);
		} 
		catch (Exception ex) {
			String errMsg = "Register " + user + " failed, err-msg: <" + ex.getMessage() + ">";
			logger.warn(errMsg);
			return new OpResult(ResultCode.SERVER_ERROR, errMsg);
		}
	}

	@Override
	public OpResult login(String email, String password) {
		logger.info("Processing email: <" + email + "> login...");
		
		logger.info("Prepare to find user by email: <" + email + ">");

		User user = userDao.findByEmail(email);
		if (user != null) {
			logger.info("Found " + user);

			String userPassword = user.getPassword();
			if (!password.equals(userPassword)) {
				String errMsg = "Input password not matched";
				logger.warn(errMsg);
				return new OpResult(ResultCode.LOGIN_FAILED_PASSWORD_INVALID, errMsg);
			}
			
			// password matched
			logger.info(user + " login succeed");
			
			return new OpResult(ResultCode.SUCCEED);
		}
		else {
			String errMsg = "Cannot find mapping user by email: <" + email + ">";
			logger.warn(errMsg);
			return new OpResult(ResultCode.LOGIN_FAILED_CANNOT_FIND_USER_BY_EMAIL, errMsg);
		}
	}

	@Override
	public User findByEmail(String email) {
		logger.info("Prepare to find user by email: <" + email + ">");

		User user = userDao.findByEmail(email);
		if (user != null) {
			logger.info("Found: " + user);
		}
		else {
			logger.warn("Not found: " + email);
		}
		return user;
	}
}
