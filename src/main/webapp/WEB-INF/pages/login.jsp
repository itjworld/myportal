<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Ashish Jaiswal">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="<c:url value="/resources/css/api/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/api/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/api/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/api/angular.min.js"/>"></script>
<style type="text/css">
.myrow-container {
	margin: 20%;
}
</style>
</head>
<body class=".container-fluid">
	<div class="container" ng-app="loginApp" ng-controller="loginCtrl">
		<div class="panel panel-success myrow-container">
			<div class="panel-heading">
				<h3 class="panel-title">
					<div align="center">
						<b>Login</b>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<form ng-submit="onLogin()">
					<div class="form-group">
						<input type="text" ng-focus="focus('username')"
							ng-blur="blur('username')" ng-model="auth.username" name="username"
							placeholder="Username" required ng-minlength="6"
							class="form-control" />
					</div>
					<div class="error-messages" ng-show="isMessagesVisible('username')" ng-cloak>
                            <div ng-message="required">The username is mandatory</div>
                            <div ng-message="minlength">must have minimum 6 characters</div>
                        </div>
					<div class="form-group">
						<input type="password" ng-focus="focus('password')"
							ng-blur="blur('password')" ng-model="auth.password" name="password"
							class="form-control" placeholder="Password" required
							ng-minlength="6" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" />
					</div>
					<div class="error-messages" ng-show="isMessagesVisible('password')"  ng-cloak>
                            <div ng-message="required">The password is mandatory</div>
                            <div ng-message="minlength">must have minimum 6 characters</div>
                            <div ng-message="pattern">At least one number and uppercase</div>
                        </div>
					<button type="submit" class="btn btn-primary">Log In</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>

<script
	src="<c:url value='/resources/js/controller/login-controller.js' />"></script>