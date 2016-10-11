<%-- 
    Document   : navbar
    Created on : Oct 11, 2016, 11:46:59 AM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="ui fixed sticky secondary inverted blue menu" 
     style="padding: 10px; height: 10%">

    <div class="right menu" style="padding-right: 32px;">
        <div class="item">
            Logged in as 
            <jsp:useBean id="user" class="org.crce.wtlabs.dto.User" scope="session"></jsp:useBean>
            <jsp:getProperty name="user" property="name"></jsp:getProperty>
        </div>
    </div>      
</div>
