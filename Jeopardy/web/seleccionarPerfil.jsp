<%-- 
    Document   : seleccionarPerfil
    Created on : 26/04/2015, 09:52:20 PM
    Author     : Marcelo
--%>

<%@page import="beans.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            if (session.getAttribute("user") == null) { %>
                <jsp:forward page="login.jsp" />                
        <%  } 
            if (request.getAttribute("perfiles") == null){ %>
                 <jsp:forward page="inicio.jsp" /> 
         <%   } %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
           ArrayList<Perfil> perfiles = (ArrayList<Perfil>) request.getAttribute("perfiles");
        %>
        <title>Seleccionar Perfil</title>
    </head>
    <body>
        <h1>Seleccionar Perfil</h1>
        <form action="ControladorJugar?operacion=perfilSelecc" method="POST">
            Porfavor selecciona un perfil: <br>
            <select name="perfil">
                <%
                    for (Perfil p : perfiles) {
                %>
                <option value="<%=p.getId()%>"><%=p.getNombre()%></option>
                <%
                    }
                %>
            </select><br><br>
            <input type="submit" value="Siguiente" />
        </form>
            <br><br>
            <a href='ControladorJugar?operacion=nuevoPerfil'>Crear Perfil</a>    
            
            
        
    </body>
</html>
