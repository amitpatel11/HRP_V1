package com.hrp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.model.Services;
import com.hrp.model.Skills;
import com.hrp.service.UtilService;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/util")
public class UtilController {

	@Autowired
	UtilService utilService;

	@RequestMapping(value = "/getAllActiveServices", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceStatus getAllActiveServices() {
		ServiceStatus serviceStatus = new ServiceStatus();
		List<Services> servicesList = utilService.getAllActiveServices();
		if (servicesList.size() > 0) {
			serviceStatus.setResult(servicesList);
			serviceStatus.setMessage("success");
		} else {
			serviceStatus.setStatus("Failed");
		}
		return serviceStatus;
	}

	@RequestMapping(value = "/getAllActiveSkills", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceStatus getAllActiveSkills() {
		ServiceStatus serviceStatus = new ServiceStatus();
		List<Skills> skillsList = utilService.getAllActiveSkills();
		if (skillsList.size() > 0) {
			serviceStatus.setResult(skillsList);
			serviceStatus.setMessage("success");
		} else {
			serviceStatus.setStatus("Failed");
		}
		return serviceStatus;
	}
}
