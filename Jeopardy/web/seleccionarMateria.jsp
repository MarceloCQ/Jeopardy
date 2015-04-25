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

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Seleccionar materias</h1>
        Porfavor seleccionar una materia: <br><br>
        <form action="ControladorJugar?operacion=matSelecc" method="POST">
            <select name="materia">
                <%
                    for (int i = 0; i < materias.size(); i++) {
                %>
                <option value="<%=materias.get(i).getId()%>"><%=materias.get(i).getNombre()%></option>
                <%
                    }
                %>
            </select>
            <input type="submit" value="Siguiente" />
        </form>

    </body>
</html>
