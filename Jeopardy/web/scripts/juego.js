/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function opSelecc(obj){
    var id = obj.id;
    var url = "ControladorAdministrar?operacion=obtenerPregunta&id=" + id;

    req = new XMLHttpRequest();
    req.onload = preguntaObtenida;
    req.open("GET", url, true);
    req.send();
}

function preguntaObtenida(){
    datos = req.responseXML;
    document.getElementById("tdPregunta").innerHTML = datos.getElementsByTagName("Pregunta")[0].textContent;
    document.getElementById("tdPista").innerHTML = datos.getElementsByTagName("Respuesta")[0].textContent;
    document.getElementById("tdPuntos").innerHTML = datos.getElementsByTagName("Puntos")[0].textContent;
    document.getElementById("pistaSelecc").style = "display: inline";
    
    
}

