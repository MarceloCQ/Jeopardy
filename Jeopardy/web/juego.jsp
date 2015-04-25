<%-- 
    Document   : Juego
    Created on : 24/04/2015, 11:32:30 PM
    Author     : Marcelo
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="beans.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jeopardy!</title>
        <link rel="StyleSheet" type="text/css" href="css/juego.css"/>
        <%
            Perfil perfil = (Perfil) session.getAttribute("perfil");
            Materia materia = perfil.getMateria();
            ArrayList<Categoria> categorias = perfil.getCategorias();
            
        %>
        
        
    </head>
    <body>
        <div id="wrapper">
            <div id="tablero">
                <h1 align="center"><%= materia.getNombre() %></h1>
            </div>
            
            <div id="score">con</div>
        </div>
    </body>
</html>
