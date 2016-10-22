package com.hrp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.dto.UserServicesRegistration;
import com.hrp.model.Skills;
import com.hrp.model.UserServices;
import com.hrp.service.UserServicesService;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/userServices")
public class UserServicesController {

	@Autowired
	UserServicesService userServicesService;

	@RequestMapping(value = "/getUserServicesById/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ServiceStatus getUserServicesById(@PathVariable("userId") Long userId) {
		ServiceStatus serviceStatus = new ServiceStatus();
		List<UserServices> userServicesList = userServicesService.getAllActiveServicesByUserId(userId);
		if (userServicesList.size() > 0) {
			serviceStatus.setResult(userServicesList);
			serviceStatus.setMessage("success");
		} else {
			serviceStatus.setStatus("Failed");
		}
		return serviceStatus;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceStatus userServicesRegistration(@RequestBody UserServicesRegistration userServicesRegistration) {

		ServiceStatus serviceStatus = new ServiceStatus();

		if (userServicesRegistration.getUser() != null & userServicesRegistration.getRole() != null
				& userServicesRegistration.getServices() != null & userServicesRegistration.getUser().getId() != null
				& userServicesRegistration.getServices().getId() != null
				& userServicesRegistration.getRole().getId() != null & userServicesRegistration.getSkills() != null
				& userServicesRegistration.getSkills().size() > 0) {
			if (validateSkills(userServicesRegistration.getSkills())) {

				userServicesService.userServicesRegistration(userServicesRegistration);

			} else {
				serviceStatus.setMessage("invalid skills id");
				serviceStatus.setStatus("failure");
			}

		} else {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("invalid service registration details");
		}

		return serviceStatus;

	}

	private Boolean validateSkills(List<Skills> skills) {

		Boolean flag = false;
		for (Skills skill : skills) {
			if (skill.getId() != null) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

}
