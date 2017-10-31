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
<title>Employee Information</title>
<!-- Bootstrap CSS -->
<link href="<c:url value="/resources/css/api/bootstrap.min.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/api/jquery.dataTables.min.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/api/datatables.bootstrap.min.css" />"
	rel="stylesheet">
	<link href="<c:url value="/resources/css/api/angular-datatables.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/api/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/api/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/api/angular.min.js"/>"></script>
<script src="<c:url value="/resources/js/api/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/resources/js/api/angular-datatables.bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/api/angular-datatables.min.js"/>"></script>
<style type="text/css">
.myrow-container {
	margin-top: 20px;
}
</style>
</head>
<body class=".container-fluid">
	<div class="container myrow-container" ng-app="empApp"
		ng-controller="empCtrl">
		<div class="panel panel-success">
			<div class="panel-heading">
				<h3 class="panel-title">
					<div align="center">
						<b>Employee Detail</b>
					</div>
				</h3>
			</div>
			<div class="panel-body">
				<form class="form-inline" ng-submit="submit()">
					<div class="form-group">
						<label for="name">Name:</label> <input type="hidden"
							ng-model="employee.id" /> <input type="text"
							class="form-control" id="name" placeholder="Enter name"
							ng-model="employee.name" maxlength="100" required />
					</div>
					<div class="form-group">
						<label for="age">Age:</label> <input type="text"
							class="form-control" id="age" placeholder="Enter age"
							ng-model="employee.age" maxlength="3" required>
					</div>
					<div class="form-group">
						<label for="salary">Salary:</label> <input type="text"
							class="form-control" id="salary" placeholder="Enter salary"
							ng-model="employee.salary" maxlength="6" required />
					</div>
					<div class="form-group">
						<label for="email">Email:</label> <input type="email"
							class="form-control" id="email" placeholder="Enter email"
							ng-model="employee.email" required />
					</div>
					<div class="form-group" style="margin-top: 10px;">
						<div class="row">
							<div class="col-xs-4">
								<input type="submit" id="saveEmployee"
									class="btn btn-success btn-sm"
									value="{{!employee.id ? 'Add' : 'Update'}}" />
							</div>
							<div class="col-xs-4">

								<button type="button" ng-click="reset()"
									class="btn btn-warning btn-sm">Cancel</button>

							</div>
							<div class="col-xs-4"></div>
						</div>
					</div>
				</form>
			</div>

		</div>
		<div class="panel panel-success" >
			<div class="panel-heading">
				<h3 class="panel-title">
					<div align="center" >
						<b>Employee List</b>
					</div>
					
				</h3>
			</div>
			<div class="panel-body">
			   <table  datatable="" dt-options="dtOptions" dt-columns="dtColumns" dt-instance="dtInstance" />
			</div>
		</div>
	</div>

</body>
</html>

<script
	src="<c:url value='/resources/js/controller/employee-controller.js' />"></script>
