<!DOCTYPE html>
<html>
<head>

<title>HR Platform</title> 

<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimal-ui" />
<meta name="apple-mobile-web-app-status-bar-style" content="yes" />


<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="311395685548-1460djek3uelogitknq2poghbnuiuka3.apps.googleusercontent.com">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript">

	var contextPath = "${pageContext.request.contextPath}";

</script>

<!-- <script type="text/javascript" src="js/lib/jquery.min.js"></script> -->
<script type="text/javascript" src="js/lib/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="js/lib/ng-file-upload-shim.min.js"></script>
<!-- <script type="text/javascript" src="js/lib/angular.js"></script> -->
<script type="text/javascript" src="js/lib/angular.min.js"></script>
<script type="text/javascript" src="js/lib/angular-route.min.js"></script>
<script type="text/javascript" src="js/lib/angular-cookies.js"></script>
<script type="text/javascript" src="js/lib/angular-resource.min.js"></script>
<script type="text/javascript" src="js/lib/angular-ui-router.js"></script>
<script type="text/javascript" src="js/lib/angular-animate.min.js"></script>
<script type="text/javascript" src="js/lib/angular-aria.min.js"></script>
<script type="text/javascript" src="js/lib/angular-messages.min.js"></script>
<script type="text/javascript" src="js/lib/angular-material.js"></script>
<script type="text/javascript" src="js/lib/angular-material.min.js"></script>
<script type="text/javascript" src="js/lib/angular-vs-repeat.js"></script>
<script type="text/javascript" src="js/lib/b64.js"></script>
<script type="text/javascript" src="js/lib/query-string.js"></script>
<script type="text/javascript" src="js/lib/angular-oauth2.js"></script>
<script type="text/javascript" src="js/lib/http-auth-interceptor.js"></script>
<script type="text/javascript" src="js/lib/angular-google-gapi.min.js"></script>

<!-- Custom js file -->
<script type="text/javascript" src="js/controllers/hrpAppConfig.js"></script>
 <script type="text/javascript" src="js/controllers/home/RegistrationController.js"></script>
  <script type="text/javascript" src="js/controllers/home/LoginController.js"></script>
  <script type="text/javascript" src="js/controllers/home/DashboardController.js"></script>
 <script type="text/javascript" src="js/services/home/userService.js"></script>
 
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/angular-material/angular-material.css">
 
 <!-- Custom css file -->
 <link rel="stylesheet" href="css/custom/home/registration.css">


</head>

<body  ng-app="hrpApp">
	
	  <div ui-view></div>

</body>
</html>