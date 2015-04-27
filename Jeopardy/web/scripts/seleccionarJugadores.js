/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var cont = 1;

function nuevoJugador(){
    cont++;
    var div = document.getElementById("jugadores");
    var label = document.createElement("span");
    var input = document.createElement("input");
    input.name = "jugador";
    
    label.innerHTML = "Jugador #" + cont + ": ";
    
    div.appendChild(label);
    div.appendChild(input);
    div.innerHTML += "<br><br>";
    
}


