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
                    .ready(function() {    
                        $('.ui.checkbox').checkbox();
                        
            })
            ;
            function prescribe(patient) {
                document.getElementById("patient-name").value = patient; 
                $('#prescribe-modal').modal('show');
            }
            
        </script>
        
    </head>
    <body>
        <table class="ui center aligned striped table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Appointment Date</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="appointment" items="${requestScope.appointmentList}">
                    <tr>
                        <td><c:out value="${appointment.patientName}"></c:out></td>
                        <td><c:out value="${appointment.appointmentDate}"></c:out></td>
                        <td>
                            <div class="ui yellow button" onclick="prescribe('${appointment.patientName}')">Prescribe</div>
                            <div class="ui blue button">Info</div>
                        </td>
                        </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="ui small modal" id="prescribe-modal">
            <i class="close icon"></i>
            <div class="header">
                Prescribe
            </div>
            <div class="content">
                <form class="ui form" action="PrescribeServlet.do" method="post">
                    <div class="ui stacked segment">
                        <div class="inline field">
                            <label>Patient Name</label>
                            <input type="text" name="patientName" id="patient-name" value=" " readonly="">
                        </div>
                        <c:forEach var="medicine" items="${requestScope.medicineList}">
                        <div class="inline field">
                            <div class="ui checkbox">
                                <input type="checkbox" class="hidden">
                                <label><c:out value="${medicine.name}"></c:out></label>
                            </div>
                        </div>
                        </c:forEach>
                        <input type="submit" class="ui fluid green submit button" value="Prescibe">
                    </div>
                </form>
            </div>
        </div>
        <div class="ui small modal" id="info-modal">
            <div class="header">
                Info
            </div>
            <div class="content">
                
            </div>
        </div>
    </body>
</html>
