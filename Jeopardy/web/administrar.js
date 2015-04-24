/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function trim(value) {
    var temp = value;
    var obj = /^(\s*)([\W\w]*)(\b\s*$)/;
    if (obj.test(temp)) {
        temp = temp.replace(obj, '$2');
    }
    var obj = /  /g;
    while (temp.match(obj)) {
        temp = temp.replace(obj, " ");
    }
    return temp;
}

//Funcion para calcular el largo en pixels det texto dado
function getTextWidth(texto)
{
    //Valor por default : 150 pixels
    var ancho = 150;

    if (trim(texto) == "")
    {
        return ancho;
    }

    //Creaci�n de un span escondido que se puedr� medir 
    var span = document.createElement("span");
    span.style.visibility = "hidden";
    span.style.position = "absolute";

    //Se agrega el texto al span y el span a la p�gina
    span.appendChild(document.createTextNode(texto));
    document.getElementsByTagName("body")[0].appendChild(span);

    //tama�o del texto
    ancho = span.offsetWidth;

    //Eliminaci�n del span
    document.getElementsByTagName("body")[0].removeChild(span);
    span = null;

    return ancho;
}

function buscarMaterias(hint) {
    
    reiniciarSeccPistas();
    reiniciarSeccCategorias();  
    document.getElementById("materiaSelecc").style = "display:none";
    var url = "ControladorAdministrar?operacion=buscar&hint=" + hint;
    req = new XMLHttpRequest();
    req.onload = cambiarOpcionesMateria;
    req.open("GET", url, true);
    req.send();
}

function cambiarOpcionesMateria() {
    datos = req.responseXML;
    materias = datos.getElementsByTagName("Nombre");
    ids = datos.getElementsByTagName("Id");
    var lista = document.getElementById("listaMaterias");

    
    lista.options.length = 0;
    var i;
    for (i = 0; i < materias.length; ++i) {
        var opt = document.createElement('option');
        opt.appendChild(document.createTextNode(materias[i].textContent));
        opt.value = ids[i].textContent;
        lista.appendChild(opt);
    }

}

function agregarMateria() {
    var materia = document.getElementById("materia");
    var texto = materia.value;
    if (confirm("¿Seguro que quieres agregar la materia " + "\"" + texto + "\"?")) {
        var url = "ControladorAdministrar?operacion=agregar&materiaNombre=" + texto;
        materia.value = "";
        req = new XMLHttpRequest();
        req.onload = cambiarOpcionesMateria;
        req.open("GET", url, true);
        req.send();
    }
}

function materiaSeleccionada() {
    var lista = document.getElementById("listaMaterias");
    var div = document.getElementById("materiaSelecc");
    var materia = lista.options[lista.selectedIndex].text;
    var id = lista.options[lista.selectedIndex].value;
    
    reiniciarSeccPistas();
    reiniciarSeccCategorias();
    
    div.style = "display:inline";

    document.getElementById("tdNombreMateria").innerHTML = materia;
    
    var divCat = document.getElementById("seccionCategorias");
    divCat.style = "display: block";

    var url = "ControladorAdministrar?operacion=buscarCategorias&hint=&id=" + id;
    req = new XMLHttpRequest();
    req.onload = poblarCategorias;
    req.open("GET", url, true);
    req.send();

}

function eliminarMateria() {
    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
    document.getElementById("materia").value = "";
    document.getElementById("materiaSelecc").style= "display:none";
    reiniciarSeccPistas();
    reiniciarSeccCategorias();
    
    var url = "ControladorAdministrar?operacion=eliminar&id=" + id;
    req = new XMLHttpRequest();
    req.onload = cambiarOpcionesMateria;
    req.open("GET", url, true);
    req.send();

    return false;
}


function editarMateria() {
    var obj = document.getElementById("tdNombreMateria");
    //Objeto que sirve para editar el valor en la pagina 
    var input = null;
    input = document.createElement("input");

    if (obj.innerText)
        input.value = obj.innerText;
    else
        input.value = obj.textContent

    input.value = trim(input.value);

    input.tabIndex = "0";

    //a la caja INPUT se la asigna un tama�o un poco mayor que el texto a modificar
    input.style.width = getTextWidth(input.value) + 30 + "px";

    //Se remplaza el texto por el objeto INPUT
    obj.replaceChild(input, obj.firstChild);

    //Se selecciona el elemento y el texto a modificar
    input.focus();
    input.select();

    //Asignaci�n de los 2 eventos que provocar�n la escritura en la base de datos

    //La tecla Enter
    input.onkeydown = function keyDown(event)
    {

        if (event.keyCode == 13)
        {
            salvarModMateria(obj, input.value);
            delete input;
        }
    };

    //Salida de la INPUT
    input.onblur = function salir()
    {
        salvarModMateria(obj, input.value);
        delete input;
    };

    return false;
}

