<%-- 
    Document   : doctor
    Created on : Oct 12, 2016, 3:08:53 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor</title>
        <link rel="stylesheet" type="text/css" href="bower_components/semantic/dist/semantic.min.css">

        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <script src="bower_components/semantic/dist/semantic.min.js"></script>
        <script src="bower_components/semantic/dist/components/sidebar.min.js"></script>
        <script src="bower_components/semantic/dist/components/transition.min.js"></script>

        <style>
            .sidebar.menu {
                width: 15% !important;
            }
            .sidebar.menu .item {
                margin-top: 48px;
                margin-bottom: 48px;
            }
        </style>
    </head>
    <body>
        <div class="ui visible sidebar inverted vertical labeled icon borderless menu">
            <a class="item">
                <i class="user icon"></i>
                Patient
            </a>
            <a class="item">
                <i class="treatment icon"></i>
                Diagnosis
            </a>
            <a class="item">
                <i class="first aid icon"></i>
                Pharmacy
            </a>
            <a class="item" href="LogOutServlet.do">
                <i class="sign out icon"></i>
                Sign Out
            </a>
        </div>
        <div class="pusher">
            <%@include file="navbar.jsp" %>
        </div>
    </body>
</html>
