<%-- 
    Document   : doctor
    Created on : Oct 12, 2016, 3:08:53 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${sessionScope.user == null}">
    <c:redirect url="login.jsp"></c:redirect>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor</title>
        
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        
        <link rel="stylesheet" type="text/css" href="../bower_components/semantic/dist/semantic.min.css">

        <script src="../bower_components/jquery/dist/jquery.min.js"></script>
        <script src="../bower_components/semantic/dist/semantic.min.js"></script>
        <script src="../bower_components/semantic/dist/components/sidebar.min.js"></script>
        <script src="../bower_components/semantic/dist/components/transition.min.js"></script>

        <style>
            .sidebar {
                width: 150px;
            }
            .pusher {
                margin-left: 150px;
            }
            .sidebar .menu{
                width: inherit !important;
                height: inherit !important;
            }
            .sidebar .menu .item {
                margin-top: 48px;
                margin-bottom: 48px;
            }
            .internal.frame {
                margin-top: 60px;
            }
        </style>
    </head>
    <body>
        <div class="ui visible sidebar">
            <div class="ui inverted vertical labeled icon borderless fluid menu">
                <a class="item" id="dashboard-tab">
                    <i class="user icon"></i>
                    Dashboard
                </a>
                <a class="item" id="diagnosis-tab">
                    <i class="doctor icon"></i>
                    Doctors
                </a>
                <a class="item" id="appointments-tab" href="../DisplayAppointmentServlet.do" target="iframe">
                    <i class="clock icon"></i>
                    Appointments
                </a>
                <a class="item" href="../LogOutServlet.do">
                    <i class="sign out icon"></i>
                    Sign Out
                </a>
            </div>
        </div>
        <%@include file="navbar.jsp" %>
        <div class="pusher">
            <iframe class="internal frame" name="iframe" src="../DisplayAppointmentServlet.do" height="720px" width="100%"
                    scrolling="auto" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0">
        </div>
    </body>
</html>
