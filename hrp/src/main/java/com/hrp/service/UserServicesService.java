package com.hrp.service;

import java.util.List;

import com.hrp.dto.UserServicesRegistration;
import com.hrp.model.UserServices;

public interface UserServicesService{

	List<UserServices> getActiveUserServicesByUserIdAndRoleId(Long userId,Long roleId);

	List<UserServices> getActiveUserServicesByUserIdAndRoleId(Long userId);

	void userServicesRegistration(UserServicesRegistration userServicesRegistration);
	
	Long getRoleIdByUserId(Long userId);
}
