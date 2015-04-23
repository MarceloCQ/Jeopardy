/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function buscarMaterias(hint) {

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
    var texto = document.getElementById("materia").value;
    if (confirm("¿Seguro que quieres agregar la materia " + "\"" + texto + "\"?")) {
        var url = "ControladorAdministrar?operacion=agregar&materiaNombre=" + texto;
        req = new XMLHttpRequest();
        req.onload = cambiarOpcionesMateria;
        req.open("GET", url, true);
        req.send();
    }
}

function materiaSeleccionada(){
    var lista = document.getElementById("listaMaterias");
    var div = document.getElementById("materiaSelecc");
    var materia = lista.options[lista.selectedIndex].text;
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
    row2.appendChild(td1);
    
    var td2 = document.createElement('td');
    td2.innerHTML = "Editar";
    row2.appendChild(td2);
    
    var td3 = document.createElement('td');
    td3.innerHTML = "<a href='www.google.com'>Borrar</a>";
    row2.appendChild(td3);
    
    
    
    
    
    
    
}