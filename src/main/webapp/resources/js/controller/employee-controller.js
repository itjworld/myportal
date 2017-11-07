'use strict';
var app = angular.module('empApp', ['datatables','datatables.bootstrap']);
app.controller('empCtrl', function($scope,$http,DTOptionsBuilder, DTColumnBuilder,$compile) {
	var REST_SERVICE_URI = 'http://localhost:8080/myportal/employee/';
	 $scope.employee={id:null,name:'',age:'',salary:'',email:''};
	 $scope.employees=[];
	 $scope.edit=edit;
	 $scope.remove=remove;
	 $scope.dtInstance = {};
	 
	 $scope.dtOptions = DTOptionsBuilder.newOptions()
     .withFnServerData(serverData)
     .withBootstrap()
     .withDataProp('data')
     .withDisplayLength(2)
     .withOption('serverSide', true)
     .withOption('processing', true)
     .withOption('bFilter', false)
     .withOption('bLengthChange', false)
     .withOption('rowCallback', rowCallback)
     .withOption("aaSorting", [0, 'asc'])
     .withPaginationType('full_numbers');
   $scope.dtColumns = [
     DTColumnBuilder.newColumn('name').withTitle('Name'),
     DTColumnBuilder.newColumn('email').withTitle('Email'),
     DTColumnBuilder.newColumn('age').withTitle('Age'),
     DTColumnBuilder.newColumn('salary').withTitle('Salary'),
     DTColumnBuilder.newColumn(null).withTitle('Edit').notSortable().renderWith(function (data) {
         return '<input type="button" class="btn btn-primary btn-sm" ng-click="edit('+data.id+')" value="Edit"/>'
     }),
     DTColumnBuilder.newColumn(null).withTitle('Remove').notSortable().renderWith(function (data) {
         return '<input type="button" class="btn btn-danger btn-sm"  ng-click="remove('+data.id+')" value="Remove" />'
     }),
    
   ];
    
   function rowCallback (nRow, aData, iDisplayIndex, iDisplayIndexFull) {
       $compile(nRow)($scope);
}
   function serverData(sSource, aoData, fnCallback, oSettings) {
	   var page=aoData[3].value;
	   if(page!=0){
		   page=parseInt(page)-1;
	   }
	   var params= {params:{page:page,pageSize:aoData[4].value,sort:aoData[2].value[0].column,sortBy:aoData[2].value[0].dir}};
	   $http.get(REST_SERVICE_URI,params)
       .then(
       function (response) {
    	   $scope.employees=response.data.results;
    	   var total=response.data.id
    	   if($scope.employees==null || $scope.employees.length==0){
    		   $scope.employees=[];
    	   }
    	   var records = {
                   'draw': aoData[0].value,
                   'recordsTotal': total,
                   'recordsFiltered':total,
                   'data': $scope.employees
               };
    	   fnCallback(records);
       },
       function(errResponse){
           console.error('Error while deleting Employee');
       }
     );
   }
   
   
	 function edit (id){
        console.log('id to be edited', id);
        for(var i = 0; i < $scope.employees.length; i++){
            if( $scope.employees[i].id === id) {
            	 $scope.employee = angular.copy($scope.employees[i]);
                break;
            }
        }
    }
    
    $scope.submit=function() {
    	console.log($scope.employee)
    	if($scope.employee.id>0){
    		updateEmployee($scope.employee);
    	}else{
    		createEmployee($scope.employee);	
    	}
    	
    	resetForm();
    }
    
    $scope.reset=function() {
    	resetForm();
    }
    function resetForm(){
    	$scope.employee={id:null,name:'',age:'',salary:'',email:''};
    }
   function remove(id){
       console.log('id to be deleted', id);
       if(id>0) {// clean form if the user to be deleted is shown there.
    	   deleteEmployee(id);
       }
       resetForm(); 
   }
   function createEmployee(employee) {
       $http.post(REST_SERVICE_URI, employee)
           .then(
           function (response) {
               console.log(response);
               render();
           },
           function(errResponse){
               console.error('Error while creating Employee');
           }
       );
   }
   function updateEmployee(employee) {
       $http.put(REST_SERVICE_URI+employee.id,employee)
           .then(
           function (response) {
               console.log(response);
               render();
           },
           function(errResponse){
               console.error('Error while creating Employee');
           }
       );
   }
   
   function deleteEmployee(id){
	   $http.delete(REST_SERVICE_URI+id)
       .then(
       function (response) {
    	   console.log(response);
    	   render();
       },
       function(errResponse){
           console.error('Error while deleting Employee');
       }
   );
   }
   function render(){
	   $scope.dtInstance.rerender();
   }
   
   $scope.logout = function() {
	   $http.post('http://localhost:8080/myportal/logout', {}).then(
		       function (response) {
		    	   if (response.status ==200 && response.statusText=='OK') {
		                window.location.replace('/myportal/');
		            }else {
		                console.log('Access denied');
		            }
		       },
		       function(errResponse){
		           console.error('Error while deleting Employee');
		       }
		 );
	 }
   
});