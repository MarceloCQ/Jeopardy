<%-- 
    Document   : inicio
    Created on : 23/04/2015, 11:18:47 AM
    Author     : Marcelo
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "datos" scope= "session" class = "basededatos.DBHandler" />
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session.getAttribute("user") == null) { %>
        <jsp:forward page="login.jsp" />                
        <%  }%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="StyleSheet" type="text/css" href="css/inicio.css"/>
        <title>Inicio</title>
    </head>
    <body>
    <center>
        <h1>Jeopardy!</h1>
        <table>
            <tr>
                <td align="center" onclick="location.href='ControladorJugar?operacion=inicio';">Jugar</td>
            </tr>
            <tr>
                <td align="center" onclick="location.href='ControladorAdministrar?operacion=inicializacion';">Administrar Pistas</td>
            </tr>
            <tr>
                <td align="center" onclick="location.href='ControladorJugar?operacion=historial';">Historial de Juegos</td>
            </tr>
            <tr>
                <td align="center" onclick="location.href='ControladorLogIn?operacion=cerrarSesion';" style="font-family: 'Trebuchet MS', Arial, Helvetica, sans-serif; font-size: 40px;  background: red">Cerrar Sesion</td>
            </tr>
        </table>
    </center>
    </body>
</html>
