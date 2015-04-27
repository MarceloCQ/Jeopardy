/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var tdPreguntaSeleccionada;
var puntos;

function opSelecc(obj){
    var id = obj.id;
    var url = "ControladorAdministrar?operacion=obtenerPregunta&id=" + id;
    tdPreguntaSeleccionada = obj;
    
    req = new XMLHttpRequest();
    req.onload = preguntaObtenida;
    req.open("GET", url, true);
    req.send();
}

function preguntaObtenida(){
    datos = req.responseXML;
    document.getElementById("divPregunta").innerHTML = datos.getElementsByTagName("Respuesta")[0].textContent;
    document.getElementById("divRespuesta").innerHTML = datos.getElementsByTagName("Pregunta")[0].textContent;
    document.getElementById("interfazPreg").style.display = "block";
    document.getElementById("preguntas").style.display = "none";
    
    
}

function verPregunta(){
    document.getElementById("divRespuesta").style.display = "block";
    document.getElementById("btnverpreg").style.display = "none";
    document.getElementById("bienomal").style.display = "block";
}

function preguntaCorrecta(){
    tdPreguntaSeleccionada.className = "usada";
    tdPreguntaSeleccionada.onclick = null;
    
    document.getElementById("interfazPreg").style.display = "none";
    document.getElementById("preguntas").style.display = "block";
    document.getElementById("divRespuesta").style.display = "none";
    document.getElementById("btnverpreg").style.display = "block";
    document.getElementById("bienomal").style.display = "none";
    
    var ind = document.getElementById("quiencontesto").selectedIndex;
    var td = document.getElementsByName(ind)[0];
    var puntos = parseInt(td.innerHTML) + parseInt(tdPreguntaSeleccionada.innerHTML); 
    td.innerHTML = puntos;
    
     tdPreguntaSeleccionada.innerHTML = "";
    
    
    
}

function preguntaIncorrecta(){
    tdPreguntaSeleccionada.className = "usada";
    tdPreguntaSeleccionada.onclick = null;
     tdPreguntaSeleccionada.innerHTML = "";
    document.getElementById("interfazPreg").style.display = "none";
    document.getElementById("preguntas").style.display = "block";
    document.getElementById("divRespuesta").style.display = "none";
    document.getElementById("btnverpreg").style.display = "block";
    document.getElementById("bienomal").style.display = "none";
    
}