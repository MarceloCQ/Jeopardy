<%-- 
    Document   : seleccionarJugadores
    Created on : 26/04/2015, 12:02:13 PM
    Author     : Marcelo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seleccionar Jugadores</title>
        <script src="scripts/seleccionarJugadores.js"></script>
    </head>
    <body>
        <h1>Seleccionar Jugadores</h1>
        <form action="ControladorJugar?operacion=jugadoresSelecc" method="POST">
            <div id="jugadores">
                <span>Jugador #1: </span><input type="text" name="jugador" value="" /><br><br>
            </div>
            <input type="button" onclick="nuevoJugador()" value="+" />
            <input type="submit" value="Jugar" />
        </form>
    </body>
</html>
