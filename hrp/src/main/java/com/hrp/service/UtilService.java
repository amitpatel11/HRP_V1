package com.hrp.service;

import java.util.List;

import com.hrp.model.Services;
import com.hrp.model.Skills;

public interface UtilService {

	List<Services> getAllActiveServices();
	
	List<Skills> getAllActiveSkills();
}
