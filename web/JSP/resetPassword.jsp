<%-- 
    Document   : resetPassword
    Created on : Oct 15, 2016, 7:42:20 AM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="../bower_components/semantic/dist/semantic.min.css">

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
              max-width: 500px;
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
                                        },
                                        confirmPassword: {
                                            identifier: 'confirm-password',
                                            rules: [
                                                {
                                                    type: 'empty',
                                                    prompt: 'Please enter your password'
                                                },
                                                {
                                                    type: 'match[password]',
                                                    prompt: 'Passwords do not match'
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
            <div class="ui middle aligned center aligned grid" style="margin-top: 120px">
                <div class="column">
                    <h2 class="ui inverted header">
                        <div class="content">
                            Enter new password
                        </div>
                    </h2>
                    <div class="ui message">
                        <h4>
                            Enter new password for <br>
                            ${param.email}
                        </h4>
                    </div>
                    <form class="ui form" action="../ResetPasswordServlet.do" method="post">
                        <input type="hidden" value="${param.email}" name="email">
                        <input type="hidden" value="${param.vcode}" name="vcode">
                        <div class="ui stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="password" placeholder="New Password">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="confirm-password" placeholder="Confirm Password">
                                </div>
                            </div>
                            <div class="ui fluid large blue submit button">Reset Password</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
