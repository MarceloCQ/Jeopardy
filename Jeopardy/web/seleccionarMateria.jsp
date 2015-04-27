<%-- 
    Document   : seleccionarMateria
    Created on : 25/04/2015, 11:25:27 AM
    Author     : Marcelo
--%>

<%@page import="beans.Materia"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            ArrayList<Materia> materias = (ArrayList<Materia>) request.getAttribute("materias");
        %>

        <title>Crear Perfil</title>
    </head>
    <body>
        <h1>Crear Perfil</h1>
        
        <form action="ControladorJugar?operacion=matSelecc" method="POST">
            Nombre del perfil: <br><input type="text" name="nombrePerfil" value="" /><br><br>
            Materia a jugar: <br>
            <select name="materia">
                <%
                    for (int i = 0; i < materias.size(); i++) {
                %>
                <option value="<%=materias.get(i).getId()%>"><%=materias.get(i).getNombre()%></option>
                <%
                    }
                %>
            </select><br><br>
            <input type="submit" value="Siguiente" />
        </form>

    </body>
</html>
