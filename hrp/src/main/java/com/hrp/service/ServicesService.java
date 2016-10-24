package com.hrp.service;

import java.util.List;

import com.hrp.model.Services;

public interface ServicesService  {

	List<Services> getActiveServices();
	
	List<Services> getAllServices();
}
