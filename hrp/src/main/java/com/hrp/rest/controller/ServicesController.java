
package com.hrp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.model.Services;
import com.hrp.service.ServicesService;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/services")
public class ServicesController {

	@Autowired
	ServicesService servicesService;

	@RequestMapping(value="/active",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ServiceStatus getActiveServices() {
		
		ServiceStatus serviceStatus=new ServiceStatus();
		
		List<Services> services=null;
		try {
			
			services=servicesService.getActiveServices();
			if(services!=null&services.size()>0){
				serviceStatus.setResult(services);
				serviceStatus.setMessage("successfully got the list");
				serviceStatus.setStatus("success");
			}else {
				serviceStatus.setStatus("failure");
				serviceStatus.setMessage("no services found");
			}
		} catch (Exception e) {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("faiure");
			e.printStackTrace();
		}
		return serviceStatus;
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET, produces = "application/json")
	public ServiceStatus getAllServices() {
		
		ServiceStatus serviceStatus=new ServiceStatus();
		
		List<Services> services=null;
		try {
			
			services=servicesService.getAllServices();
			if(services!=null&services.size()>0){
				serviceStatus.setResult(services);
				serviceStatus.setMessage("successfully got the list");
				serviceStatus.setStatus("success");
			}else {
				serviceStatus.setStatus("failure");
				serviceStatus.setMessage("no services found");
			}
		} catch (Exception e) {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("faiure");
			e.printStackTrace();
		}
		return serviceStatus;
	}
}
