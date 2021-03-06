var app = angular.module('appPortal',['ngRoute','datatables','datatables.bootstrap']);
app.value('contextPath', window.location.origin+"/myportal");
app.config(function($routeProvider) {
    $routeProvider
    .when("/home", {
        templateUrl : "/myportal/views/home.html"
    })
    .when("/send", {
        templateUrl : "/myportal/views/sendMessage.html"
    })
    .when("/add", {
        templateUrl : "/myportal/views/addMobile.html"
    })
    .when("/info", {
        templateUrl : "/myportal/views/info.html"
    })
    .otherwise({
        redirectTo: '/home'
    });
});
