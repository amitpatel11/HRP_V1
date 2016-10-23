hrpApp.controller('dashboardController',function($scope,$location,$http,$uibModal,dashboardService,$rootScope,$resource,$window,$cookieStore){

	$scope.userId=$cookieStore.get('loggedUserId');
	
	 dashboardService.getUserId($scope.userId) 
	  .then(function successCallback(response) {
     	 $scope.userName=response.data.result.email;
     	  $scope.groupNames=response.data.result;
         }, function errorCallback(response) {

        });
		 
	 $scope.getAllActiveServices=function(){
		  dashboardService.getAllActiveService() 
		  .then(function successCallback(response) {
	      	$scope.serviceNames=response.data.result;
              }, function errorCallback(response) {

             });
		  $scope.closeRoleModal = function() {
				try{
					$scope.modalInstance.close();
				} catch (e) {
				
				}
			};
	   };
		  
		  $scope.subscribeService=function(serviceID)
		  {
			  $scope.subscribableServiceId=serviceID;
			  
			  $scope.modalInstance = $uibModal
				.open({
					templateUrl : 'SubscriptionModal.html',
					animation : true,
					keyboard : false,
					size : 'md',
					scope : $scope
				});
			  
		  };
		  
	       $scope.registerForService=function(userrole)
	           {
	    	  $scope.role=userrole;
	    	   if(userrole!=null && userrole!="" && userrole!=undefined){
	    	   $scope.closeRoleModal();
	    	   $scope.serviceRegistrationModalInstance = $uibModal
				.open({
					templateUrl : 'ServiceRegistrationModal.html',
					animation : true,
					keyboard : false,
					size : 'md',
					scope : $scope
				});
	    	   }
	    	   else{
	    		   
	    	   }
		
	           };
	       $scope.closeServiceRegistrationModal = function() {
				try{
					$scope.serviceRegistrationModalInstance.close();
				} catch (e) {
				
				}
			};
	       
	       $scope.getAllActiveSkills=function()
	       {
	    	   $scope.userSkills=[];
	    	   dashboardService.getAllActiveSkills() 
	 		  .then(function successCallback(response) {
	 	      	  $scope.skills=response.data.result;
	               }, function errorCallback(response) {

	              });
	    	   
	       }
	     
	       $scope.userSkills=[];
	   	$scope.changeSkil = function(skill){
			if(skill.value)	{
				$scope.userSkills.push({id : skill.id})
					return;
			}
			else	{
				$scope.indexOfSkillFunction(skill, $scope.userSkills)
				$scope.userSkills.splice($scope.indexOfSkill, 1);
				return;
			}
		}
		
		$scope.indexOfSkillFunction = function(skil,addedSkills){
			var i = 0;
			addedSkills.forEach(function( addedSkill){
				if(skil.id == addedSkill.id){
					$scope.indexOfSkill = i;
				return i;
				}
				i++;
				return -1;
			});
		}
		
	    $scope.serviceRegistration=function()
	       {
	    	var user={
	    			user:{id :$scope.userId},
	    			role:{id:$scope.role},
	    			services:{id:$scope.subscribableServiceId},
	    			skills:$scope.userSkills
	    	}
	    	console.log(user);
	     dashboardService.registerForService(user) 
	 		  .then(function successCallback(response) {
	 			  console.log(response);
	 	      	 if(response.data.status==="success"){
	 	      		$window.location.reload();
	 	      	 }
	 	      	 else{
	 	      	 }
	               }, function errorCallback(response) {

	              });
	    	   
	       };
	
});