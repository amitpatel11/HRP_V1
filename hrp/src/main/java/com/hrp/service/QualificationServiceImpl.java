package com.hrp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.QualificationDao;
import com.hrp.model.Qualification;

@Service("qualificationService")
@Transactional("transactionManager")
public class QualificationServiceImpl implements QualificationService{

	@Autowired
	QualificationDao qualificationDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Qualification> getAllActiveQualifications() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("deletedYn", false);
		return (List<Qualification>)qualificationDao.getEntitiesByMatchingProperties(Qualification.class, properties);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Qualification> getAllQualifications() {
		return (List<Qualification>)qualificationDao.getAll(Qualification.class) ;
	}

}
