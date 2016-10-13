<%-- 
    Document   : register
    Created on : Oct 11, 2016, 10:05:53 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
                                        firstName: {
                                            identifier: 'first-name',
                                            rules: [
                                                {
                                                    type: 'empty',
                                                    prompt: 'Please enter your first name'
                                                }
                                            ]
                                        },
                                        lastName: {
                                            identifier: 'last-name',
                                            rules: [
                                                {
                                                    type: 'empty',
                                                    prompt: 'Please enter your last name'
                                                }
                                            ]
                                        },
                                        phone: {
                                            identifier: 'phone',
                                            rules: [
                                                {
                                                    type: 'empty',
                                                    prompt: 'Please enter your contact number'
                                                },
                                                {
                                                    type: 'regExp[/^[7-9][0-9]{9}$/]',
                                                    prompt: 'Please enter a valid number'
                                                }
                                            ]
                                        },
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

                        $('.ui.checkbox')
                                .checkbox()
                                ;
                    })
                    ;
        </script>
        <script src='https://www.google.com/recaptcha/api.js'></script>
    </head>
    <body>
        <div class="pusher">
            <div class="ui middle aligned center aligned grid" style="margin-top: 120px">
                <div class="column">
                    <h2 class="ui inverted image header">
                        <div class="content">
                            Create your account
                        </div>
                    </h2>
                    <form class="ui form" action="../RegisterServlet.do" method="post">
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
                                    <input type="password" name="password" placeholder="Password">
                                </div>
                            </div>
                            <div class="field">
                                <div class="ui left icon input">
                                    <i class="lock icon"></i>
                                    <input type="password" name="confirm-password" placeholder="Confirm Password">
                                </div>
                            </div>
                            <div class="g-recaptcha" align="center"
                                 data-sitekey="6Ldy2QgUAAAAAKlIbdfRRyZ7G8Y-IHITYy9CMrF-"
                                 style="margin-bottom: 2em;"></div>

                            <div class="ui fluid large blue submit button">Register</div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
