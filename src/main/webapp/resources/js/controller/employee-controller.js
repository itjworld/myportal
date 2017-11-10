'use strict';
angular
		.module('appPortal')
		.controller(
				'empCtrl',
				[
						'$scope',
						'DTOptionsBuilder',
						'DTColumnBuilder',
						'$compile',
						'employeeService',
						function($scope, DTOptionsBuilder, DTColumnBuilder,
								$compile, employeeService) {

							$scope.employee = {
								id : null,
								name : '',
								age : '',
								salary : '',
								email : ''
							};
							$scope.employees = [];
							$scope.edit = edit;
							$scope.remove = remove;
							$scope.dtInstance = {};

							$scope.dtOptions = DTOptionsBuilder.newOptions()
									.withFnServerData(serverData)
									.withBootstrap().withDataProp('data')
									.withDisplayLength(5).withOption(
											'serverSide', true).withOption(
											'processing', true).withOption(
											'bFilter', false).withOption(
											'bLengthChange', false).withOption(
											'rowCallback', rowCallback)
									.withOption("aaSorting", [ 0, 'asc' ])
									.withPaginationType('full_numbers');
							$scope.dtColumns = [
									DTColumnBuilder.newColumn('name')
											.withTitle('Name'),
									DTColumnBuilder.newColumn('email')
											.withTitle('Email'),
									DTColumnBuilder.newColumn('age').withTitle(
											'Age'),
									DTColumnBuilder.newColumn('salary')
											.withTitle('Salary'),
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
								console.log(aoData);
								var page = aoData[3].value;
								if (page != 0) {
									page = parseInt(page) - 1;
								}
								var params = {
									params : {
										page : page,
										pageSize : aoData[4].value,
										sort : aoData[2].value[0].column,
										sortBy : aoData[2].value[0].dir
									}
								};
								employeeService
										.fetchAllEmployee(params)
										.then(
												function(response) {
													$scope.employees = response.data.results;
													var total = response.data.id
													if ($scope.employees == null
															|| $scope.employees.length == 0) {
														$scope.employees = [];
														total=0;
													}
													var records = {
														'draw' : aoData[0].value,
														'recordsTotal' : total,
														'recordsFiltered' : total,
														'data' : $scope.employees
													};
													fnCallback(records);
												},
												function(errResponse) {
													console
															.error('Something wrong!');
												});
							}

							function edit(id) {
								for (var i = 0; i < $scope.employees.length; i++) {
									if ($scope.employees[i].id === id) {
										$scope.employee = angular
												.copy($scope.employees[i]);
										break;
									}
								}
							}

							$scope.submit = function() {
								if ($scope.employee.id > 0) {
									updateEmployee($scope.employee);
								} else {
									createEmployee($scope.employee);
								}

								resetForm();
							}

							$scope.reset = function() {
								resetForm();
							}
							function resetForm() {
								$scope.employee = {
									id : null,
									name : '',
									age : '',
									salary : '',
									email : ''
								};
							}
							function remove(id) {
								if (id > 0) {// clean form if the user to be
												// deleted is shown
									deleteEmployee(id);
								}
							}
							function createEmployee(employee) {
								employeeService
										.createEmployee(employee)
										.then(
												function(response) {
													console.log(response);
													render();
												},
												function(errResponse) {
													console
															.error('Error while creating Employee');
												});
							}
							function updateEmployee(employee) {
								employeeService
										.updateEmployee(employee.id, employee)
										.then(
												function(response) {
													render();
												},
												function(errResponse) {
													console
															.error('Error while creating Employee');
												});
							}

							function deleteEmployee(id) {
								employeeService.deleteEmployee(id).then(
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

							$scope.logout = function() {
								employeeService.logout();
							}
						} ]);