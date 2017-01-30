<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head lang="es">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema de gesti&oacute;n de competencias</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext4/resources/css/ext-all-neptune-debug.css">
         <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext4/resources/css/dataView.css">
        <script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/ext4/ext-all-debug.js"></script>
        <script type="text/javascript" charset="UTF-8" src="<%=request.getContextPath()%>/app.js">
            
        </script>
    </head>
    <body>
        <script>
           guardarUser('<% out.print(request.getSession().getAttribute("rol").toString()); %>','<% out.print(request.getSession().getAttribute("username").toString()); %>');
           
        </script>
    </body>

   
</html>
