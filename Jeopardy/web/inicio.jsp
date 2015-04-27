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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Jeopardy</h1>
        <a href="ControladorAdministrar?operacion=inicializacion">Administrar</a><br>
        <a href="ControladorJugar?operacion=inicio">Jugar</a><br>
        <a href="ControladorJugar?operacion=historial">Historial de Juegos</a><br>
    </body>
</html>
