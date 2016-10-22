package com.hrp.service;

import java.util.List;

import com.hrp.dto.UserServicesRegistration;
import com.hrp.model.UserServices;

public interface UserServicesService{

	List<UserServices> getActiveUserServicesByUserId(Long userId);

	void userServicesRegistration(UserServicesRegistration userServicesRegistration);
}
