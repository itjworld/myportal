'use strict';
var app = angular.module('loginApp', []);
app.controller('loginCtrl', function($scope, $http) {
	var REST_SERVICE_URI = 'http://localhost:8080/Spring-Rest-hibernate/login';
	var headers= {"Content-Type": "application/x-www-form-urlencoded"};
	$scope.submitted=false;
	$scope.auth = {
		username : '',
		password : ''
	};
	var fieldWithFocus;
	$scope.focus = function(fieldName) {
		fieldWithFocus = fieldName;
	};

	$scope.blur = function(fieldName) {
		fieldWithFocus = undefined;
	};

	$scope.isMessagesVisible = function(fieldName) {
		return fieldWithFocus === fieldName || $scope.submitted;
	};
	$scope.onLogin = function() {
		$scope.submitted = true;
		 $http.post(REST_SERVICE_URI,$scope.auth,headers)
	       .then(
	       function (response) {
	    	   console.log(response);
	    	   
	       },
	       function(errResponse){
	           console.error('Error while deleting Employee');
	       }
	   );
	}
}).directive(
		'checkPasswordsMatch',
		function() {
			return {
				require : 'ngModel',
				link : function(scope, elm, attrs, ngModel) {
					ngModel.$validators.checkPasswordsMatch = function(
							modelValue, viewValue) {
						if (scope.auth && scope.auth.password && viewValue) {
							console.log(modelValue);
							console.log(viewValue);
							return scope.auth.password === viewValue;
						}
						return true;
					};
				}
			};
		});