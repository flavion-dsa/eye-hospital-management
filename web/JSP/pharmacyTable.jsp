<%-- 
    Document   : pharmacyTable
    Created on : Oct 13, 2016, 11:54:48 AM
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
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Expiry Date</th>
                    <c:if test="${sessionScope.user.type == 3}">
                        <th></th>
                        <th></th>
                    </c:if>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="medicine" items="${requestScope.medicineList}">
                <tr>
                    <td><c:out value="${medicine.id}"></c:out></td>
                    <td><c:out value="${medicine.name}"></c:out></td>
                    <td><c:out value="${medicine.type}"></c:out></td>
                    <td><c:out value="${medicine.price}"></c:out></td>
                    <td><c:out value="${medicine.quantity}"></c:out></td>
                    <td><c:out value="${medicine.expiry}"></c:out></td>
                    <c:if test="${sessionScope.user.type == 3}">
                        <td><div class="ui red button">Remove</div></td>
                        <td><div class="ui yellow button">Update</div></td>
                    </c:if>
                    <td><div class="ui blue button">Info</div></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div class="ui right aligned segment">
        <div class="ui green button">Add Medicine</div>
    </div>
    </body>
</html>
