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
        
        <meta http-equiv="Cache-Control" content="private, max-age=0, no-cache">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        
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
        <div class="ui small modal" id="update-patient">
            <i class="close icon"></i>
            <div class="header">
                Add Patient
            </div>
            <div class="content">
                <form class="ui form" action="../AddPatientServlet.do" method="post">
                    <div class="ui stacked segment">
                        <div class="two fields">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="first-name" placeholder="First Name">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon disabled input">
                                    <i class="user icon"></i>
                                    <input type="text" name="last-name" placeholder="Last Name">
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="phone icon"></i>
                                <input type="text" name="phone" placeholder="Contact Number">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="at icon"></i>
                                <input type="text" name="email" placeholder="E-mail address">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" name="password" placeholder="Password">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="lock icon"></i>
                                <input type="password" name="confirm-password" placeholder="Confirm Password">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="actions">
                <div class="ui green approve button">Add</div>
                <div class="ui red deny button">Cancel</div>
            </div>
        </div>
    </body>
</html>
