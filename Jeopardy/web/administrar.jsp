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
        <%
            if (session.getAttribute("user") == null) { %>
                <jsp:forward page="login.jsp" />                
        <%  } 
            if (request.getAttribute("materias") == null){ %>
                 <jsp:forward page="inicio.jsp" /> 
         <%   } %>
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración de Pistas</title>
        <link rel="StyleSheet" type="text/css" href="css/administrar.css"/>
        <script src="scripts/administrar.js"></script>
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
                <div style="display: none" id="materiaSelecc">
                    <table border="1" id="tablaMaterias">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Editar</th>
                                <th>Borrar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td id="tdNombreMateria"></td>
                                <td><a onclick="editarMateria()" href="#" >Editar</a></td>
                                <td><a onclick="eliminarMateria()" href="#" >Eliminar</a></td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
            <div id="seccionCategorias" style="display:none">
                <h2>Categorias</h2>
                Buscar: <input type="text" onkeyup="buscarCategorias(this.value)" id="categoria" value="" />
                <input type="button" onclick="agregarCategoria()" value="Agregar categoria" /><br><br>

                <select onchange="categoriaSeleccionada()" style="width: 300px" id="listaCategorias" size="5">         
                </select><br><br>
                <div style="display: none" id="categoriaSelecc">
                    <table border="1" id="tablaCategorias">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Editar</th>
                                <th>Borrar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td id="tdNombreCategoria"></td>
                                <td><a onclick="editarCategoria()" href="#" >Editar</a></td>
                                <td><a onclick="eliminarCategoria()" href="#" >Eliminar</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="seccionPistas" style="display: none">
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
                    <select onchange="preguntaSeleccionada()" style="width: 300px" id="listaPistas" size="5">                      
                    </select><br><br>
                    <div style="display: none" id="pistaSelecc">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>Pregunta</th>
                                    <th>Pista</th>
                                    <th>Puntos</th>
                                    <th>Editar</th>
                                    <th>Borrar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td style="width:100px" id="tdPregunta"></td>
                                    <td style="width:150px" id ="tdPista"></td>
                                    <td style="text-align: center" id ="tdPuntos"></td>
                                    <td ><a onclick="editarPregunta()" href="#" >Editar</a></td>
                                    <td><a onclick="eliminarPregunta()" href="#" >Eliminar</a></td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                </div>

            </div>

        </div>
                <br><br><br>
                <a href="inicio.jsp">[Regresar a Inicio]</a>

</html>
