'use strict';
var app = angular.module('loginApp', []);
app.controller('loginCtrl',['$scope', '$http', function($scope, $http) {
	var LOGIN_URI = '/myportal/authenticate';
	var method='POST';
	var headers= {"Content-Type": "application/x-www-form-urlencoded;charset=utf-8;","X-Login-Ajax-call": 'true'};
	
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
		var credentials='username=' + $scope.auth.username + '&password=' + $scope.auth.password; 
		$http({
            method: method,
            url: LOGIN_URI,
            data: credentials,
            headers:headers
        })
        .then(function(response) {
        	console.log(response);
            if (response.status ==200 && response.statusText=='OK') {
                window.location.replace('/myportal/views/home.html');
            }else {
                console.log('Access denied');
            }
        });
	}
	}]).directive(
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