function salvarModMateria(obj, input) {

    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;

    var url = "ControladorAdministrar?operacion=editarMateria&id=" + id + "&nombre=" + input;

    var materiaNombre = document.getElementById("materia");

    materiaNombre.value = input;

    obj.replaceChild(document.createTextNode(input), obj.firstChild);

    req = new XMLHttpRequest();
    req.onload = cambiarOpcionesMateria;
    req.open("GET", url, true);
    req.send();
}

//FUNCIONES PARA CATEGORIAS
function poblarCategorias() {
    datos = req.responseXML;
    categorias = datos.getElementsByTagName("Nombre");
    ids = datos.getElementsByTagName("Id");
    var lista = document.getElementById("listaCategorias");

    lista.options.length = 0;
    var i;
    for (i = 0; i < categorias.length; ++i) {
        var opt = document.createElement('option');
        opt.appendChild(document.createTextNode(categorias[i].textContent));
        opt.value = ids[i].textContent;
        lista.appendChild(opt);
    }
}

function agregarCategoria() {
    var categoria = document.getElementById("categoria");
    var texto = categoria.value;
    var lista = document.getElementById("listaMaterias");
    
    var id = lista.options[lista.selectedIndex].value;

    if (confirm("¿Seguro que quieres agregar la categoria " + "\"" + texto + "\"?")) {
        categoria.value = "";
        reiniciarSeccPistas();
        var url = "ControladorAdministrar?operacion=agregarCategoria&nombre=" + texto + "&idMateria=" + id;
        req = new XMLHttpRequest();
        req.onload = poblarCategorias;
        req.open("GET", url, true);
        req.send();
    }
}

function buscarCategorias(hint) {
    reiniciarSeccPistas();
    
    document.getElementById("categoriaSelecc").style = "display: none";
    
    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
    var url = "ControladorAdministrar?operacion=buscarCategorias&hint=" + hint + "&id=" + id;
    
    req = new XMLHttpRequest();
    req.onload = poblarCategorias;
    req.open("GET", url, true);
    req.send();
}

function categoriaSeleccionada() {
    var lista = document.getElementById("listaCategorias");
    var div = document.getElementById("categoriaSelecc");
    var categoria = lista.options[lista.selectedIndex].text;
    var id = lista.options[lista.selectedIndex].value;
    
    reiniciarSeccPistas();
    
    div.style = "display: inline";

   document.getElementById("tdNombreCategoria").innerHTML = categoria;

    var divCat = document.getElementById("seccionPistas");
    divCat.style = "display: inline";

    var url = "ControladorAdministrar?operacion=buscarPreguntas&hint=&id=" + id;
    req = new XMLHttpRequest();
    req.onload = poblarPistas;
    req.open("GET", url, true);
    req.send();
}

function editarCategoria() {
    var obj = document.getElementById("tdNombreCategoria");
    //Objeto que sirve para editar el valor en la pagina 
    var input = null;
    input = document.createElement("input");

    if (obj.innerText)
        input.value = obj.innerText;
    else
        input.value = obj.textContent

    input.value = trim(input.value);

    input.tabIndex = "0";

    //a la caja INPUT se la asigna un tama�o un poco mayor que el texto a modificar
    input.style.width = getTextWidth(input.value) + 30 + "px";

    //Se remplaza el texto por el objeto INPUT
    obj.replaceChild(input, obj.firstChild);

    //Se selecciona el elemento y el texto a modificar
    input.focus();
    input.select();

    //Asignaci�n de los 2 eventos que provocar�n la escritura en la base de datos

    //La tecla Enter
    input.onkeydown = function keyDown(event)
    {

        if (event.keyCode == 13)
        {
            salvarModCategoria(obj, input.value);
            delete input;
        }
    };

    //Salida de la INPUT
    input.onblur = function salir()
    {
        salvarModCategoria(obj, input.value);
        delete input;
    };

    return false;
}

function salvarModCategoria(obj, input) {
    var lista = document.getElementById("listaCategorias");
    var listaM = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
    var idM = listaM.options[listaM.selectedIndex].value;


    var url = "ControladorAdministrar?operacion=editarCategoria&id=" + id + "&idMateria=" + idM + "&nombre=" + input;

    var categoriaNombre = document.getElementById("categoria");

    categoriaNombre.value = input;

    obj.replaceChild(document.createTextNode(input), obj.firstChild);

    req = new XMLHttpRequest();
    req.onload = poblarCategorias;
    req.open("GET", url, true);
    req.send();
}

