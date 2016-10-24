package com.hrp.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrp.model.User;
import com.hrp.model.UserSkills;
import com.hrp.service.UserService;
import com.hrp.service.UserServicesService;
import com.hrp.service.UserSkillsService;

@Component("hrpAlgorithm")
public class HrpAlgorithm {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSkillsService userSkillsService;
	
	@Autowired
	UserServicesService userServicesService;
	
	public List<User> getProviders(Long userId, Long serviceId){
		
		List<UserSkills>  userSkills= userSkillsService.getUserSkillsServiceById(userId);
		
		System.out.println(" User Skills  size------> "+userSkills.size());
/*		System.out.println(" User Skills ------> "+userSkills);*/
		
		Long roleId=userServicesService.getRoleIdByUserId(userId);
		System.out.println(" Role id -----> "+roleId);

		User user= userService.getUserById(userId);
		
		List<User> users = new ArrayList<User>();
		//users.add(user);
		
		//System.out.println(userService.selectProviders(serviceId, roleId, user.getUserProfile().getExperience())+"fadfsdgsadfgbsdfhshbsb");
		
		users = userService.selectProviders(serviceId, roleId, user.getUserProfile().getExperience());
		
		
		
		
	/*	ArrayList<Long> idList = new ArrayList<Long>();
		
		if(users.size() > 0  && users.size() != 3){
			
			for(User userIds: users){
				idList.add(userIds.getId());
			}
			
			System.out.println(" Id=List -----> "+idList.size());
			System.out.println(" Id=List -----> "+idList);
			
			userService.selectProvidersBasedOnExperience(serviceId, roleId,idList);
		}
		*/
	//	System.out.println(" List of providers ------> "+userService.selectProvidersBasedOnExperience(serviceId, roleId);
		return users;
	}
	
}

