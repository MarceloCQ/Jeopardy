<%-- 
    Document   : login
    Created on : 21/04/2015, 04:31:38 PM
    Author     : Marcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Jeopardy</title>
    </head>
    <body>
    <center>
        <h1>Jeopardy</h1>
        <h2>Iniciar Sesión</h2>
        <form action="Controlador" method="POST">
            <input type="hidden" name="operacion" value="login"/>           
            <table border="0">
                <tr>
                    <td>Usuario : </td><td><input type="text" name="username" value="" /><br></td>
                </tr>
                <tr>
                    <td>Contraseña : </td><td><input type="password" name="password" value="" /></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Iniciar Sesión" />
        </form>
    </center>
    </body>
</html>
