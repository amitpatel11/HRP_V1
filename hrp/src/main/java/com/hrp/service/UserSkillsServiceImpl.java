package com.hrp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserSkills> getUserSkillsServiceById(Long userId) {

		Map<String, Object> properties=new HashMap<String,Object>();
		properties.put("userId",userId);
		return (List<UserSkills>) userSkillsDao.getEntitiesByMatchingProperties(UserSkills.class, properties);
	}

	@Override
	public List<Long> getSkillIdByUserId(Long userId) {
		
		return userSkillsDao.getSkillIdByUserId(userId);
	}

}
