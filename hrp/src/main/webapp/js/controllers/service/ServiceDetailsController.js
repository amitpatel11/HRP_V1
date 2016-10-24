hrpApp.controller('serviceDetailsController',function($scope,$location,$http,$uibModal,dashboardDetailsService,$rootScope,$resource,$window,$cookieStore){
	
	$scope.userId=$cookieStore.get('loggedUserId');
	
	 dashboardDetailsService.getUserId($scope.userId) 
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
	
});