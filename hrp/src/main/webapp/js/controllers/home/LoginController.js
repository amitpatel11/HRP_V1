/*Created on 20-October-2016
      Author: Nageswara rao
      Copyright (c) 2016 by HR Platform. All Rights Reserved.*/

hrpApp.controller('loginController',function($scope,$location,$http,userService,$rootScope,$cookieStore){
	
	$scope.loginform1=false;
	$scope.loginform2=true;
    $scope.loginform3=true; 
    $scope.loginform4=true;
    $scope.userNameExist=false;
    $scope.answersMessageDiv=false;
    $scope.userNameFieldValidation=false;
    
    $scope.regexPatterns = {
			emailRegex :  /^[A-Za-z]+[A-Za-z0-9._]+@[A-Za-z]+\.[A-Za-z.]{2,5}$/
		};
    
    $scope.showLoginForm1=function()
    {
     	$scope.loginAnswer1="";
    	$scope.loginAnswer2="";
    	$scope.loginAnswer3="";
     	$scope.loginform1=false;
    	$scope.loginform2=true;
        $scope.loginform3=true;
        $scope.loginform4=true;
        $scope.userNameExist=false;
        $scope.answersMessageDiv=false;
   
    }
    
    $scope.showLoginFormBack2=function()
    {
    	$scope.loginform1=true;
		$scope.loginform2=false;
	    $scope.loginform3=true; 
	    $scope.loginform4=true;
	    $scope.userNameExist=false;
	    $scope.answersMessageDiv=false;
    }
    
    $scope.showLoginForm2=function()
    {
    	
     var email=$scope.userName;
     if ((email != undefined) && (email != null) && (email != "") && $scope.regexPatterns.emailRegex.test(email)) {
    	userService.getSecurityQustionForUserLogin(email)
        .then(function successCallback(response) {
        	
        	console.log(response.data.status=="succes")
        	
        	if(response.data.status=="succes")
        		{
        		$scope.userID=response.data.result.userId;
        		$scope.LoginQuestions=response.data.result.questions;
        		var i=1;
        		
            	angular.forEach(response.data.result.questions, function(value, key) {
               		$scope["LoginQuestionObj"+i]=value;
               		i=i+1;
               		});
            	$scope.loginform1=true;
        		$scope.loginform2=false;
        	    $scope.loginform3=true; 
        	    $scope.loginform4=true;
        	    $scope.userNameExist=false;
        	    $scope.answersMessageDiv=false;
        		}
        	else{
        		$scope.loginform1=false;
        		$scope.loginform2=true;
        	    $scope.loginform3=true; 
        	    $scope.loginform4=true;
        	    $scope.userNameExist=true;
        	    $scope.answersMessageDiv=false;
        	    $scope.userNameFieldValidation=false;
        	    $scope.userNameExistMessage="Please enter registered email id"
        	}
        console.log(response.data.result);
      }, function errorCallback(response) {

       });
     }
     else{
    	 $scope.userNameFieldValidationMessage="Please enter email id";
    	 $scope.userNameFieldValidation=true;
     }
    	
    }
    
    $scope.showLoginForm3=function()
    {
    	
    	$scope.loginform1=true;
    	$scope.loginform2=true;
        $scope.loginform3=false;
        $scope.loginform4=true;
        $scope.userNameExist=false;
        $scope.answersMessageDiv=false;
    }
    
    $scope.showLoginForm4=function()
    {
    	$scope.loginform1=true;
    	$scope.loginform2=true;
        $scope.loginform3=true;
        $scope.loginform4=false;
        $scope.userNameExist=false;
        $scope.answersMessageDiv=false;
    }
    
    $scope.loginUser=function(){
    	
    	var answer1=$scope.loginAnswer1;
    	var answer2=$scope.loginAnswer2;
    	var answer3=$scope.loginAnswer3;
    	if($scope.loginFormValidation(answer1,answer2,answer3)){
    	var loginData=[
    		{
    			"questions":{
    				"id":$scope.LoginQuestionObj1.id
    			},
    			"user":{
    		        "id":$scope.userID
    			},
    			"answer":answer1
    		},
    		{
    			"questions":{
    				"id":$scope.LoginQuestionObj2.id
    			},
    			"user":{
    		        "id":$scope.userID
    			},
    			"answer":answer2
    		},
    		{
    			"questions":{
    				"id":$scope.LoginQuestionObj3.id
    			},
    			"user":{
    		        "id":$scope.userID
    			},
    			"answer":answer3
    		}
    		]
    	
    	userService.loginUser(loginData).
                then(function successCallback(response) {
                	console.log(response);
                	if(response.data.status === "success")
                	  {
                		$cookieStore.put('loggedUserId', response.config.data[0].user.id);
                		$location.path("/dashboard");
                	  }
                	else{
                		 $scope.showLoginForm1();
                	    $scope.answersMessageDiv=true;
                	    $scope.answersMessage="Please enter correct answers."
                	}
                	
      }, function errorCallback(response) {

       });
    	   }	
    };
    
    $scope.loginFormValidation=function(answer1,answer2,answer3)
    {
			if ((answer1 == undefined) || (answer1 == null)
					|| (answer1 == "")) {
			$scope.showLoginFormBack2();
				return false;
			} 
			if ((answer2 == undefined) || (answer2 == null)
					|| (answer2 == "")) {
				$scope.showLoginForm3();
				return false;
			} 
			if ((answer3 == undefined) || (answer3 == null)
					|| (answer3 == "")) {
				$scope.showLoginForm4();
				return false;
			} 
			else{
				return true;
			}
    };
    
	
});