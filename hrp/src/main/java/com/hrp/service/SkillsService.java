package com.hrp.service;

import java.util.List;

import com.hrp.model.Skills;

public interface SkillsService {

	List<Skills> getActiveSkills();
	
	List<Skills> getAllSkills();
}
