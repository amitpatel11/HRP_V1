package com.hrp.dao;

import java.util.List;

import com.hrp.model.Services;
import com.hrp.model.Skills;

public interface UtilDao extends Dao {

	List<Services> getAllActiveServices();

	List<Skills> getAllActiveSkills();
}
