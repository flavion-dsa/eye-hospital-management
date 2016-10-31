<%-- 
    Document   : paymentTable
    Created on : Oct 27, 2016, 12:24:42 PM
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
        <div class="ui segment">
            <form class="ui form" method="post">
                <div class="ui inline fields">
                    <div class="ui six wide field">
                        <input type="text" name="email" placeholder="Enter patient email">
                    </div>
                    <div class="ui four wide field">
                        <button class="ui blue submit button">Create Bill</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="ui segment">
            <table class="ui center aligned striped table">
                <thead>
                    <tr>
                        <th>Patient Name</th>
                        <th>Date</th>
                        <th>Mode of Payment</th>
                        <th>Amount</th>
                        <th></th>
                    </tr>
                    <c:forEach var="payment" items="${requestScope.paymentList}">
                        <tr>
                            <td><c:out value="${payment.patientName}"></c:out></td>
                            <td><c:out value="${payment.paymentDate}"></c:out></td>
                            <td><c:out value="${payment.modeOfPayment}"></c:out></td>
                            <td><c:out value="${payment.fees}"></c:out></td>
                            <td><div class="ui blue button">Info</div></td>
                        </tr>
                    </c:forEach>
                </thead>
            </table>
        </div>
    </body>
</html>
