 /*Created on 20-October-2016
      Author: Nageswara rao
      Copyright (c) 2016 by HR Platform. All Rights Reserved.*/ 

hrpApp.service("userService", function($http) {

	this.getSecurityQustionForUserLogin = function() {
		return $http.get('user/login');
	};
	this.registerUser = function(user){
		return	$http.post('user/register',user, {
				headers : {
					'Content-Type' : 'application/json',
					'Accept' : 'application/json'
				}
			});
		};
}); 
