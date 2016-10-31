<%-- 
    Document   : verify
    Created on : Oct 10, 2016, 9:23:22 PM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify</title>
        <link rel="stylesheet" type="text/css" href="bower_components/semantic/dist/semantic.min.css">
        
	<script src="bower_components/jquery/dist/jquery.min.js"></script>
	<script src="bower_components/semantic/dist/semantic.min.js"></script>
	<script src="bower_components/semantic/dist/components/sidebar.min.js"></script>
	<script src="bower_components/semantic/dist/components/transition.min.js"></script>

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
          .ready(function() {
            $('.ui.form')
              .form({
                fields: {
                  passcode: {
                    identifier  : 'passcode',
                    rules: [
                      {
                        type   : 'empty',
                        prompt : 'Please enter your verification code'
                      }
                    ]
                  }
                },
                inline : true,
                on     : 'blur'
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
                          Verify your Account
                      </div>
                  </h2>
                  <div class="ui message">
                      <h4>
                          Verification code has been sent to <br>
                          <jsp:useBean id="user" class="org.crce.wtlabs.dto.User" scope="application"></jsp:useBean>
                          <jsp:getProperty name="user" property="name"></jsp:getProperty>
                      </h4>
                  </div>
                  <form class="ui form" action="VerifyServlet.do" method="post">
                      <div class="ui stacked segment">
                          <div class="field">
                              <div class="ui left icon input">
                                  <i class="lock icon"></i>
                                  <input type="text" name="passcode" placeholder="Verification Code">
                              </div>
                          </div>
                          <div class="ui fluid large blue submit button">Verify</div>
                      </div>
                  </form>
                </div>
            </div>
        </div>
    </body>
</html>
