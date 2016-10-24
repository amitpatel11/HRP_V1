 /*Created on 20-October-2016
      Author: Nageswara rao
      Copyright (c) 2016 by HR Platform. All Rights Reserved.*/ 

hrpApp.controller('registrationController',function($scope,$location,$http,userService, $resource){
	
	$scope.regexPatterns = {
			username : /^[A-Za-z0-9]+(?:[ _.][A-Za-z0-9]+)*$/,
			mobile : /^\d{10}$/,
			firstname: /^[a-zA-Z ]+$/,
			emailRegex :  /^[A-Za-z]+[A-Za-z0-9._]+@[A-Za-z]+\.[A-Za-z.]{2,5}$/
		};
	
	$scope.registerform1=false;
	$scope.registerform2=true;
    $scope.registerform3=true;
    $scope.successMessageDiv=true;

    $scope.showRegisterForm1=function()
    {
    	$scope.registerform1=false;
    	$scope.registerform2=true;
        $scope.registerform3=true;
        $scope.successMessageDiv=true;
    }
    $scope.showRegisterBackForm2=function()
    {
    	$scope.registerform1=true;
    	$scope.registerform2=false;
        $scope.registerform3=true;
        $scope.successMessageDiv=true;
    }
    $scope.showRegisterForm2=function()
    {
    	if($scope.registerForm1Validations())
		{
    	
    	userService.getAllQuestionForRegistration()
        .then(function successCallback(response) {
             var i=1;
        	angular.forEach(response.data.result, function(value, key) {
        		$scope["registerQuestion"+i]=value.question; 
        		i=i+1;
        		});
              }, function errorCallback(response) {
              console.log("failure   "+response);
        });
    	$scope.registerform1=true; 
    	$scope.registerform2=false;
        $scope.registerform3=true;
        $scope.successMessageDiv=true;
        
        		}
    }
    
    $scope.showRegisterForm3=function()
    {
    /*	if($scope.registerForm2Validations())
		{*/
    	$scope.registerform1=true;
    	$scope.registerform2=true;
        $scope.registerform3=false;
        $scope.successMessageDiv=true;
	/*	}*/
    }
    
    $scope.registerUser=function()
    {
    	/*if($scope.registerForm3Validations())
		{*/
   	var firstName=$scope.fName;
    	var lastName=$scope.lName;
    	var mobile=$scope.mobileNo;
    	var email=$scope.emailId;
    var RegisterAnswer1=$scope.registerAnswer1;
    	
    var RegisterAnswer2=$scope.registerAnswer2;
    var RegisterAnswer3=$scope.registerAnswer3;
    var RegisterAnswer4=$scope.registerAnswer4;
    var RegisterAnswer5=$scope.registerAnswer5;
    var RegisterAnswer6=$scope.registerAnswer6;
    	
    var RegisterAnswer7=$scope.registerAnswer7;
    var RegisterAnswer8=$scope.registerAnswer8;
    var RegisterAnswer9=$scope.registerAnswer9;
    var RegisterAnswer10=$scope.registerAnswer10;
    	
    	var userData = {
    			email : email,
    			deletedYn:false,
    			userProfile:{
    				firstName:firstName,
    				lastName:lastName,
    				mobile:mobile,
    				deletedYn:false
    			},
    	      
    	answers:[
              {
            	  questionId:1,
            	  answer:RegisterAnswer1,
            	  deletedYn:false
              },
              {
            	  questionId:2,
            	  answer:RegisterAnswer2,
            	  deletedYn:false
              },
              {
            	  questionId:3,
            	  answer:RegisterAnswer3,
            	  deletedYn:false
              },
              {
            	  questionId:4,
            	  answer:RegisterAnswer4,
            	  deletedYn:false
              },
              {
            	  questionId:5,
            	  answer:RegisterAnswer5,
            	  deletedYn:false
              },
              {
            	  questionId:6,
            	  answer:RegisterAnswer6,
            	  deletedYn:false
              },
              {
            	  questionId:7,
            	  answer:RegisterAnswer7,
            	  deletedYn:false
              },
              {
            	  questionId:8,
            	  answer:RegisterAnswer8,
            	  deletedYn:false
              },
              {
            	  questionId:9,
            	  answer:RegisterAnswer9,
            	  deletedYn:false
              },
              {
            	  questionId:10,
            	  answer:RegisterAnswer10,
            	  deletedYn:false
              }
    	         
    	         ]
    	
			};
    	
    	userService.registerUser(userData)
        .then(function successCallback(response) {
      	  console.log(response)
        	      if(response.data.status=="success")
        	    	  {
        	    	  $scope.registerform1=true; 
        	      	  $scope.registerform2=true;
        	          $scope.registerform3=true;
        	          $scope.successMessageDiv=false;
        	    	  
        	    	  }
        	      else{
        	    	  
        	      }
        	
                  }, function errorCallback(response) {

                 });
		   /*  }*/
         };
    
    $scope.registerForm1Validations=function()
    {
			$scope.registerFormUsertypeValidationMessage = false;
			var fName = $scope.fName;;
			var lName=$scope.lName;
			var mobileNo=$scope.mobileNo;
			
			if ((fName == undefined) || (fName == null)
					|| (fName == "")) {
				$scope.registerFormFirstNameValidationMessage = true;
				$scope.registerFormFirstNameMessage = "Please enter your first name"
				$("#fNameId").focus();
				return false;
			} else if (fName.length < 3) {
				$scope.registerFormFirstNameValidationMessage = true;
				$scope.registerFormFirstNameMessage = "First name must have atleast 3 characters"
				$("#fNameId").focus();
				return false;
			} else if (!$scope.regexPatterns.firstname.test(fName)) {
				$scope.registerFormFirstNameValidationMessage = true;
				$scope.registerFormFirstNameMessage = "Special characters and numbers not allowed"
				$("#fNameId").focus();
				return false;
			} else {
				$scope.registerFormFirstNameValidationMessage = false;
			}

			if ((lName == undefined) || (lName == null)
					|| (lName == "")) {
				$scope.registerFormLastNameValidationMessage = true;
				$scope.registerFormLastNameMessage = "Please enter your last name."
				$("#lNameId").focus();
				return false;
			} else if (lName.length < 3) {
				$scope.registerFormLastNameValidationMessage = true;
				$scope.registerFormLastNameMessage = "Last name must have atleast 3 characters"
				$("#lNameId").focus();
				return false;
			} else if (!$scope.regexPatterns.firstname.test(lName)) {
				$scope.registerFormLastNameValidationMessage = true;
				$scope.registerFormLastNameMessage = "Special characters and numbers not allowed"
				$("#lNameId").focus();
				return false;
			} else {
				$scope.registerFormLastNameValidationMessage = false;
			}
			if ((mobileNo == undefined) || (mobileNo == null)
					|| (mobileNo == "")) {
				$scope.registerFormMobileValidationMessage = true;
				$scope.registerFormMobileMessage = "Please enter your mobile number"
				$("#mobileId").focus();
				return false;
			} else if (mobileNo.length < 10) {
				$scope.registerFormMobileValidationMessage = true;
				$scope.registerFormMobileMessage = "Please enter 10 digit number"
				$("#mobileId").focus();
				return false;
			}
			else if (!$scope.regexPatterns.mobile.test(mobileNo)) {
				$scope.registerFormMobileValidationMessage = true;
				$scope.registerFormMobileMessage = "Please enter 10 digit number"
				$("#mobileId").focus();
				return false;
			}else {
				$scope.registerFormMobileValidationMessage = false;
			}
			if(!$scope.checkEmail()){
				$("#emailId").focus();
				return false;
			}
			else{
				return true;
			}
    	
    };
    $scope.registerForm2Validations=function()
    {
    	    var RegisterAnswer1=$scope.registerAnswer1;
    	    var RegisterAnswer2=$scope.registerAnswer2;
    	    var RegisterAnswer3=$scope.registerAnswer3;
    	    var RegisterAnswer4=$scope.registerAnswer4;
    	    var RegisterAnswer5=$scope.registerAnswer5;
    	    
    	    if ((RegisterAnswer1 == undefined) || (RegisterAnswer1 == null)
					|| (RegisterAnswer1 == "")) {
				$scope.RegisterAnswer1ValidationMessage = true;
				$scope.RegisterAnswer1Message = "Please enter answer"
				$("#answer1").focus();
				return false;
			} 
    	    else {
    	    	$scope.RegisterAnswer1ValidationMessage = false;
			}
    	    if ((RegisterAnswer2 == undefined) || (RegisterAnswer2 == null)
					|| (RegisterAnswer2 == "")) {
				$scope.RegisterAnswer2ValidationMessage = true;
				$scope.RegisterAnswer2Message = "Please enter answer"
				$("#answer2").focus();
				return false;
			} 
    	    else {
    	    	$scope.RegisterAnswer2ValidationMessage = false;
			}
    	    if ((RegisterAnswer3 == undefined) || (RegisterAnswer3 == null)
					|| (RegisterAnswer3 == "")) {
				$scope.RegisterAnswer3ValidationMessage = true;
				$scope.RegisterAnswer3NameMessage = "Please enter answer"
				$("#answer3").focus();
				return false;
			} 
    	    else {
    	    	$scope.RegisterAnswer3ValidationMessage = false;
			}
    	    if ((RegisterAnswer4 == undefined) || (RegisterAnswer4 == null)
					|| (RegisterAnswer4 == "")) {
				$scope.RegisterAnswer4ValidationMessage = true;
				$scope.RegisterAnswer4NameMessage = "Please enter answer"
				$("#answer4").focus();
				return false;
			} 
    	    else {
    	    	$scope.RegisterAnswer4ValidationMessage = false;
			}
    	    if ((RegisterAnswer5 == undefined) || (RegisterAnswer5 == null)
					|| (RegisterAnswer5 == "")) {
				$scope.RegisterAnswer5ValidationMessage = true;
				$scope.RegisterAnswer5Message = "Please enter answer"
				$("#answer5").focus();
				return false;
			}
    	    else{
    	    	$scope.RegisterAnswer5ValidationMessage = false;
    	    	return true;
    	    }
    	 
    	
    };
    $scope.registerForm3Validations=function()
    {
    	var RegisterAnswer6=$scope.registerAnswer6;
  	    var RegisterAnswer7=$scope.registerAnswer7;
  	    var RegisterAnswer8=$scope.registerAnswer8;
  	    var RegisterAnswer9=$scope.registerAnswer9;
  	    var RegisterAnswer10=$scope.registerAnswer10;
  	    
  	    if ((RegisterAnswer6 == undefined) || (RegisterAnswer6 == null)
					|| (RegisterAnswer6 == "")) {
				$scope.RegisterAnswer6ValidationMessage = true;
				$scope.RegisterAnswer6Message = "Please enter answer"
				$("#answer6").focus();
				return false;
			} 
  	  else {
	    	$scope.RegisterAnswer6ValidationMessage = false;
		}
  	    if ((RegisterAnswer7 == undefined) || (RegisterAnswer7 == null)
					|| (RegisterAnswer7 == "")) {
				$scope.RegisterAnswer7ValidationMessage = true;
				$scope.RegisterAnswer7NameMessage = "Please enter answer"
				$("#answer7").focus();
				return false;
			} 
  	  else {
	    	$scope.RegisterAnswer7ValidationMessage = false;
		}
  	    if ((RegisterAnswer8 == undefined) || (RegisterAnswer8 == null)
					|| (RegisterAnswer8 == "")) {
				$scope.RegisterAnswer8ValidationMessage = true;
				$scope.RegisterAnswer8NameMessage = "Please enter answer"
				$("#answer8").focus();
				return false;
			} 
  	  else {
	    	$scope.RegisterAnswer8ValidationMessage = false;
		}
  	    if ((RegisterAnswer9 == undefined) || (RegisterAnswer9 == null)
					|| (RegisterAnswer9 == "")) {
				$scope.RegisterAnswer9ValidationMessage = true;
				$scope.RegisterAnswer9NameMessage = "Please enter answer"
				$("#answer9").focus();
				return false;
			}
  	  else {
	    	$scope.RegisterAnswer9ValidationMessage = false;
		}
  	    if ((RegisterAnswer10 == undefined) || (RegisterAnswer10 == null)
					|| (RegisterAnswer10 == "")) {
				$scope.RegisterAnswer10ValidationMessage = true;
				$scope.RegisterAnswer10Message = "Please enter answer"
				$("#answer10").focus();
				return false;
			}
  	    else{
  	    	$scope.RegisterAnswer10ValidationMessage = false;
  	    	return true;
  	    }
    	
    };
    
	$scope.checkEmail = function() {
		var email = $scope.emailId;
		if ((email == null) || (email == undefined)
				|| (email == "")) {
			$scope.registerFormEmailValidationMessage = true;
			$scope.registerFormEmailMessage = "Please enter your email";
			return false;
		} else if (!$scope.regexPatterns.emailRegex.test(email)) {
			$scope.registerFormEmailValidationMessage = true;
			$scope.registerFormEmailMessage = "Please enter valid email";
			return false;
		} else {
			$scope.registerFormEmailValidationMessage = false;
			return true;
		}
	};
	
});