'use strict';
app.controller(
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
					$scope.contextPath = contextPath;
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
										console.log(response);
										var msg='';
										if("FOUND"==response.data){
											msg="Already user exist,Please enter unique user name!"
										}else if("CREATED"==response.data){
											msg="Successfully add user!"
										}else{
											msg="Something wrong!"
										}
										if(msg!=''){showAlter(msg, true);}
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