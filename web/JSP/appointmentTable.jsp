<%-- 
    Document   : appointmentTable
    Created on : Oct 14, 2016, 5:43:12 PM
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
        
        <script>
            $(document)
                    .ready(function {
                        
                        $('.ui.yellow.button')
                            .on('click', function(name) {
                                $('.ui.modal')
                                    .show()
                                ;
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
                    <th>Name</th>
                    <th>Appointment Date</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${requestScope.appointmentList}">
                    <tr>
                        <td><c:out value="${appointment.patientName}"></c:out></td>
                        <td><c:out value="${appointment.appointmentDate}"></c:out></td>
                        <td><div class="ui red button">Done</div></td>
                        <td><div class="ui yellow button">Prescribe</div></td>
                        <td><div class="ui blue button">Info</div></td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
