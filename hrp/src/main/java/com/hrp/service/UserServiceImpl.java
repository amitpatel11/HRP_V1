package com.hrp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.UserDao;
import com.hrp.model.Answer;
import com.hrp.model.User;

@Service("userService")
@Transactional("transactionManager")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	 public List<User> loginUser(String username){
		return userDao.loginUser(username);
	}
 
	 public void registerUser(User user){
		 userDao.registerUser(user);
	 }

	@Override
	public Long isUser(String email) {
		return userDao.isUser(email);
	}

	@Override
	public Boolean checkAnswer(Answer answer) {
		
		
		return userDao.checkAnswer(answer) ;
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

}
