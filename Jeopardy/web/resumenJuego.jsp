<%-- 
    Document   : resumenJuego
    Created on : 27/04/2015, 05:10:51 PM
    Author     : Marcelo
--%>

<%@page import="beans.Perfil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Juego"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Juego juego = (Juego)request.getAttribute("juego");
            String[] jugadores = (String[]) request.getAttribute("jugadores");
            ArrayList<Integer> scores = (ArrayList<Integer>) request.getAttribute("scores");
            Perfil perfil = (Perfil) request.getAttribute("perfil");
            
        %>
        
        <title>Resumen del Juego</title>
    </head>
    <body>
        <h1>Resumen del juego</h1>
        <table>
            <tr>
                <td style="font-weight:bold">Fecha: </td>
                <td><%= juego.getFecha().toString() %></td>
            </tr>
            <tr>
                <td style="font-weight:bold">Perfil: </td>
                <td><%= perfil.getNombre() %></td>
            </tr>
        </table>
            <p>Puntuaciones: </p>
            <table border="1" style="border-collapse: collapse">
                <tr>
                    <th style="font-weight:bold">Nombre</th>
                    <th style="font-weight:bold">Puntuacion</th>
                </tr>
                <% for (int i = 0; i < jugadores.length; i++) { %>
                <tr>
                    <td><%= jugadores[i] %></td>
                    <td align="center"><%= scores.get(i) %></td>
                </tr>
                <% } %>
            </table>
</html>
