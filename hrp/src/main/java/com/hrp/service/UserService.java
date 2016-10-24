package com.hrp.service;

import java.util.ArrayList;
import java.util.List;

import com.hrp.dto.UserRegistrationDTO;
import com.hrp.model.Answer;
import com.hrp.model.User;

public interface UserService {

	public List<User> loginUser(String username);

	public void registerUser(UserRegistrationDTO user);

	public Long isUser(String email);

	Boolean checkAnswer(Answer answer);

	User getUserById(Long id);

	List<Long> selectProviders(Long serviceId, Long roleId, Integer experience);

	List<User> selectProvidersBasedOnExperience(Long serviceId, Long roleId, ArrayList<Long> idList);

}
