 /*Created on 20-October-2016
      Author: Nageswara rao
      Copyright (c) 2016 by HR Platform. All Rights Reserved.*/ 

hrpApp.controller('registrationController',function($scope,$location,$http,userService){
	
	$scope.registerform1=false;
	$scope.registerform2=true;
    $scope.registerform3=true;
    
    $scope.showRegisterForm1=function()
    {
    	$scope.registerform1=false;
    	$scope.registerform2=true;
        $scope.registerform3=true;
    }
    
    $scope.showRegisterForm2=function()
    {
    	$scope.registerform1=true; 
    	$scope.registerform2=false;
        $scope.registerform3=true;
    }
    
    $scope.showRegisterForm3=function()
    {
    	$scope.registerform1=true;
    	$scope.registerform2=true;
        $scope.registerform3=false;
    }
    
    $scope.registerUser=function()
    {
    	//send data to the method
    	
    	userService.registerUser()
        .then(function successCallback(response) {
      	  

                  }, function errorCallback(response) {

                 });
          }
    
	
});