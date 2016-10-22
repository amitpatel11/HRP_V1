package com.hrp.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.model.Answer;
import com.hrp.model.Questions;
import com.hrp.model.User;
import com.hrp.service.QuestionsService;
import com.hrp.service.UserService;
import com.hrp.util.HrpUtil;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuestionsService questionsService;

	@SuppressWarnings("null")
	@RequestMapping(value ="/login/{username:.+}", method = RequestMethod.GET)
	public @ResponseBody ServiceStatus loginUser(@PathVariable("username") String username) {
		ServiceStatus serviceStatus = new ServiceStatus();
		List<Questions> questions=null;
		List<Questions> selectedQuestions=new ArrayList<Questions>();
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			
			if(!HrpUtil.isEmptyString(username)){
               Long userId=userService.isUser(username);
				if(userId!=null){
					questions=questionsService.getTotalNumberOfQuestionsNotDeleted();
							if(questions!=null&questions.size()>0){
								Random randomGenerator=new Random();
								for(Integer i=0;i<=2;i++){
									Integer index = randomGenerator.nextInt(questions.size());
								 selectedQuestions.add( questions.get(index));	
								}
								result.put("questions", selectedQuestions);
								result.put("userId", userId);
								serviceStatus.setMessage("successfully got three random question");
								serviceStatus.setStatus("succes");
								serviceStatus.setResult(result);
							}else {
								serviceStatus.setStatus("failure");
								serviceStatus.setMessage("question not found  ");
							}
				}else {
					serviceStatus.setStatus("failure");
					serviceStatus.setMessage("username not found ");
				}
			}else {
				serviceStatus.setStatus("failure");
				serviceStatus.setMessage("invalid user name");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			serviceStatus.setMessage("failure");
			serviceStatus.setStatus("failure");
		}
		return serviceStatus;
		
	}
	
   @RequestMapping(value="/login",method=RequestMethod.POST)
	public ServiceStatus loginUser(@RequestBody List<Answer> answers  ){
	   ServiceStatus serviceStatus=new ServiceStatus();
	   Boolean validationFlag=false;
	   Boolean loginFlag=false;
		   
		  try {
			  
				
			  if(answers!=null&answers.size()>0){
				  
				  for(Answer answer:answers){
					   if(answer.getQuestions().getId()!=null
							   &answer.getUser().getId()!=null
							   &!HrpUtil.isEmptyString(answer.getAnswer())){
						   validationFlag=true;
						   loginFlag=userService.checkAnswer(answer);
						   if(!loginFlag){
							   break;
							   }
						   continue;
					   }else {
						   validationFlag=false;
						break;
					}
				   }
				  
			  }else{
				  validationFlag=false;
			  }
			  
					  if(validationFlag){
						
						  if(loginFlag){
							  serviceStatus.setStatus("success");
							  serviceStatus.setMessage("successfully logged in ");
						  }else {
							  serviceStatus.setStatus("failure");
							serviceStatus.setMessage("given   answers is invalid");
						}
					   }else{
						   serviceStatus.setMessage("invalid  details ");
						   serviceStatus.setStatus("failure");
					   }
					  
		} catch (Exception e) {
			
			e.printStackTrace();
			 serviceStatus.setMessage("failure");
			   serviceStatus.setStatus("failure");		}
		   
		
	   
	   return serviceStatus;
		
	}
	
   
	
   @RequestMapping(value ="/registerUser", method = RequestMethod.POST,
		   consumes={"application/json"},produces={"application/json"})
	public  ServiceStatus registerUser(@RequestBody  User user)  {
		ServiceStatus serviceStatus=new ServiceStatus();
			
		String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
			if(user !=null && (user.getEmail().trim().length() > 0 && user.getEmail().trim().length() <= 50) && (user.getEmail().matches(emailRegex)) 
					&& (user.getUserProfile().getFirstName().trim().length() > 0)&& (user.getUserProfile().getLastName().trim().length() > 0)
					&& ( user.getUserProfile().getMobile().trim().length() > 0) && ( user.getUserProfile().getMobile().matches("\\d{10}"))){
		
					try {
									userService.registerUser(user);
									serviceStatus.setStatus("success");	
									serviceStatus.setMessage("User registered successfully.");
							} catch (Exception e) {
									e.printStackTrace();
									serviceStatus.setStatus("failure");	
									serviceStatus.setMessage("Registration failed.");
							}
				}else{
								serviceStatus.setStatus("failure");	
								serviceStatus.setMessage("Invalid details.");
				}
		
		return serviceStatus;
	}
   
   @RequestMapping(value ="/getUserById/{id}", method = RequestMethod.GET)
	public @ResponseBody ServiceStatus getUserById(@PathVariable("id") Long id) {
		ServiceStatus serviceStatus = new ServiceStatus();
		User user = userService.getUserById(id);
		serviceStatus.setResult(user);
		serviceStatus.setMessage("success");
		return serviceStatus;
		
   }

}
