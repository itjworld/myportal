'use strict';
app.controller('descCtrl', [
		'$scope',
		'$http',
		'contextPath',
		function($scope, $http, contextPath) {
			$scope.info = {
				name : '',
				mobile : '',
				desc : ''
			};
			$scope.contextPath = contextPath;
			$scope.alter = '';
			$scope.show = false;
			$scope.sendMessage = function() {
				var details = {
					name : $scope.info.name,
					mobile : $scope.info.mobile,
					desc : $scope.info.desc
				};
				$http.post(contextPath + '/addDesc/', details).then(
						function(response) {
							console.log(response);
							var msg = '';
							if ("OK" == response.statusText) {
								msg = "successfully added"
							} else {
								msg = "Something wrong!"
							}
							showAlter(msg, true);
							$scope.info = {
								name : '',
								mobile : '',
								desc : ''
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