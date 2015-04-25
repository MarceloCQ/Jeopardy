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
        <script src="scripts/juego.js"></script>
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
                <div id="header">
                    <h1 align="center"><%= materia.getNombre()%></h1>
                </div>
                <div  style="display: table-cell;" id="divPregunta">
                    <h1 style="display: table-cell; vertical-align: central; text-align: center">Hola</h1>
                </div>
                <div id="preguntas" style="display:none">
                    <table border="1">
                        <tr>
                            <%
                                for (Categoria c : categorias) {%>
                            <th><%=c.getNombre()%></th>

                            <% }%>
                        </tr>
                        <% for (int i = 100; i <= 500; i += 100) {%>
                        <tr>
                            <% for (int p = 0; p < 6; p++) {%>
                            <td id="<%= categorias.get(p).getPistas().get(0).get(i / 100 - 1).getId()%>" onclick="opSelecc(this)"><%=i%></td>
                            <% }%>
                        </tr>
                        <% }%>
                    </table>
                </div>
            </div>

            <div id="score">
                <h1 align="center">Score</h1>
            </div>
        </div>
    </body>
</html>
