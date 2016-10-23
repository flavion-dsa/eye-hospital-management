<%-- 
    Document   : forgotPassword
    Created on : Oct 15, 2016, 11:05:54 AM
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
                            Reset your password
                        </div>
                    </h2>
                    <form class="ui form" action="../ForgotPasswordServlet.do" method="post">
                        <div class="ui stacked segment">
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="user icon"></i>
                                    <input type="text" name="email" placeholder="Username">
                                </div>
                            </div>
                            <div class="ui fluid large blue submit button">Send verification</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>