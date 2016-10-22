package com.hrp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.ServicesDao;
import com.hrp.model.Services;

@Service("servicesService")
@Transactional("transactionManager")
public class ServicesServiceImpl implements ServicesService {

	@Autowired
	ServicesDao servicesDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Services> getActiveServices() {
		
		Map<String,Object> properties=new HashMap<String,Object>();
		properties.put("deletedYn", false);
		return (List<Services>) servicesDao.getEntitiesByMatchingProperties(Services.class, properties);
	
	}

}
