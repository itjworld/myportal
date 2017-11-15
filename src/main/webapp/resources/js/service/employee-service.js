'use strict';
app.factory('employeeService', ['$http', '$q','contextPath', function($http, $q,contextPath){

	var REST_SERVICE_URI = contextPath+'/employee/';
	var contextPath = contextPath;

    var factory = {
        fetchAllEmployee: fetchAllEmployee,
        createEmployee: createEmployee,
        updateEmployee:updateEmployee,
        deleteEmployee:deleteEmployee,
        logout:logout
    };

    return factory;

    function fetchAllEmployee(params) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI,params)
            .then(
            function (response) {
                deferred.resolve(response);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function createEmployee(employee) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, employee)
            .then(
            function (response) {
                deferred.resolve(response);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }


    function updateEmployee(id,employee) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, employee)
            .then(
            function (response) {
                deferred.resolve(response);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function deleteEmployee(id) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+id)
            .then(
            function (response) {
                deferred.resolve(response);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    function logout(){
		   $http.post(contextPath+'/logout', {}).then(
			       function (response) {
			    	   if (response.status ==200 && response.statusText=='OK') {
			                window.location.replace(contextPath);
			            }else {
			                console.log('Access denied');
			            }
			       },
			       function(errResponse){
			           console.error('Something wrong!');
			       }
			 );
		 }

}]);
