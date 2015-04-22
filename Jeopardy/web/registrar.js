/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function verificarUsuarioNuevo(){
    var input = document.getElementById("nombreUsuario");
    var valor = input.value;
    var url = "ControladorRegistro?operacion=verificar&userName="+valor;
    
    req = new XMLHttpRequest();   
    req.onload = desplegar;
    req.open("GET", url, true);
    req.send();
}

function desplegar(){
    var respuesta = req.responseText.trim();
    var div = document.getElementById("igualono");
    if (respuesta == "si"){
        div.style = "color:green";
        div.innerHTML = "<img src='checkmark.png' height='10' width='10'>";
        div.innerHTML += " Este usuario está disponible";
    }
    else if (respuesta = "no"){
        div.style = "color:red";
        div.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div.innerHTML += " Este usuario no está disponible";
    }
    
}
