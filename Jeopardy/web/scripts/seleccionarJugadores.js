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
    div.appendChild(document.createElement("br"));
    div.appendChild(document.createElement("br"));
    
    
}

function validarForma(){
    var elementos = document.getElementsByName("jugador");
    var correcto = true;
    for (var i = 0; i < elementos.length; i++){
        if (elementos[i].value == ""){
            correcto = false;
        }
    }
    
    if (!correcto){
        alert("Favor de llenar todas las casillas");
        return false;
    }
    else{
        return true;
    }
    
    
    
}


