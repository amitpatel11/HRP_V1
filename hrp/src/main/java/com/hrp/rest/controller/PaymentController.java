package com.hrp.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hrp.model.Payment;
import com.hrp.service.PaymentService;
import com.hrp.util.ServiceStatus;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@RequestMapping(value ="/{userId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ServiceStatus getPayment(@PathVariable("userId") Long userId) {
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(userId > 0){
			
			Payment payment=null;
			try {
						payment=paymentService.getPayment(userId);
				
						if(payment!=null){
							serviceStatus.setResult(payment);
							serviceStatus.setMessage("Payment details fetched successfully.");
							serviceStatus.setStatus("success");
						}else {
							serviceStatus.setMessage("Payment details not available for this user.");
							serviceStatus.setStatus("failure");
						}
				} catch (Exception e) {
					serviceStatus.setMessage("Exception occured while fetching data.");
					serviceStatus.setStatus("failure");
					e.printStackTrace();
				}
		}else{
				serviceStatus.setMessage("invalid user id.");
				serviceStatus.setStatus("failure");
		}
		
		return serviceStatus;
		
   }
	
	@RequestMapping(value ="/{userId}/{serviceId}/{roleId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ServiceStatus getPayment(@PathVariable("userId") Long userId,@PathVariable("serviceId") Long serviceId, @PathVariable("roleId") Long roleId) {
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(userId > 0 && serviceId > 0 && roleId >0){
			
			Payment payment=null;
			try {
						payment=paymentService.getPayment(userId, serviceId, roleId);
				
						if(payment!=null){
							serviceStatus.setResult(payment);
							serviceStatus.setMessage("Payment details fetched successfully.");
							serviceStatus.setStatus("success");
						}else {
							serviceStatus.setMessage("Payment details not available for this user.");
							serviceStatus.setStatus("failure");
						}
				} catch (Exception e) {
					serviceStatus.setMessage("Exception occured while fetching data.");
					serviceStatus.setStatus("failure");
					e.printStackTrace();
				}
		}else{
				serviceStatus.setMessage("invalid details.");
				serviceStatus.setStatus("failure");
		}
		
		return serviceStatus;
		
   }
	
	@RequestMapping(value ="/{userId}/{roleId}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ServiceStatus getTotalAmount(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(userId > 0 && roleId >0){
			
			double amount= 0.0;
			try {
						amount =paymentService.getTotalAmount(userId,roleId);
				
						if(amount > 0){
							serviceStatus.setResult(amount);
							serviceStatus.setMessage("Amount fetched successfully.");
							serviceStatus.setStatus("success");
						}else {
							serviceStatus.setMessage("Amount not available for this user.");
							serviceStatus.setStatus("failure");
						}
				} catch (Exception e) {
					serviceStatus.setMessage("Exception occured while fetching data.");
					serviceStatus.setStatus("failure");
					e.printStackTrace();
				}
		}else{
				serviceStatus.setMessage("invalid details.");
				serviceStatus.setStatus("failure");
		}
		
		return serviceStatus;
   }
	
	
	@RequestMapping(value ="/{userId}/{serviceId}/{roleId}/{amount}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public  @ResponseBody ServiceStatus savePayment(@PathVariable("userId") Long userId,@PathVariable("serviceId") Long serviceId,
				  @PathVariable("roleId") Long roleId, @PathVariable("amount") double amount ) {
		ServiceStatus serviceStatus = new ServiceStatus();
		
		if(userId > 0 && serviceId > 0 && roleId > 0 && amount > 0){
		
		}
		
		return serviceStatus;
		
   }

}
