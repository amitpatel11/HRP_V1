package com.hrp.dao;

import java.util.List;

public interface UserSkillsDao extends Dao{
	
	List<Long>  getSkillIdByUserId(Long userId);
	
}
