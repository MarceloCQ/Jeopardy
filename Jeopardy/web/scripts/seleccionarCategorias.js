/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validarSeleccion() {
    var options = document.getElementById("cats").options, count = 0;
    for (var i = 0; i < options.length; i++) {
        if (options[i].selected)
            count++;
    }
    
    if (count != 6){
        alert("Debes seleccionar exactamente 6 categorias");
        return false;
    }
    
}