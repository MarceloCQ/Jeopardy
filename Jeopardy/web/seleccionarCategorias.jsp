<%-- 
    Document   : seleccionarCategorias
    Created on : 25/04/2015, 11:57:21 AM
    Author     : Marcelo
--%>

<%@page import="beans.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seleccionar Categorias</title>
        <script src="scripts/seleccionarCategorias.js"></script>
        <%
            ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
        %>
    </head>
    <body>
        <h1>Seleccionar Categorias</h1>
        Porfavor seleccionar 6 categorias: <br><br>
        <form onsubmit="return validarSeleccion()" action="ControladorJugar?operacion=catSelecc" method="POST">
            <select style="width: 300px" name="categorias" id="cats" size="6" multiple>
                <%
                    for (int i = 0; i < categorias.size(); i++) {
                %>
                <option value="<%=categorias.get(i).getId()%>"><%=categorias.get(i).getNombre()%></option>
                <%
                    }
                %>
            </select><br><br>
            <input type="submit" value="Siguiente" />
        </form>
    </body>
</html>
