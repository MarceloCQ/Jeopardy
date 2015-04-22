/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function verificarUsuarioNuevo(){
    var input = document.getElementById("nombreUsuario");
    var valor = input.value;
    var url = "ControladorRegistro?operacion=verificar&userName="+valor;
    var boton = document.getElementById("btnregistrar");
    
    if (valor == ""){
        var div = document.getElementById("igualono");
        div.style = "color:red";
        div.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div.innerHTML += " Favor de no dejar en blanco esta casilla";
        boton.disabled = true;
    }
    else{
        req = new XMLHttpRequest();   
        req.onload = desplegar;
        req.open("GET", url, true);
        req.send();
    }
}

function verificarMail(){
    var input = document.getElementById("email");
    var valor = input.value;
    var boton = document.getElementById("btnregistrar");
    var div = document.getElementById("emaildiv");
    if (valor == ""){
        
        div.style = "color:red";
        div.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div.innerHTML += " Favor de no dejar en blanco esta casilla";
        boton.disabled = true;
    }
    else{
        div.innerHTML = "";
        boton.disabled = false;
    }
}

function desplegar(){
    var respuesta = req.responseText.trim();
    var div = document.getElementById("igualono");
    var boton = document.getElementById("btnregistrar");
    
    if (respuesta == "si"){
        div.style = "color:green";
        div.innerHTML = "<img src='checkmark.png' height='10' width='10'>";
        div.innerHTML += " Este usuario está disponible";
        boton.disabled = false;
    }
    else if (respuesta = "no"){
        div.style = "color:red";
        div.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div.innerHTML += " Este usuario no está disponible";
        boton.disabled = true;
    }
    
<<<<<<< HEAD
}

function validarForma(){
     var nombreUsuario = document.getElementById("nombreUsuario").value;
     var email = document.getElementById("email").value;
     
     var div1 = document.getElementById("igualono");
     var div2 = document.getElementById("emaildiv");
     var valido = true;
     if (nombreUsuario == ""){
        div1.style = "color:red";
        div1.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div1.innerHTML += " Favor de no dejar en blanco esta casilla";
        valido = false;
     }
     if (email == ""){
        div2.style = "color:red";
        div2.innerHTML = "<img src='crossmark.png' height='10' width='10'>";
        div2.innerHTML += " Favor de no dejar en blanco esta casilla";
        valido = false;
     }
     
     return valido;
     
     
     
}
=======
}
>>>>>>> origin/master
