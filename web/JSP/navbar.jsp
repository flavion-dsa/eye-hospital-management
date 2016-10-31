<%-- 
    Document   : navbar
    Created on : Oct 11, 2016, 10:28:06 PM
    Author     : Flav
--%>
<script src="../bower_components/angular/angular.min.js"></script>
<script>
    var clock = angular.module('clock', []);
    clock.controller('clockControl', function($scope, $interval) {
        $scope.currentTime = new Date().toLocaleTimeString();
        $interval(function() {
            $scope.currentTime = new Date().toLocaleTimeString();
        }, 1000);
    });
</script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="ui sticky fixed secondary inverted blue menu" 
             style="padding: 10px; height: 60px;">
            
            <div class="ui inverted blue menu" style="margin-left: 150px;">
                <div class="item" ng-app="clock" ng-controller="clockControl">
                    {{currentTime}}
                </div>
            </div>
            <div class="right menu" style="padding-right: 10px;">
                <div class="item">
                    Welcome, &nbsp;
                    <jsp:useBean id="user" class="org.crce.wtlabs.dto.User" scope="session"></jsp:useBean>
                    <jsp:getProperty name="user" property="name"></jsp:getProperty>
                </div>
                <div class="item">
                    <a class="ui compact olive button">Edit profile</a>
                </div>
                <div class="item">
                    <a class="ui compact red button" href="../LogOutServlet.do">Log out</a>
                </div>
            </div>      
        </div>
