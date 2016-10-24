package com.hrp.dao;

import java.util.ArrayList;
import java.util.List;

import com.hrp.model.Answer;
import com.hrp.model.User;

public interface UserDao extends Dao {

	public List<User> loginUser(String username);

	public void registerUser(List<Answer> answers);

	public Long isUser(String email);

	Boolean checkAnswer(Answer answer);

	Long getProfileIdByUserId(Long userId);
	
	Long saveUser(User user);

	List<Long> selectProviders(Long serviceId, Long roleId, Integer experience);

	public List<User> selectProvidersBasedOnExperience(Long serviceId, Long roleId, ArrayList<Long> idList);

}
