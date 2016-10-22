package com.hrp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.UtilDao;
import com.hrp.model.Services;
import com.hrp.model.Skills;
@Service("utilService")
@Transactional("transactionManager")
public class UtilServiceImpl implements UtilService {

	@Autowired
	UtilDao utilDao;
	
	@Override
	public List<Services> getAllActiveServices() {
		return utilDao.getAllActiveServices();
	}

	@Override
	public List<Skills> getAllActiveSkills() {
		return utilDao.getAllActiveSkills();
	}

}
