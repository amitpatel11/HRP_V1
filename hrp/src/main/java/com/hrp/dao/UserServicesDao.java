package com.hrp.dao;

import java.util.List;

import com.hrp.model.UserServices;

public interface UserServicesDao extends Dao {
	List<UserServices> getAllActiveServicesByUserId(Long userId);
}
