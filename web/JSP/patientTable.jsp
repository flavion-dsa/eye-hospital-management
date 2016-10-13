<%-- 
    Document   : patientTable
    Created on : Oct 12, 2016, 12:58:36 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <link rel="stylesheet" type="text/css" href="bower_components/semantic/dist/semantic.min.css">

        <script src="bower_components/jquery/dist/jquery.min.js"></script>
        <script src="bower_components/semantic/dist/semantic.min.js"></script>
        <script src="bower_components/semantic/dist/components/sidebar.min.js"></script>
        <script src="bower_components/semantic/dist/components/transition.min.js"></script>

    </head>
    <body>
        <table class="ui center aligned striped table">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Contact</th>
                    <th>Email</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="patient" items="${requestScope.patientList}">
                    <tr>
                        <td><c:out value="${patient.firstName}"></c:out></td>
                        <td><c:out value="${patient.lastName}"></c:out></td>
                        <td><c:out value="${patient.contact}"></c:out></td>
                        <td><c:out value="${patient.email}"></c:out></td>
                            <td><div class="ui red button">Remove</div></td>
                            <td><div class="ui yellow button">Update</div></td>
                            <td><div class="ui blue button">Info</div></td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="ui right aligned segment">
            <div class="ui green button">Add Patient</div>
        </div>
    </body>
</html>
