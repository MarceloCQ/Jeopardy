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

    var url = "ControladorAdministrar?operacion=buscar&hint=" + hint;
    var div = document.getElementById("materiaSelecc");
    div.innerHTML = "";
    req = new XMLHttpRequest();
    req.onload = cambiarOpcionesMateria;
    req.open("GET", url, true);
    req.send();
}

function cambiarOpcionesMateria() {
    datos = req.responseXML;
    var div = document.getElementById("materiaSelecc");
    materias = datos.getElementsByTagName("Nombre");
    ids = datos.getElementsByTagName("Id");
    var lista = document.getElementById("listaMaterias");

 //   div.innerHTML = "";
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
    var texto = document.getElementById("materia").value;
    if (confirm("¿Seguro que quieres agregar la materia " + "\"" + texto + "\"?")) {
        var url = "ControladorAdministrar?operacion=agregar&materiaNombre=" + texto;
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
    div.innerHTML = "";

    var tabla = document.createElement('table');
    tabla.id = "tablaMaterias";
    div.appendChild(tabla);

    var tbdy = document.createElement('tbody');
    tabla.appendChild(tbdy);

    var row1 = document.createElement('tr');
    tbdy.appendChild(row1);

    var th1 = document.createElement('th');
    th1.innerHTML = "Nombre";
    row1.appendChild(th1);

    var th2 = document.createElement('th');
    th2.innerHTML = "Editar";
    row1.appendChild(th2);

    var th3 = document.createElement('th');
    th3.innerHTML = "Borrar";
    row1.appendChild(th3);

    var row2 = document.createElement('tr');
    tbdy.appendChild(row2);

    var td1 = document.createElement('td');
    td1.innerHTML = materia;
    td1.id = "tdNombreMateria";
    row2.appendChild(td1);

    var td2 = document.createElement('td');
    var anchor2 = document.createElement('a');
    anchor2.onclick = editarMateria;
    anchor2.href = "#";
    anchor2.innerHTML = "Editar";
    td2.appendChild(anchor2);
    row2.appendChild(td2);

    var td3 = document.createElement('td');
    var anchor = document.createElement('a');
    anchor.onclick = eliminarMateria;
    anchor.href = "#";
    anchor.innerHTML = "Borrar";
    td3.appendChild(anchor);
    row2.appendChild(td3);
    
    var url = "ControladorAdministrar?operacion=buscarCategorias&hint=&id=" + id;
    req = new XMLHttpRequest();
    req.onload = poblarCategorias;
    req.open("GET", url, true);
    req.send();
    
}

function eliminarMateria() {
    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
    var texto = document.getElementById("materia").value;
    var div = document.getElementById("materiaSelecc");
    div.innerHTML = "";
    var url = "ControladorAdministrar?operacion=eliminar&id=" + id + "&materiaNombre=" + texto;
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

function salvarModMateria(obj, input){
    
    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
 
    var url = "ControladorAdministrar?operacion=editarMateria&id="+id+"&nombre="+input;
    
    var materiaNombre = document.getElementById("materia");
    
    materiaNombre.value = input;

    obj.replaceChild(document.createTextNode(input), obj.firstChild);
    
    req = new XMLHttpRequest();
    req.onload = cambiarOpcionesMateria;
    req.open("GET", url, true);
    req.send();
}

//FUNCIONES PARA CATEGORIAS
function poblarCategorias(){
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

function agregarCategoria(){
    var texto = document.getElementById("categoria").value;
    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
    
    if (confirm("¿Seguro que quieres agregar la categoria " + "\"" + texto + "\"?")) {
        var url = "ControladorAdministrar?operacion=agregarCategoria&nombre=" + texto + "&idMateria="+id;
        req = new XMLHttpRequest();
        req.onload = poblarCategorias;
        req.open("GET", url, true);
        req.send();
    }
}

function buscarCategorias(hint){
    var lista = document.getElementById("listaMaterias");
    var id = lista.options[lista.selectedIndex].value;
    var url = "ControladorAdministrar?operacion=buscarCategorias&hint=" + hint + "&id=" + id;
    var div = document.getElementById("categoriaSelecc");
    div.innerHTML = "";
    req = new XMLHttpRequest();
    req.onload = poblarCategorias;
    req.open("GET", url, true);
    req.send();
}