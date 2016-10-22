package com.hrp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.UserSkillsDao;
import com.hrp.model.UserSkills;

@Service("userSkillsService")
@Transactional("transactionManager")
public class UserSkillsServiceImpl implements UserSkillsService {

	@Autowired
	UserSkillsDao userSkillsDao;

	@Override
	public void saveUserSkills(UserSkills userSkills) {
		userSkillsDao.save(userSkills);
	}

	@Override
	public void saveUserSkills(List<UserSkills> userSkills) {
	
		for (UserSkills userSkill : userSkills) {
			userSkill.setDeletedYn(false);
			userSkillsDao.save(userSkill);
		}
		
	}


}
