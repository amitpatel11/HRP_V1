hrpApp.service("serviceDetailsService", function($http) {

	this.getUserId = function(id) {
		return $http.get('user/'+id);
	};
	this.getAllActiveService = function() {
		return $http.get('util/getAllActiveServices');
	};
	
	this.getAllActiveSkills = function() {
		return $http.get('skills/active');
	};
	
	this.registerForService = function(user){
		return	$http({
			method : 'POST',
			url : 'userServices/register',
			data : user,
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json'
			}
        	});
	};
		
}); 