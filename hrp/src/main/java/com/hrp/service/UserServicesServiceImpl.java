package com.hrp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.UserServicesDao;
import com.hrp.dto.UserServicesRegistration;
import com.hrp.model.Skills;
import com.hrp.model.UserServices;
import com.hrp.model.UserSkills;
@Service("userServicesService")
@Transactional("transactionManager")
public class UserServicesServiceImpl implements UserServicesService{

	@Autowired
	UserServicesDao userServicesDao;
	
	@Autowired
	UserSkillsService userSkillsService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserServices> getActiveUserServicesByUserIdAndRoleId(Long userId,Long roleId) {
		Map<String,Object> properties=new HashMap<String,Object>();
		properties.put("user.id", userId);
		properties.put("role.id",roleId);
		return (List<UserServices>)userServicesDao.getEntitiesByMatchingProperties(UserServices.class, properties);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserServices> getActiveUserServicesByUserIdAndRoleId(Long userId) {
		Map<String,Object> properties=new HashMap<String,Object>();
		properties.put("user.id", userId);
		return (List<UserServices>)userServicesDao.getEntitiesByMatchingProperties(UserServices.class, properties);
	}

	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void userServicesRegistration(UserServicesRegistration userServicesRegistration) {
		
		UserServices userServices=new UserServices();
		userServices.setRole(userServicesRegistration.getRole());
		userServices.setServices(userServicesRegistration.getServices());
		userServices.setUser(userServicesRegistration.getUser());
		userServices.setDeletedYn(false);

		userServicesDao.save(userServices);
		
		UserSkills userSkill=null;
	    
		List<UserSkills> userSkills=new ArrayList<UserSkills>();
		
	    for (Skills skill : userServicesRegistration.getSkills()) {
			userSkill=new UserSkills();
			userSkill.setSkillId(skill.getId());
			userSkill.setUserId(userServicesRegistration.getUser().getId());
			userSkill.setServiceId(userServicesRegistration.getServices().getId());
			userSkills.add(userSkill);
		} 
		userSkillsService.saveUserSkills(userSkills);
		
	}

	@Override
	public Long getRoleIdByUserId(Long userId) {
		
		return userServicesDao.getRoleIdByUserId(userId);
	}
	
}
