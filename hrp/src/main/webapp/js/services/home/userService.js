 /*Created on 20-October-2016
      Author: Nageswara rao
      Copyright (c) 2016 by HR Platform. All Rights Reserved.*/ 

hrpApp.service("userService", function($http) {

	this.getSecurityQustionForUserLogin = function(email) {
		return $http.get('user/login/'+email+"/");
	};
	this.getAllQuestionForRegistration = function() {
		return $http.get('questions/getAllQuestions');
	};
	
	this.registerUser = function(user){
		return	$http({
			method : 'POST',
			url : 'user/registerUser',
			data : user,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
        	});
	    };
		
		this.loginUser = function(loginData){
			return	$http({
				method : 'POST',
				url : 'user/login',
				data : loginData,
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
	        	});
		};
		
}); 
