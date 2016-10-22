package com.hrp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.SkillsDao;
import com.hrp.model.Skills;

@Service("skillsService")
@Transactional("transactionManager")
public class SkillsServiceImpl implements SkillsService {

	
	@Autowired
	SkillsDao skillsDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Skills> getSkills() {
		Map<String,Object> properties=new HashMap<String,Object>();
		properties.put("deletedYn", false);
		return (List<Skills>) skillsDao.getEntitiesByMatchingProperties(Skills.class, properties);
		
	}
}
