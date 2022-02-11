package com.jove.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jove.demo.model.User;
import com.jove.demo.persistence.UserRepository;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRep;
	
	public User validateLogin(User user) {
		User returnValue = userRep.findByUserName(user.getUserName());
		if(returnValue != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			//logger.debug(encoder.encode("abc123"));
			if (encoder.matches(user.getPassword(), returnValue.getPassword())) {
				logger.debug("userName:"+ user.getUserName());
				logger.debug("password:"+ user.getPassword());
				logger.debug("password:"+ returnValue.getPassword());
				
				returnValue.setPassword("");
				//TODO generate token
				returnValue.setToken("Logined");
				return returnValue;
			} 
		} 
		return user;
	}
}
