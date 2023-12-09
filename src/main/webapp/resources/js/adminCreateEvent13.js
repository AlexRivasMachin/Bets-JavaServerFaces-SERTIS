console.log("adminCreateEvent13.js cargado");

let botonAceptar;
let calendarioFecha;

$(document).ready(function () {
    console.log("adminCreateEvent13.js document ready");

    botonAceptar = document.getElementById("miForm:botonAceptar")
    calendarioFecha = PF('calendario');

});

function validarFecha(){
    console.log("validando fecha");

    let fechaCalendario = calendarioFecha.jqEl.datepicker('getDate');
    let fechaActual = new Date();


    if(fechaCalendario < fechaActual){
        console.log("fecha invalida");
        disableButton(botonAceptar);
    }else{
        console.log("fecha valida");
        enableButton(botonAceptar);
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