function eliminarCategoria() {
    var lista = document.getElementById("listaCategorias");
    var listaM = document.getElementById("listaMaterias");

    var id = lista.options[lista.selectedIndex].value;
    var idM = listaM.options[listaM.selectedIndex].value;

    document.getElementById("categoria").value = "";
    document.getElementById("categoriaSelecc").style = "display: none";
    reiniciarSeccPistas();

    var url = "ControladorAdministrar?operacion=eliminarCategoria&id=" + id + "&idMateria=" + idM;
    req = new XMLHttpRequest();
    req.onload = poblarCategorias;
    req.open("GET", url, true);
    req.send();

    return false;

}

function poblarPistas() {
    var datos = req.responseXML;
    var pistas = datos.getElementsByTagName("Nombre");
    var ids = datos.getElementsByTagName("Id");
    var lista = document.getElementById("listaPistas");

    lista.options.length = 0;
    var i;
    for (i = 0; i < pistas.length; ++i) {
        var opt = document.createElement('option');
        opt.appendChild(document.createTextNode(pistas[i].textContent));
        opt.value = ids[i].textContent;
        lista.appendChild(opt);
    }
}

function agregarPreguntas() {
    var div = document.getElementById("divDesglose");
    var div2 = document.getElementById("buscarPistas");

    if (div.style.display == "block") {
        var pregunta = document.getElementById("pregunta").value;
        var respuesta = document.getElementById("respuesta").value;
        var puntos = (document.getElementById("puntos").selectedIndex + 1) * 100;
        var lista = document.getElementById("listaCategorias");
        var idCategoria = lista.options[lista.selectedIndex].value;

        if (pregunta == "" || respuesta == "") {
            alert("Favor de llenar todos los campos");
        }
        else {
            if (confirm("¿Seguro que quieres agregar la pregunta " + "\"" + pregunta + "\"?")) {
                var url = "ControladorAdministrar?operacion=agregarPregunta&pregunta=" + pregunta + "&respuesta=" + respuesta + "&puntos=" + puntos + "&idCategoria=" + idCategoria;
                pregunta = document.getElementById("pregunta");
                respuesta = document.getElementById("respuesta");
                puntos = document.getElementById("puntos");

                div.style = "display:none";
                div2.style = "display:block";
                pregunta.value = "";
                respuesta.value = "";
                puntos.selectedIndex = 0;
                req = new XMLHttpRequest();
                req.onload = poblarPistas;
                req.open("GET", url, true);
                req.send();

            }
        }

    } else {
        div.style = "display:block";
        div2.style = "display:none";
    }

}

function buscarPreguntas(hint) {
    var lista = document.getElementById("listaCategorias");
    var id = lista.options[lista.selectedIndex].value;
    var url = "ControladorAdministrar?operacion=buscarPreguntas&hint=" + hint + "&id=" + id;
    var div = document.getElementById("pistaSelecc");
    div.innerHTML = "";
    req = new XMLHttpRequest();
    req.onload = poblarPistas;
    req.open("GET", url, true);
    req.send();
}

function reiniciarSeccPistas() {
    var div = document.getElementById("divDesglose");
    var div2 = document.getElementById("buscarPistas");
    var div3 = document.getElementById("seccionPistas");
    var div4 = document.getElementById("pistaSelecc");
    
    
    var pregunta = document.getElementById("pregunta");
    var respuesta = document.getElementById("respuesta");
    var puntos = document.getElementById("puntos");

    div.style = "display:none";
    div2.style = "display:block";
    div3.style = "display:none";
    div4.style = "display:none";
    
    pregunta.value = "";
    respuesta.value = "";
    puntos.selectedIndex = 0;
}

function reiniciarSeccCategorias(){
    var div = document.getElementById("seccionCategorias");
    var divTabla = document.getElementById("categoriaSelecc");
    var opciones = document.getElementById("listaCategorias");
    var nombreCat = document.getElementById("categoria");
    
    div.style = "display:none";
    divTabla.style = "display:none";
    removeOptions(opciones);
    nombreCat.value = "";
}

function preguntaSeleccionada(){
    
    var lista = document.getElementById("listaPistas");
    var id = lista.options[lista.selectedIndex].value;
    
    var url = "ControladorAdministrar?operacion=obtenerPregunta&id=" + id;
    req = new XMLHttpRequest();
    req.onload = preguntaMostrada;
    req.open("GET", url, true);
    req.send(); 
}

