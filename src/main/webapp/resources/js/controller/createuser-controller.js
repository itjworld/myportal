'use strict';
angular.module('appPortal').controller(
		'userCtrl',
		[
				'$scope',
				'$http',
				'contextPath',
				function($scope, $http,contextPath) {
					$scope.auth = {
						username : '',
						password : '',
						comfirm : ''
					};
					$scope.alter = '';
					$scope.show = false;
					$scope.createUser = function() {
						if ($scope.auth.password != $scope.auth.comfirm) {
							showAlter("Password does not match,try again!",
									true);
							$scope.auth.comfirm = $scope.auth.password = '';
						} else {
							var credentials={username: $scope.auth.username,password:$scope.auth.password};
							$http.post(contextPath+'/addUser/', credentials).then(
									function(response) {
										var msg='';
										if("FOUND"==response){
											msg="Already user exist,Please enter unique user name!"
										}else if("CREATED"==response){
											msg="Successfully add user!"
										}else{
											msg="Something wrong!"
										}
										showAlter(msg, true);
										$scope.auth = {
												username : '',
												password : '',
												comfirm : ''
											};
									}, function(errResponse) {
										console.log(errResponse);
									});
						}

					}
					function showAlter(msg, show) {
						$scope.alter = msg;
						$scope.show = show;
					}
					$scope.close = function() {
						showAlter('', false);
					}
				} ]);