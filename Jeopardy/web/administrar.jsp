<%-- 
    Document   : administrar
    Created on : 22/04/2015, 09:38:45 PM
    Author     : Marcelo
--%>

<%@page import="beans.Materia"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de Pistas</title>
        <script src="administrar.js"></script>
        <%
            ArrayList<Materia> materias = (ArrayList<Materia>) request.getAttribute("materias");
        %>
    </head>
    <body>
        <h1>Administración de Pistas</h1>
        <h2>Materias</h2>

        <input type="hidden" name="operacion" value="agregar"/>
        Nombre: <input type="text" onkeyup="buscarMaterias(this.value)" id="materia" name="materiaNombre" value="" />
        <input type="button" onclick="agregarMateria()" value="Agregar materia" /><br><br>

        <select style="width: 300px"id="listaMaterias" name="materias" size="5">
            <%
                for (Materia m : materias) {
            %>
            <option><%= m.getNombre()%></option>
            <%
                }
            %>
        </select>
    </body>
</html>
