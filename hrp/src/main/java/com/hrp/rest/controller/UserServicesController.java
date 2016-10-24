package com.hrp.rest.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/active/{userId}/{roleId}", method = RequestMethod.GET, produces = "application/json")
	public  ServiceStatus getUserServicesById(@PathVariable("userId") Long userId,@PathVariable("roleId") Long roleId) {
		ServiceStatus serviceStatus = new ServiceStatus();
		if(userId!=null&roleId!=null){
			List<UserServices> userServices=null;
			try {
				
				userServices=userServicesService.getActiveUserServicesByUserIdAndRoleId(userId,roleId);
				if(userServices!=null&userServices.size()>0){
					serviceStatus.setResult(userServices);
					serviceStatus.setStatus("success");
					serviceStatus.setMessage("successfully got the user services");
				}else {
					serviceStatus.setMessage("no services found");
					serviceStatus.setStatus("failure");
				}
				
			} catch (Exception e) {
				serviceStatus.setStatus("failure");
				serviceStatus.setMessage("failure");
			}
			
		}else {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("userid or roleId invalid");
		}
		return serviceStatus;
	}

	@RequestMapping(value = "/active/{userId}", method = RequestMethod.GET, produces = "application/json")
	public  ServiceStatus getUserServicesById(@PathVariable("userId") Long userId) {
		ServiceStatus serviceStatus = new ServiceStatus();
		if(userId!=null){
			List<UserServices> userServices=null;
			try {
				
				userServices=userServicesService.getActiveUserServicesByUserIdAndRoleId(userId);
				if(userServices!=null&userServices.size()>0){
					serviceStatus.setResult(userServices);
					serviceStatus.setStatus("success");
					serviceStatus.setMessage("successfully got the user services");
				}else {
					serviceStatus.setMessage("no services found");
					serviceStatus.setStatus("failure");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				serviceStatus.setStatus("failure");
				serviceStatus.setMessage("failure");
			}
			
		}else {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("userid invalid");
		}
		return serviceStatus;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ServiceStatus userServicesRegistration(@RequestBody UserServicesRegistration userServicesRegistration) {

		ServiceStatus serviceStatus = new ServiceStatus();

		if (userServicesRegistration.getUser() != null
				& userServicesRegistration.getRole() != null
				& userServicesRegistration.getServices() != null
				& userServicesRegistration.getUser().getId() != null
			    & userServicesRegistration.getServices().getId() != null
				& userServicesRegistration.getRole().getId() != null & userServicesRegistration.getSkills() != null
				& userServicesRegistration.getSkills().size() > 0) {
			if (validateSkills(userServicesRegistration.getSkills())) {
			
				try {
				
					userServicesService.userServicesRegistration(userServicesRegistration);
					serviceStatus.setMessage("successfully register user with services ");
				    serviceStatus.setStatus("success");
				  
				} 
				
				catch (Exception e) {
					serviceStatus.setMessage("failure");
					if(e instanceof ConstraintViolationException)
						serviceStatus.setMessage("duplicate entry or invalid payload, please provide correct data");
                   
					 e.printStackTrace();
					serviceStatus.setStatus("failure");
				}
				

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
