/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function verificarIgualdad(){
    var original = document.getElementById("original").value;
    var repeticion = document.getElementById("repeticion").value;
    var div = document.getElementById("igualono");
    
    if (original != repeticion){
        div.style = "color:red";
        div.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div.innerHTML += " La contraseña ingresada no es igual.";
    }
    else{
        div.style = "color:green";
        div.innerHTML = "<img src='checkmark.png' height='10' width='10'>";
        div.innerHTML += " La contraseña es igual.";
    }
}

function verificarUsuarioNuevo() {
    
}