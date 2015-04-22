<%-- 
    Document   : registrar
    Created on : 21-abr-2015, 22:37:16
    Author     : eduardocristerna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro - Jeopardy</title>
        <script src="registrar.js"></script>
    </head>
    
        <%
            //Se saca el mensaje si es que lo hay
            String mensaje = "";
            if (request.getAttribute("mensaje") != null){
                mensaje = "<p style='color:red'>" +  request.getAttribute("mensaje") + "</p>";
                
            }           
        %>
    <body>
    <center>
        <h1>Jeopardy</h1>
        <h2>Registro</h2>
        <form action="ControladorRegistro" method="POST">    
            <input type="hidden" name="operacion" value="registrar"/> 
            <table border="0">
                <tr>
                    <td>Usuario : </td>
                    <td><input type="text" oninput="verificarUsuarioNuevo()" id="nombreUsuario" name="username" value="" /></td>
                    <td><div id="igualono"></div></td>
                </tr>
                <tr>
                    <td>E-mail : </td>
                    <td><input type="text" name="email" value="" /><br></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Registrar" />
        </form>
        <div id="mensaje"></div>
        <div><%=mensaje%></div>
    </center>
    </body>
</html>
