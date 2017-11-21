'use strict';
app.controller(
				'infoCtrl',
				[
						'$scope',
						'DTOptionsBuilder',
						'DTColumnBuilder',
						'$compile',
						'infoService',
						'contextPath',
						function($scope, DTOptionsBuilder, DTColumnBuilder,
								$compile, infoService, contextPath) {

							$scope.detail = {
								id : null,
								name : '',
								mobile : '',
								desc : ''
							};
							$scope.contextPath = contextPath;
							$scope.details = [];
							$scope.edit = edit;
							$scope.remove = remove;
							$scope.dtInstance = {};

							$scope.dtOptions = DTOptionsBuilder.newOptions()
									.withFnServerData(serverData)
									.withBootstrap().withDataProp('data')
									.withDisplayLength(5).withOption(
											'serverSide', true).withOption(
											'processing', true).withOption(
											'bFilter', true).withOption(
											'bLengthChange', true).withOption(
											'rowCallback', rowCallback)
									.withOption("aaSorting", [ 0, 'asc' ])
									.withPaginationType('full_numbers');
							$scope.dtColumns = [
									DTColumnBuilder.newColumn('name')
											.withTitle('Name'),
									DTColumnBuilder.newColumn('mobile')
											.withTitle('Mobile'),
									DTColumnBuilder.newColumn('desc').withTitle(
											'Description'),
									DTColumnBuilder
											.newColumn(null)
											.withTitle('Edit')
											.notSortable()
											.renderWith(
													function(data) {
														return '<input type="button" class="btn btn-primary btn-sm" ng-click="edit('
																+ data.id
																+ ')" value="Edit"/>'
													}),
									DTColumnBuilder
											.newColumn(null)
											.withTitle('Remove')
											.notSortable()
											.renderWith(
													function(data) {
														return '<input type="button" class="btn btn-danger btn-sm"  ng-click="remove('
																+ data.id
																+ ')" value="Remove" />'
													}),

							];

							function rowCallback(nRow, aData, iDisplayIndex,
									iDisplayIndexFull) {
								$compile(nRow)($scope);
							}
							function serverData(sSource, aoData, fnCallback,
									oSettings) {
								console.log(JSON.stringify(aoData[5].value.value));
								var page = aoData[3].value;
								var params = {
									params : {
										page : page,
										pageSize : aoData[4].value,
										sort : aoData[2].value[0].column,
										sortBy : aoData[2].value[0].dir,
										searchParam:aoData[5].value.value
									}
								};
								infoService
										.fetchAllDetails(params)
										.then(
												function(response) {
													$scope.details = response.data.results;
													console.log($scope.details);
													var total = response.data.id;
													if ($scope.details == null
															|| $scope.details.length == 0) {
														$scope.details = [];
														total=0;
													}
													var records = {
														'draw' : aoData[0].value,
														'recordsTotal' : total,
														'recordsFiltered' : total,
														'data' : $scope.details
													};
													fnCallback(records);
												},
												function(errResponse) {
													console
															.error('Something wrong!');
												});
							}

							function edit(id) {
								console.log(id)
								for (var i = 0; i < $scope.details.length; i++) {
									if ($scope.details[i].id === id) {
										$scope.detail = angular
												.copy($scope.details[i]);
										break;
									}
								}
							}

							$scope.submit = function() {
								console.log("**** : " + $scope.detail);
								if ($scope.detail.id > 0) {
									updateDetail($scope.detail);
								} 
								resetForm();
							}
							$scope.updateDetail = function(){
								console.log("**** : " + $scope.detail);
								if ($scope.detail.id > 0) {
									updateDetail($scope.detail);
								} 
								resetForm();
							}

							$scope.reset = function() {
								resetForm();
							}
							function resetForm() {
								$scope.detail = {
									id : null,
									name : '',
									mobile : '',
									desc : ''
								};
							}
							function remove(id) {
								if (id > 0) {// clean form if the user to be
												// deleted is shown
									deleteDetail(id);
								}
							}
//							function createEmployee(employee) {
//								employeeService
//										.createEmployee(employee)
//										.then(
//												function(response) {
//													console.log(response);
//													render();
//												},
//												function(errResponse) {
//													console
//															.error('Error while creating Employee');
//												});
//							}
							function updateDetail(detail) {
								console.log(detail);
								infoService
										.updateInfo(detail.id, detail)
										.then(
												function(response) {
													render();
												},
												function(errResponse) {
													console
															.error('Error while creating Employee');
												});
							}

							function deleteDetail(id) {
								infoService.deleteInfo(id).then(
										function(response) {
											console.log(response);
											render();
										}, function(errResponse) {
											console.error('Something wrong!');
										});
							}
							function render() {
								$scope.dtInstance.rerender();
							}
						} ]);