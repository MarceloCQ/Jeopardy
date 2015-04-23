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
    var lista = document.getElementById("listaMaterias");
    lista.options.length = 0;
    var i;
    for (i = 0; i < materias.length; ++i) {
        var opt = document.createElement('option');
        opt.appendChild(document.createTextNode(materias[i].textContent));
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
