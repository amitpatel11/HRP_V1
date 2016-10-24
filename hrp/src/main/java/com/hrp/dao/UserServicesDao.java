package com.hrp.dao;

public interface UserServicesDao extends Dao {
	
	Long getRoleIdByUserId(Long userId);
	
}
