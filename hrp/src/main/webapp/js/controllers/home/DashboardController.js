hrpApp.controller('dashboardController',function($mdDialog,$scope,$location,$http,userService){
	 $scope.status = '  ';
	  $scope.customFullscreen = false;
	  
	  $scope.showConfirm = function(ev) {
		    // Appending dialog to document.body to cover sidenav in docs app
		    var confirm = $mdDialog.confirm()
		          .title('Service Dialog')
		          .textContent('Please select service')
		          .ariaLabel('Lucky day')
		          .targetEvent(ev)
		          .ok('Seeker')
		          .cancel('Provider');

		    $mdDialog.show(confirm).then(function() {
		      $scope.status = 'You decided to get rid of your debt.';
		    }, function() {
		      $scope.status = 'You decided to keep your debt.';
		    });
		  };
	
});