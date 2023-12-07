package dev.sertis.betsjsf.bean;

import javax.annotation.PostConstruct;
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
    private boolean eventosButtonEnabled = true;
    private boolean apuestasButtonEnabled = true;
    private boolean añadirSaldoButtonEnabled = true;
    private boolean datosPersonalesButtonEnabled = true;
    private String componentContent;

    public UserBean() {
        username = "UsuarioX";
        saldo = 100.0;
    }

    @PostConstruct
    public void init() {
        showEventos();
    }


    public String logout() {
        return "/login.xhtml?faces-redirect=true";
    }

    public String showEventos() {
        mostrarEventos = true;
        resetFlagsExcept("mostrarEventos");
        eventosButtonEnabled = false;
        componentContent = "userNormalComponentes/eventosComponente.xhtml";
        return null;
    }

    public String showApuestas() {
        mostrarApuestas = true;
        resetFlagsExcept("mostrarApuestas");
        apuestasButtonEnabled = false;
        componentContent = "userNormalComponentes/apuestasRealizadas.xhtml";
        return null;
    }

    public String showAñadirSaldo() {
        mostrarAñadirSaldo = true;
        resetFlagsExcept("mostrarAñadirSaldo");
        añadirSaldoButtonEnabled = false;
        componentContent = "userNormalComponentes/añadirSaldoComponente.xhtml";
        return null;
    }

    public String showDatosPersonales() {
        mostrarDatosPersonales = true;
        datosPersonalesButtonEnabled = false;
        resetFlagsExcept("mostrarDatosPersonales");
        componentContent = "userNormalComponentes/datosPersonalesComponente.xhtml";
        return null;
    }

    public String getUsername() {
        return username;
    }

    public double getSaldo() {
        return saldo;
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
