    <%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
       pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="es">
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/ext4/resources/css/style_login.css">
        <!--script type="text/javascript" src="<%=request.getContextPath()%>/ext4/ext-all-debug.js"></script>
        <!--script type="text/javascript" src="<%=request.getContextPath()%>/appLoggin.js"></script-->
    </head>
    <body>
        <!--h1>Credenciales por favor.</h1>
        <form action="j_spring_security_check" method="post">
            <input type="text" name="username" />
            <input type="password" name="password" />
            <input type="submit" value="Enviar este formulario" />
        </form-->

        <div id="login">

            <h1>Autenticaci&oacute;n</h1>
            <form action="j_spring_security_check" method="post">
                <input type="text" name="username" placeholder="Usuario..." />
                <input type="password" name="password" placeholder="Clave..." />
                <input type="submit" value="Aceptar" />
                <!--input type="submit" value="An&oacute;nimo"/-->
            </form>

        </div>

    </body>

</html>
