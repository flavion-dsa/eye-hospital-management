<%-- 
    Document   : doctorDashboard
    Created on : Oct 12, 2016, 11:06:37 AM
    Author     : Flav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            .tabular.menu {
                
            }
        </style>
        <script>
            $(document)
                    .ready(function() {
                        $('.tabular.menu .item').tab();
            })
            ;
        </script>
    </head>
    <body>
        
    </body>
</html>
