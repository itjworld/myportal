'use strict';
var app = angular.module('loginApp', []);
app.controller('loginCtrl',['$scope', '$http', function($scope, $http) {
	var LOGIN_URI = '/myportal/authenticate';
	var method='POST';
	var headers= {"Content-Type": "application/x-www-form-urlencoded;charset=utf-8;","X-Login-Ajax-call": 'true'};
	$scope.alter='';
	$scope.in=false;
	$scope.submitted=false;
	$scope.auth = {
		username : '',
		password : ''
	};
	/*var fieldWithFocus;
	$scope.focus = function(fieldName) {
		fieldWithFocus = fieldName;
	};

	$scope.blur = function(fieldName) {
		fieldWithFocus = undefined;
	};

	$scope.isMessagesVisible = function(fieldName) {
		return fieldWithFocus === fieldName || $scope.submitted;
	};*/
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
        	if (response.status ==200 && response.statusText=='OK') {
                window.location.replace('/myportal/views/home.html');
            }else if (response.status ==203)  {
            	if(response.data=='BAD'){
            		showAlter('Wrong credentials, try again!',true);
            	}else if(response.data=='DISABLED'){
            		showAlter('User has been disabled!',true);
            	}else if(response.data=='LOCKED'){
            		showAlter('User account has been locked!',true);
            	}else if(response.data=='EXPIRED'){
            		showAlter('User account has been expired!',true);
            	}else if(response.data=='CREDENTIAL_EXPIRED'){
            		showAlter('Credential has been expired!',true);
            	}
            	$scope.in=true;
            }else{
            	showAlter('Something is wrong!',true);
            }
        },
        function(errResponse){
        	showAlter(errResponse,true);
        });
		function showAlter(msg,show){
			$scope.alter=msg;
        	$scope.in=show;
		}
		$scope.close=function(){
			showAlter('',false);
		}
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