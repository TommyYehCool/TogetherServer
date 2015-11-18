package com.exfantasy.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exfantasy.server.models.UserEntity;
import com.exfantasy.server.models.UserRepository;
import com.exfantasy.server.vo.LoginResult;
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
		
		UserEntity user = new UserEntity(email, password, name);
		try {
			logger.info("Prepare to register " + user);
			
			UserEntity existUser = userDao.findByEmail(email);
			if (existUser != null) {
				String errMsg = "Email: <" + email + "> already used";
				return new OpResult(ResultCode.REGISTER_FAILED_EMAIL_ALREADY_USED, errMsg);
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
	public LoginResult login(String email, String password) {
		logger.info("Processing email: <" + email + "> login...");
		
		logger.info("Prepare to find user by email: <" + email + ">");

		UserEntity user = userDao.findByEmail(email);
		if (user != null) {
			logger.info("Found " + user);

			String userPassword = user.getPassword();
			if (!password.equals(userPassword)) {
				String errMsg = "Input password not matched";
				logger.warn(errMsg);
				return new LoginResult(ResultCode.LOGIN_FAILED_PASSWORD_INVALID, "Enter password not matched");
			}
			
			// password matched
			logger.info(user + " login succeed");
			
			return new LoginResult(ResultCode.SUCCEED, user.getUserId(), user.getName(), user.getEmail(), "http://xxx.xxx.xxx.xxx/xxx.jpg");
		}
		else {
			String errMsg = "Cannot find mapping user by email: <" + email + ">";
			logger.warn(errMsg);
			return new LoginResult(ResultCode.LOGIN_FAILED_CANNOT_FIND_USER_BY_EMAIL, errMsg);
		}
	}

	@Override
	public UserEntity findByEmail(String email) {
		logger.info("Prepare to find user by email: <" + email + ">");

		UserEntity user = userDao.findByEmail(email);
		if (user != null) {
			logger.info("Found: " + user);
		}
		else {
			logger.warn("Not found: " + email);
		}
		return user;
	}
}
