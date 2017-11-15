'use strict';
app.factory('loginService', ['$http', '$q','contextPath', function($http, $q,contextPath){
	var LOGIN_URI=contextPath+'/authenticate';
	var method='POST'; 
	var headers= {"Content-Type": "application/x-www-form-urlencoded;charset=utf-8;","X-Login-Ajax-call": 'true'};
	
	    var factory = {login: login};
	    return factory;
	    
	    function login(credentials) {
	        var deferred = $q.defer();
	        $http({
	            method: method,
	            url: LOGIN_URI,
	            data: credentials,
	            headers:headers
	        })
	        .then(function(response) {
	        	if (response.status ==200) {
	        		deferred.resolve(response.statusText);
	            }else if (response.status ==203)  {
	            	deferred.resolve(response.data);
	            }
	        },
	        function(errResponse){
	        	deferred.reject(errResponse);
	        });
	        return deferred.promise;
	    }
}]);