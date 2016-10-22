package com.hrp.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.model.Skills;
import com.hrp.service.SkillsService;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/skills")
public class SkillsController {

	@Autowired
	SkillsService skillsService;
	
	@RequestMapping(value="/active",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ServiceStatus getActiveSkills() {
		ServiceStatus serviceStatus=new ServiceStatus();
		List<Skills> skills=null;
		
		try {
			
		skills=skillsService.getActiveSkills();
		if(skills!=null&skills.size()>0){
			serviceStatus.setResult(skills);
			serviceStatus.setStatus("success");
			serviceStatus.setMessage("successfully got the list");
			
		}else {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("skills not found");
		}
			
		} catch (Exception e) {
			serviceStatus.setStatus("failure");
			serviceStatus.setMessage("failure");
			e.printStackTrace();
		}
		return serviceStatus;
	}
}
