'use strict';
app.factory('infoService', ['$http', '$q','contextPath', function($http, $q,contextPath){

	var REST_SERVICE_URI = contextPath+'/info/';
	var contextPath = contextPath;

    var factory = {
        fetchAllDetails: fetchAllDetails,
        updateInfo:updateInfo,
        deleteInfo:deleteInfo
    };

    return factory;

    function fetchAllDetails(params) {
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

    function updateInfo(id,info) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+id, info)
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

    function deleteInfo(id) {
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

}]);
