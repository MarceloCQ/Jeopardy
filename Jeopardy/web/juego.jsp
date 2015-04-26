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
                <div style="display: none" id="interfazPreg">
                    <div align="center" id="btnverpreg"><br><input onclick="verPregunta()" style="width: 200px; height: 50px; font-size: 20px" type="button" value="Ver Pregunta" /><br></div>   
                    <div align="center" id="divRespuesta" style="display:none">

                    </div>
                    <br>
                    <div align="center" id="divPregunta">

                    </div>

                    <div style="display:none" id="bienomal" align="center"><br><br>
                        <img onclick=" preguntaCorrecta()" height="100px" style="margin: 30px" width="100px" src="checkmark.png">
                        <img onclick="preguntaIncorrecta()" style="margin: 30px" height="100px" width="100px" src="crossmark.png">
                    </div>


                </div>
                <div id="preguntas">
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
                            <td class="nousada" id="<%= categorias.get(p).getPistas().get(0).get(i / 100 - 1).getId()%>" onclick="opSelecc(this)"><%=i%></td>
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
