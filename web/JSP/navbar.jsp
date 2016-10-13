<%-- 
    Document   : navbar
    Created on : Oct 11, 2016, 10:28:06 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <div class="ui sticky fixed secondary inverted blue menu" 
             style="padding: 10px; height: 60px;">

            <div class="right menu" style="padding-right: 32px;">
                <div class="item">
                    Logged in as 
                    <jsp:useBean id="user" class="org.crce.wtlabs.dto.User" scope="session"></jsp:useBean>
                    <jsp:getProperty name="user" property="name"></jsp:getProperty>
                </div>
            </div>      
        </div>
