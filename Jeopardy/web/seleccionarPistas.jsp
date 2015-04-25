<%-- 
    Document   : seleccionarPistas
    Created on : 25/04/2015, 12:27:23 PM
    Author     : Marcelo
--%>

<%@page import="beans.Pista"%>
<%@page import="beans.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
        %>
    </head>
    
        <h1>Seleccionar Preguntas</h1>
        Favor de seleccionar 5 preguntas para cada categoria
        <form action="ControladorJugar?operacion=pistaSelecc" method="POST">       
            <% for (Categoria c : categorias) {%>
                <h2><%=c.getNombre()%></h2>

                <% for (int i = 100; i <= 500; i += 100) {%>

                
                <%=i%> puntos: 
                <select name="pistas">
                    <%
                        for (Pista p : c.getPistas().get(i / 100 - 1)) { %>
                        <option value="<%=p.getId()%>"><%=p.getPregunta()%></option>
                    <%   }
                    %>
                </select><br><br>

                <% }
                
                }%>
                <input type="submit" value="Siguiente" />
                </form>


    
</html>
