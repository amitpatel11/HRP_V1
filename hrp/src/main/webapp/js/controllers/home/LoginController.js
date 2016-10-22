/*Created on 20-October-2016
      Author: Nageswara rao
      Copyright (c) 2016 by HR Platform. All Rights Reserved.*/

hrpApp.controller('loginController',function($scope,$location,$http,userService){
	
	$scope.loginform1=false;
	$scope.loginform2=true;
    $scope.loginform3=true; 
    $scope.loginform4=true;
    
    $scope.showLoginForm1=function()
    {
    	$scope.loginform1=false;
    	$scope.loginform2=true;
        $scope.loginform3=true;
        $scope.loginform4=true;
    }
    
    $scope.showLoginForm2=function()
    {
    	$scope.loginform1=true;
    	$scope.loginform2=false;
        $scope.loginform3=true;
        $scope.loginform4=true;
    }
    
    $scope.showLoginForm3=function()
    {
    	$scope.loginform1=true;
    	$scope.loginform2=true;
        $scope.loginform3=false;
        $scope.loginform4=true;
    }
    
    $scope.showLoginForm4=function()
    {
    	$scope.loginform1=true;
    	$scope.loginform2=true;
        $scope.loginform3=true;
        $scope.loginform4=false;
    }
    
 /*   userService.getSecurityQustionForUserLogin()
                  .then(function successCallback(response) {
                	  
       
      }, function errorCallback(response) {
         
      });*/
    
	
});