function preguntaMostrada(){
    var datos = req.responseXML;
    document.getElementById("tdPregunta").innerHTML = datos.getElementsByTagName("Pregunta")[0].textContent;
    document.getElementById("tdPista").innerHTML = datos.getElementsByTagName("Respuesta")[0].textContent;
    document.getElementById("tdPuntos").innerHTML = datos.getElementsByTagName("Puntos")[0].textContent;
    document.getElementById("pistaSelecc").style = "display: inline";
}

function eliminarPregunta(){
    var lista = document.getElementById("listaPistas");
    var listaC = document.getElementById("listaCategorias");

    var id = lista.options[lista.selectedIndex].value;
    var idC = listaC.options[listaC.selectedIndex].value;

    document.getElementById("pregunta").value = "";
    document.getElementById("pistaSelecc").style = "display: none";

    var url = "ControladorAdministrar?operacion=eliminarPregunta&id=" + id + "&idCategoria=" + idC;
    req = new XMLHttpRequest();
    req.onload = poblarPistas;
    req.open("GET", url, true);
    req.send();
}

function editarPregunta(){
    var objPregunta = document.getElementById("tdPregunta");
    var objRespuesta = document.getElementById("tdPista");
    var objPuntos = document.getElementById("tdPuntos");
    
    //Objeto que sirve para editar el valor en la pagina 
    var inputPregunta = null;
    var inputRespuesta = null;
    var inputPuntos = null;
    
    inputPregunta = document.createElement("textarea");
    inputRespuesta = document.createElement("textarea");
    
    inputPuntos = document.createElement("select");
    
    for (var i = 1; i <= 5; i++){
        var opt = document.createElement("option");
        opt.innerHTML = i * 100;
        inputPuntos.appendChild(opt);
    }
    
    

    if (objPregunta.innerText){
        inputPregunta.value = objPregunta.innerText;
        inputRespuesta.value = objRespuesta.innerText;
    }
    else{
        inputPregunta.value = objPregunta.textContent;
        inputRespuesta.value = objRespuesta.textContent;
        
    }
    
    inputPuntos.selectedIndex = parseInt(objPuntos.innerHTML) / 100 - 1;

   // inputPregunta.value = trim(inputPregunta.value);
   // inputRespuesta.value = trim(inputRespuesta.value);

    inputPregunta.tabIndex = "0";
    inputRespuesta.tabIndex = "0";

    //a la caja INPUT se la asigna un tama�o un poco mayor que el texto a modificar
    inputPregunta.style.width = "100px";
    inputRespuesta.style.width = "150px";

    //Se remplaza el texto por el objeto INPUT
    objPregunta.replaceChild(inputPregunta, objPregunta.firstChild);
    objRespuesta.replaceChild(inputRespuesta, objRespuesta.firstChild);
    objPuntos.replaceChild(inputPuntos, objPuntos.firstChild);

    //Se selecciona el elemento y el texto a modificar
    inputPregunta.focus();

    //Asignaci�n de los 2 eventos que provocar�n la escritura en la base de datos

    //La tecla Enter
    inputPregunta.onkeydown = inputRespuesta.onkeydown = inputPuntos.onkeydown = function keyDown(event)
    {

        if (event.keyCode == 13)
        {
            salvarModPregunta(objPregunta, inputPregunta.value, objRespuesta, inputRespuesta.value, objPuntos, (inputPuntos.selectedIndex + 1) * 100);
            delete input;
        }
    };

    

    
}

function salvarModPregunta(objPr, inputPr, objResp, inputResp, objPuntos, inputPuntos) {
    var lista = document.getElementById("listaPistas");
    var listaC = document.getElementById("listaCategorias");
    var id = lista.options[lista.selectedIndex].value;
    var idC = listaC.options[listaC.selectedIndex].value;


    var url = "ControladorAdministrar?operacion=editarPregunta&id=" + id + "&idCategoria=" + idC + "&pregunta=" + inputPr + "&respuesta=" + inputResp + "&puntos=" + puntos;

    var categoriaNombre = document.getElementById("pregunta");
    categoriaNombre.value = inputPr;

    objPr.replaceChild(document.createTextNode(inputPr), objPr.firstChild);
    objResp.replaceChild(document.createTextNode(inputResp), objResp.firstChild);
    objPuntos.replaceChild(document.createTextNode(inputPuntos), objPuntos.firstChild);


//    req = new XMLHttpRequest();
  //  req.onload = poblarPistas;
    //req.open("GET", url, true);
    //req.send();
}

function removeOptions(selectbox)
{
    var i;
    for(i=selectbox.options.length-1;i>=0;i--)
    {
        selectbox.remove(i);
    }
}