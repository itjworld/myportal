'use strict';

angular
		.module('appPortal')
		.controller(
				'loginCtrl',
				[
						'$scope',
						'loginService',
						function($scope, loginService) {
							$scope.alter = '';
							$scope.show = false;
							$scope.auth = {
								username : '',
								password : ''
							};
							$scope.onLogin = function() {
								var credentials = 'username='
										+ $scope.auth.username + '&password='
										+ $scope.auth.password;
								loginService
										.login(credentials)
										.then(
												function(data) {
													var msg = ''
													if (data != '') {
														if (data == 'OK') {
															window.location
																	.replace('/views/home.html');
														} else if (data == 'BAD') {
															msg = 'Wrong credentials, try again!';
														} else if (data == 'DISABLED') {
															msg = 'User has been disabled!';
														} else if (data == 'LOCKED') {
															msg = 'User account has been locked!';
														} else if (data == 'EXPIRED') {
															msg = 'User account has been expired!';
														} else if (data == 'CREDENTIAL_EXPIRED') {
															msg = 'Credential has been expired!';
														}else{
															msg = 'Something is wrong!'	
														}
													}else{
														 msg = 'Something is wrong!'
													}
													showAlter(msg, true);
												},
												function(errResponse) {
													showAlter(
															'Something is wrong!',
															true);
												});

							}
							function showAlter(msg, show) {
								$scope.alter = msg;
								$scope.show = show;
							}
							$scope.close = function() {
								showAlter('', false);
							}
						} ]);