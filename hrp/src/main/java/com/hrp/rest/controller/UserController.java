package com.hrp.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.algorithm.HrpAlgorithm;
import com.hrp.constants.HrpConstants;
import com.hrp.dto.UserRegistrationDTO;
import com.hrp.model.Answer;
import com.hrp.model.Questions;
import com.hrp.model.User;
import com.hrp.service.QuestionsService;
import com.hrp.service.UserService;
import com.hrp.spring.config.HRPConfig;
import com.hrp.util.HrpUtil;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	QuestionsService questionsService;

	@Autowired
	HrpAlgorithm hrpAlgorithm;
	
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
	public  ServiceStatus registerUser(@RequestBody  UserRegistrationDTO userDTO)  {
		ServiceStatus serviceStatus=new ServiceStatus();
			
		String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
			if(userDTO !=null && (userDTO.getUser().getEmail().trim().length() > 0 && userDTO.getUser().getEmail().trim().length() <= 50) && (userDTO.getUser().getEmail().matches(emailRegex)) 
					&& (userDTO.getUserProfile().getFirstName().trim().length() > 0)&& (userDTO.getUserProfile().getLastName().trim().length() > 0)
					&& ( userDTO.getUserProfile().getMobile().trim().length() > 0) && ( userDTO.getUserProfile().getMobile().matches("\\d{10}")))
				{
						try {
										userService.registerUser(userDTO);
										serviceStatus.setStatus("success");	
										serviceStatus.setMessage("User registered successfully.");
								} catch (Exception e) {
										e.printStackTrace();
										serviceStatus.setStatus("failure");	
										serviceStatus.setMessage("failure.");
										
										if(e instanceof ConstraintViolationException)
											serviceStatus.setMessage("Registration failed due to duplicate entry or invalid payload, please provide correct data");
								}
				}else{
									serviceStatus.setStatus("failure");	
									serviceStatus.setMessage("Invalid user registration details.");
				}
		
			return serviceStatus;
	}
   
   @RequestMapping(value ="/{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ServiceStatus getUserById(@PathVariable("id") Long id) {
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(id!=null){

			User user=null;
			try {
				user=userService.getUserById(id);
				if(user!=null){
                  serviceStatus.setResult(user);
                  serviceStatus.setMessage("success");
                  serviceStatus.setStatus("success");
				}else {
					serviceStatus.setMessage("user not found");
					serviceStatus.setStatus("failure");
				}
			} catch (Exception e) {
				serviceStatus.setMessage("failure");
				serviceStatus.setStatus("failure");
				e.printStackTrace();
			}
		}else{
			serviceStatus.setMessage("invalid id");
			serviceStatus.setStatus("failure");
		}
		
		return serviceStatus;
		
   }

   @RequestMapping(value="/is/{email:.+}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
   public ServiceStatus checkIsUserRegister(@PathVariable("email") String email){
	   ServiceStatus serviceStatus=new ServiceStatus();
	   
	   if(HrpUtil.isRegexTrue(HrpConstants.EMAIL_REGEX,email)){
		   Long userId=null;
		   try {
			
			   userId=userService.isUser(email);
			   
			   if(userId!=null){
				   serviceStatus.setMessage("already register");
				   serviceStatus.setStatus("failure");;
			   }else{
				   serviceStatus.setMessage("you can proceed registration with that email not yet registred");
				   serviceStatus.setStatus("success");
			   }
			   
		} catch (Exception e) {
			e.printStackTrace();
			serviceStatus.setMessage("failure");
			   serviceStatus.setStatus("failure");

		}
		   
	   }else{
		   serviceStatus.setMessage("invalid email");
		   serviceStatus.setStatus("failure");
	   }
	   
	   return serviceStatus;
   }
   
   @RequestMapping(value ="/getProviders/{userId}/{serviceId}", method = RequestMethod.GET)
 	public ServiceStatus getProviders(@PathVariable("userId") Long userId, @PathVariable("serviceId") Long serviceId) {
 		ServiceStatus serviceStatus = new ServiceStatus();
 		
 		 System.out.println("Request coming to alogrithm");
 		 
 		 List<User> users= hrpAlgorithm.getProviders(userId,serviceId);
 		 
 		 serviceStatus.setResult(users);
 		 serviceStatus.setMessage("success");
 		 return serviceStatus;
 }
   
}
