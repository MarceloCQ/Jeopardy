<%-- 
    Document   : cambioContrasena
    Created on : 21/04/2015, 08:03:44 PM
    Author     : Marcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="cambioContrasena.js"></script>
        <%
            //Se saca el mensaje si es que lo hay
            String mensaje = "";
            if (request.getAttribute("mensaje") != null){
                mensaje = "<p style='color:red'>" +  request.getAttribute("mensaje") + "</p>";
                
            }           
        %>
    </head>
    <body>
    <center>
        <h1>Jeopardy</h1>
        <h2>Cambiar Contraseña</h2>
        <div><%=mensaje%></div>
        <form action="Controlador" method="POST">
            <input type="hidden" name="operacion" value="cambioContra"/>           
            <table border="0">
                <tr>
                    <td>Contraseña anterior: </td><td><input type="password" name="oldpassword" value="" /><br></td>
                </tr>
                <tr>
                    <td>Contraseña nueva: </td><td><input id="original" oninput="verificarIgualdad()" type="password" name="newpassword" value="" /></td>
                </tr>
                <tr>
                    <td>Repetir contraseña: </td>
                    <td><input type="password" id="repeticion" oninput="verificarIgualdad()" name="newpassword2" value="" /></td>
                    <td><div id="igualono"></div></td>
            
                
                </tr>
            </table>
            <br>
            <input type="submit" value="Cambiar contraseña" />
        </form>
    </center>
</body>
</html>
