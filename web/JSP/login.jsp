<%-- 
    Document   : login
    Created on : Oct 11, 2016, 10:01:28 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${sessionScope.user.type == 0}">
        <c:redirect url="admin.jsp"></c:redirect>
    </c:when>
    <c:when test="${sessionScope.user.type == 1}">
        <c:redirect url="patient.jsp"></c:redirect>
    </c:when>
    <c:when test="${sessionScope.user.type == 2}">
        <c:redirect url="doctor.jsp"></c:redirect>
    </c:when>
    <c:when test="${sessionScope.user.type == 3}">
        <c:redirect url="chemist.jsp"></c:redirect>
    </c:when>
</c:choose>
<%@include file="cacheControl.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Log in</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <meta http-equiv="Cache-Control" content="private, max-age=0, no-cache">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">

        <link rel="stylesheet" href="../bower_components/semantic/dist/semantic.min.css">

        <script src="../bower_components/jquery/dist/jquery.min.js"></script>
        <script src="../bower_components/semantic/dist/semantic.min.js"></script>
        <script src="../bower_components/semantic/dist/components/sidebar.min.js"></script>
        <script src="../bower_components/semantic/dist/components/transition.min.js"></script>

        <style type="text/css">
            body {
                background-color: #1B1C1D;
            }
            body > .grid {
                height: 100%;
                margin-top: 1em;
            }
            .image {
                margin-top: -100px;
            }
            .column {
                max-width: 450px;
            }

            .ui.message h4 {
                font-style: italic;
            }
        </style>
        
        <script>
            $(document)
                    .ready(function () {
                        $('.ui.form')
                                .form({
                                    fields: {
                                        email: {
                                            identifier: 'email',
                                            rules: [
                                                {
                                                    type: 'empty',
                                                    prompt: 'Please enter your e-mail'
                                                },
                                                {
                                                    type: 'email',
                                                    prompt: 'Please enter a valid e-mail'
                                                }
                                            ]
                                        },
                                        password: {
                                            identifier: 'password',
                                            rules: [
                                                {
                                                    type: 'empty',
                                                    prompt: 'Please enter your password'
                                                },
                                                {
                                                    type: 'length[6]',
                                                    prompt: 'Your password must be at least 6 characters'
                                                }
                                            ]
                                        }
                                    },
                                    inline: true,
                                    on: 'blur'
                                })
                                ;
                    })
                    ;
        </script>

    </head>
    <body>
        
        <div class="pusher">
            <div class="ui middle aligned center aligned grid" style="margin-top: 100px">
                <div class="column">
                    <h2 class="ui inverted header">
                        <div class="content">
                            Log-in to your account
                        </div>
                    </h2>
                    
                    <form class="ui form" action="../LoginServlet.do" method="post">
                        <div class="ui stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="email" placeholder="E-mail address">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="password" placeholder="Password">
                                </div>
                            </div>
                            <a href="forgotPassword.jsp">Forgot password ?</a>
                            <br><br>
                            <div class="ui fluid large blue submit button">Sign in</div>
                            <div class="ui horizontal divider">or</div>
                            <a class="ui fluid large google plus labeled icon button" href="../GoogleLoginServlet.do">
                                <i class="google plus icon"></i>
                                Sign in with Google +
                            </a>
                            <br>

                            <fb:login-button 
                                scope="public_profile,email"
                                onlogin="checkLoginState();">
                            </fb:login-button>
                            <div class="ui large fluid facebook labeled icon button">
                                <i class="facebook f icon"></i>
                                Sign in with Facebook
                            </div>
                        </div>
                    </form>
                    <div class="ui message">
                        <h4>Don't have an account? Create it now!</h4>
                        <button class="ui large orange fluid button"
                                onclick="window.location.href = 'register.jsp'">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
