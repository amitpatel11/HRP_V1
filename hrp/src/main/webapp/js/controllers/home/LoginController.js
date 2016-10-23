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
    
    $scope.showLoginForm1=function()
    {
     	$scope.loginform1=false;
    	$scope.loginform2=true;
        $scope.loginform3=true;
        $scope.loginform4=true;
        $scope.userNameExist=false;
        $scope.answersMessageDiv=false;
    }
    
    $scope.showLoginForm2=function()
    {
    	
     var email=$scope.userName;
    	
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
        	    $scope.userNameExistMessage="Please enter valid user name."
        	}
        console.log(response.data.result);
      }, function errorCallback(response) {

       });
    	
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
    	
    	var loginData=[
    		{
    			"questions":{
    				
    				"id":$scope.LoginQuestionObj1.id
    			},
    			"user":{
    				
    		        "id":$scope.userID
    			},
    			"answer":$scope.loginAnswer1
    			
    		},
    		{
    			"questions":{
    				
    				"id":$scope.LoginQuestionObj2.id
    			},
    			"user":{
    				
    		        "id":$scope.userID
    			},
    			"answer":$scope.loginAnswer2
    			
    		},
    		{
    			"questions":{
    				
    				"id":$scope.LoginQuestionObj3.id
    			},
    			"user":{
    				
    		        "id":$scope.userID
    			},
    			"answer":$scope.loginAnswer3
    			
    		}
    		
    		]
    	
    	userService.loginUser(loginData).
                then(function successCallback(response) {
                	console.log(response);
                	if(response.data.status === "success")
                	  {
                		//$rootScope.loggedUserId=response.config.data[0].user.id;
                		//$rootScope.loggedUserName=$scope.userName;
                		$cookieStore.put('loggedUserId', response.config.data[0].user.id);
                		$location.path("/dashboard");
                	  }
                	else{
                		$scope.loginform1=false;
                		$scope.loginform2=true;
                	    $scope.loginform3=true; 
                	    $scope.loginform4=true;
                	    $scope.userNameExist=false;
                	    $scope.answersMessageDiv=true;
                	    $scope.answersMessage="Please enter correct answers."
                	}
                	
      }, function errorCallback(response) {

       });
    	
    }
	
});