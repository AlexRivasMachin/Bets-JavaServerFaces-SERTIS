console.log("adminCreateEvent30.js cargado");

let botonAceptar;
let calendarioFecha;
let mensajes;

$(document).ready(function () {
    console.log("adminCreateEvent21.js document ready");

    botonAceptar = document.getElementById("formBotonAceptar:botonAceptar")
    calendarioFecha = PF('calendario');
});

function validarFecha(){
    console.log("validando fecha");

    let fechaCalendario = calendarioFecha.jqEl.datepicker('getDate');
    let fechaActual = new Date();
    fechaActual.setDate(fechaActual.getDate() - 1);

    if(fechaCalendario > fechaActual){
        console.log("fecha valida");
        enableButton(botonAceptar);
    }else{
        console.log("fecha invalida");
        disableButton(botonAceptar);
    }

}

function enableButton(button){
    button.classList.remove("buttonDisabled");
    button.classList.add("buttonEnabled");
    button.disabled = false;
}
function disableButton(button){
    button.classList.remove("buttonEnabled");
    button.classList.add("buttonDisabled");
    button.disabled = true;
}

