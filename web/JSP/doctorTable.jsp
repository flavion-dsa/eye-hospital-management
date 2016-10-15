<%-- 
    Document   : doctorTable
    Created on : Oct 12, 2016, 1:51:44 PM
    Author     : Flav
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <script>
            $(document)
                .ready(function() {
                    
                    $('#add-doctor')
                        .on('click', function() {
                            $('.ui.modal')
                                .modal({
                                    closable	: false,
                                    onApprove	: function() {
                                            $('.ui.modal').modal('hide');
                                    }
                                })
                                .modal('show');
                    })
                    ;
            })
            ;
        </script>
    </head>
    <body>
        <table class="ui center aligned striped table">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Contact</th>
                    <th>Email</th>
                    <th>Qualification</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="doctor" items="${requestScope.doctorList}">
                    <tr>
                        <td><c:out value="${doctor.firstName}"></c:out></td>
                        <td><c:out value="${doctor.lastName}"></c:out></td>
                        <td><c:out value="${doctor.contact}"></c:out></td>
                        <td><c:out value="${doctor.email}"></c:out></td>
                        <td><c:out value="${doctor.qualification}"></c:out></td>
                        <td><div class="ui red button">Remove</div></td>
                        <td><div class="ui yellow button">Edit</div></td>
                        <td><div class="ui blue button">Info</div></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="ui right aligned segment">
            <div class="ui green button" id="add-doctor">Add Doctor</div>
        </div>
        <div class="ui small modal">
            <i class="close icon"></i>
            <div class="header">
                Add a Doctor
            </div>
            <div class="content">
                <form class="ui form" action="AddDoctorServlet.do" method="post">
                    <div class="ui stacked segment">
                        <div class="two fields">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="first-name" placeholder="First Name">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
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
                                <input type="text" name="password" placeholder="Default Password">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="student icon"></i>
                                <input type="text" name="qualification" placeholder="Qualification">
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui left icon input">
                                <i class="money icon"></i>
                                <input type="text" name="salary" placeholder="Salary">
                            </div>
                        </div>
                        <input type="submit" class="ui fluid green submit button" value="Add">
                    </div>
                </form>
            </div>
        </div>
        
    </body>
</html>
