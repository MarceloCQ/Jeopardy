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
        <link rel="StyleSheet" type="text/css" href="administrar.css"/>
        <script src="administrar.js"></script>
        <%
            ArrayList<Materia> materias = (ArrayList<Materia>) request.getAttribute("materias");
        %>
    </head>
    <body>
        <h1>Administración de Pistas</h1>
        <div id ="conjunto">
            <div id="seccionMaterias">
                <h2>Materias</h2>
                Buscar: <input type="text" onkeyup="buscarMaterias(this.value)" id="materia" name="materiaNombre" value="" />
                <input type="button" onclick="agregarMateria()" value="Agregar materia" /><br><br>

                <select onchange="materiaSeleccionada()" style="width: 300px"id="listaMaterias" name="materias" size="5">
                    <%
                        for (Materia m : materias) {
                    %>
                    <option value="<%= m.getId()%>"><%= m.getNombre()%></option>
                    <%
                        }
                    %>
                </select><br><br>
                <div id="materiaSelecc"></div>
            </div>
            <div id="seccionCategorias" style="display:none">
                <h2>Categorias</h2>
                Buscar: <input type="text" onkeyup="buscarCategorias(this.value)" id="categoria" value="" />
                <input type="button" onclick="agregarCategoria()" value="Agregar categoria" /><br><br>

                <select onchange="categoriaSeleccionada()" style="width: 300px" id="listaCategorias" size="5">         
                </select><br><br>
                <div id="categoriaSelecc"></div>
            </div>
            <div id="seccionPistas">
                <h2>Preguntas</h2>
                Buscar: <input type="text"  onkeyup="buscarPreguntas(this.value)" id="pregunta" value="" />
                <div id="divDesglose" style="display:none">
                    <br>
                    Respuesta: <textarea rows="3" cols="20"  id="respuesta"/></textarea><br><br>
                    Puntos: <select id="puntos">
                        <option>100</option>
                        <option>200</option>
                        <option>300</option>
                        <option>400</option>
                        <option>500</option>
                    </select><br><br>                     
                </div>

                <input type="button" onclick="agregarPreguntas()" value="Agregar pregunta" />
                <div id="buscarPistas">
                    <br>
                    <select onchange="categoriaSeleccionada()" style="width: 300px" id="listaPistas" size="5">                      
                    </select>
                    <div id="pistaSelecc"></div>
                </div>

            </div>

        </div>

</html>
