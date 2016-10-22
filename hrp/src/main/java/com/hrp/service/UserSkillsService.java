package com.hrp.service;

import java.util.List;

import com.hrp.model.UserSkills;

public interface UserSkillsService {

	void saveUserSkills(UserSkills userSkills);
	
	void saveUserSkills(List<UserSkills> userSkills);
}
