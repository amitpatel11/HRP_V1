package com.hrp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.model.Qualification;
import com.hrp.service.QualificationService;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/qualification")
public class QualificationController {
	
	@Autowired
	QualificationService qualificationService;

	@RequestMapping(value = "/getAllActiveQualifications", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceStatus getAllActiveQualifications() {
		ServiceStatus serviceStatus = new ServiceStatus();
		List<Qualification> qualificationsList = qualificationService.getAllActiveQualifications();
		if (qualificationsList.size() > 0) {
			serviceStatus.setResult(qualificationsList);
			serviceStatus.setStatus("success");
		} else {
			serviceStatus.setStatus("failure");
		}
		return serviceStatus;
	}
	
	@RequestMapping(value = "/getAllQualifications", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceStatus getAllQualifications() {
		ServiceStatus serviceStatus = new ServiceStatus();
		List<Qualification> qualificationsList = qualificationService.getAllQualifications();
		if (qualificationsList.size() > 0) {
			serviceStatus.setResult(qualificationsList);
			serviceStatus.setStatus("success");
		} else {
			serviceStatus.setStatus("failure");
		}
		return serviceStatus;
	}
}
