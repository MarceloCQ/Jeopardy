<%-- 
    Document   : seleccionarJuego
    Created on : 27/04/2015, 05:31:53 PM
    Author     : Marcelo
--%>

<%@page import="beans.Juego"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session.getAttribute("user") == null) { %>
                <jsp:forward page="login.jsp" />                
        <%  } 
            if (request.getAttribute("juegos") == null){ %>
                 <jsp:forward page="inicio.jsp" /> 
         <%   } %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            ArrayList<Juego> juegos = (ArrayList<Juego>) request.getAttribute("juegos");
        %>
        <title>Historial de Juegos</title>
    </head>
    <body>
        <h1>Historial de Juegos</h1>
        <p>Favor de seleccionar un juego: </p>
        <ul type="circle">
            <% for (Juego j : juegos) { 
                String href = "ControladorJugar?operacion=juegoSelecc&id=" + j.getId();
            %>
            <li><a href="<%=href%>"><%=j.getFecha().toString()%></a></li>
            <% } %>
        </ul>

        
        
    </body>
</html>
