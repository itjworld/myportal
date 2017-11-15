'use strict';
app.controller('msgCtrl', [
		'$scope',
		'$http',
		'contextPath',
		function($scope, $http, contextPath) {
			$scope.info = {
				mobile : '',
				message : ''
			};
			$scope.contextPath = contextPath;
			$scope.alter = '';
			$scope.show = false;
			$scope.sendMessage = function() {
				var details = {
					mobile : $scope.info.mobile,
					message : $scope.info.message
				};
				$http.post(contextPath + '/sendMsg/', details).then(
						function(response) {
							console.log(response);
							var msg = '';
							if ("OK" == response.statusText) {
								msg = "successfully sent"
							} else {
								msg = "Something wrong!"
							}
							showAlter(msg, true);
							$scope.info = {
								mobile : '',
								message : ''
							};
						}, function(errResponse) {
							console.log(errResponse);
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