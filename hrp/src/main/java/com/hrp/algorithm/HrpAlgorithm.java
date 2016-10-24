package com.hrp.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrp.model.User;
import com.hrp.service.UserService;
import com.hrp.service.UserServicesService;
import com.hrp.service.UserSkillsService;
import com.hrp.util.ServiceStatus;

@Component("hrpAlgorithm")
public class HrpAlgorithm {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSkillsService userSkillsService;
	
	@Autowired
	UserServicesService userServicesService;
	
	public ServiceStatus getProviders(Long userId, Long serviceId){
		
		ServiceStatus serviceStatus=new ServiceStatus();
		List<Long> selectedProvidersIds=new ArrayList<Long>();
		List<Long> seekerSkillsIds=userSkillsService.getSkillIdByUserId(userId);
		
		if(seekerSkillsIds!=null&seekerSkillsIds.size()>0){
			User user= userService.getUserById(userId);
			Integer seekerExperience=user.getUserProfile().getExperience();
			
			List<Long> providersIds=null;
			if(seekerExperience!=null){
				providersIds=userService.selectProviders(serviceId, 2l, seekerExperience);
				if(providersIds!=null&providersIds.size()>0){

				        //select providers max 3 
					List<Entry<Long, Integer>> list= matchSkills(providersIds, seekerSkillsIds);
					
				        for (int i = 0; i < list.size(); i++) {
				        	Map.Entry<Long, Integer> entry=list.get(i);
				        	 selectedProvidersIds.add(entry.getKey());
				        	 if(i==2){
				        		 break;
				        	 }
				        		 
						}
				}
				
				//if not found by experience then  
				if(selectedProvidersIds.size()<3){
					
					providersIds=userService.selectProviders(serviceId, 2l, 0);	
					
					if(providersIds!=null){
					
						providersIds.removeAll(selectedProvidersIds);
						
						if(providersIds.size()>0){
							
							List<Entry<Long, Integer>> list= matchSkills(providersIds, seekerSkillsIds);
						
							Integer count =2-selectedProvidersIds.size();
							for (int i = 0; i < list.size(); i++) {
					        	Map.Entry<Long, Integer> entry=list.get(i);
					        	 selectedProvidersIds.add(entry.getKey());
					        	 if(i==count){
					        		 break;
					        	 }
							}
						}
					}	
				}
				
				if (selectedProvidersIds.size()>0) {
					
					serviceStatus.setStatus("success");
					serviceStatus.setMessage("successfully got ");
					serviceStatus.setResult(selectedProvidersIds);
					
				} else {
					serviceStatus.setMessage("no providers found");
					serviceStatus.setStatus("failure");
				}
					
			}else {
				serviceStatus.setMessage("please edit profile and provide the experience");
				serviceStatus.setStatus("failure");
			}
		}else{
			serviceStatus.setMessage("din't get any skills for seeker first choose some skills");
			serviceStatus.setStatus("failure");
		}
	return serviceStatus;
	}
	
	
	
	private List<Entry<Long, Integer>> matchSkills(List<Long> providersIds,List<Long> seekerSkillsIds){
	

		List<Long> providerSkillsIds=null;
		
		Map<Long,Integer> skillCount=new HashMap<Long,Integer>();
		List<Long> tempSeekerSkillsIds=null;
		
		//matching the skills
		for(Long providerId:providersIds){
			providerSkillsIds=userSkillsService.getSkillIdByUserId(providerId);
			tempSeekerSkillsIds=new ArrayList<Long>(seekerSkillsIds);
			tempSeekerSkillsIds.retainAll(providerSkillsIds);
			if(tempSeekerSkillsIds.size()>0){
				skillCount.put(providerId,tempSeekerSkillsIds.size());
			}
			
		}
		
		//sorting the skillCount to select best skills match
		 Set<Entry<Long, Integer>> set = skillCount.entrySet();
	        List<Entry<Long, Integer>> list = new ArrayList<Entry<Long, Integer>>(set);
	        Collections.sort( list, new Comparator<Map.Entry<Long, Integer>>()
	        {
	            public int compare( Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2 )
	            {
	                return (o2.getValue()).compareTo( o1.getValue() );
	            }
	        } );
	   
	      return list;  
	      						
	}
	
}

