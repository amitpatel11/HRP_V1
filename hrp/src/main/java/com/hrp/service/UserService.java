package com.hrp.service;

import java.util.List;

import com.hrp.model.Answer;
import com.hrp.model.User;

public interface UserService {

	public List<User> loginUser(String username);

	public void registerUser(User user);

	public Long isUser(String email);

	Boolean checkAnswer(Answer answer);
	
	User getUserById(Long id);
    
}
