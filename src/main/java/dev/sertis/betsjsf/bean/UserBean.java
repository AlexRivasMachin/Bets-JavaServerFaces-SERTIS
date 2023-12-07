package dev.sertis.betsjsf.bean;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class UserBean {

    private String username;
    private double saldo;
    private boolean mostrarEventos = false;
    private boolean mostrarApuestas = false;
    private boolean mostrarAñadirSaldo = false;
    private boolean mostrarDatosPersonales = false;
    private String componentContent;

    public UserBean() {
        username = "UsuarioX";
        saldo = 100.0;
        resetFlagsExcept("mostrarEventos");
        componentContent = "Contenido por defecto";
    }

    public String logout() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String showEventos() {
        mostrarEventos = true;
        resetFlagsExcept("mostrarEventos");
        System.out.println("showEventos");
        //componentContent = "userNormalComponentes/eventosComponente.xhtml";
        return null;
    }

    public String showApuestas() {
        mostrarApuestas = true;
        resetFlagsExcept("mostrarApuestas");
        //componentContent = "userNormalComponentes/apuestasRealizadas.xhtml";
        return null;
    }

    public String showAñadirSaldo() {
        mostrarAñadirSaldo = true;
        resetFlagsExcept("mostrarAñadirSaldo");
        //componentContent = "userNormalComponentes/añadirSaldoComponente.xhtml";
        return null;
    }

    public String showDatosPersonales() {
        mostrarDatosPersonales = true;
        resetFlagsExcept("mostrarDatosPersonales");
        //componentContent = "userNormalComponentes/datosPersonalesComponente.xhtml";
        return null;
    }

    private void resetFlagsExcept(String flagToKeep) {
        if (!"mostrarEventos".equals(flagToKeep)) mostrarEventos = false;
        if (!"mostrarApuestas".equals(flagToKeep)) mostrarApuestas = false;
        if (!"mostrarAñadirSaldo".equals(flagToKeep)) mostrarAñadirSaldo = false;
        if (!"mostrarDatosPersonales".equals(flagToKeep)) mostrarDatosPersonales = false;
    }

    public String getComponentContent() {
        return componentContent;
    }

    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }


}
