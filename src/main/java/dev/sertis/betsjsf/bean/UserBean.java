package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.domain.Bet;

import java.util.ArrayList;

public class UserBean {

    private String username;
    private String dni;
    private String name;
    private String apellido;
    private String tarjetaDeCredito;
    private double saldo;
    private String componentContent;
    private ArrayList<Bet> apuestasRealizadas;
    public UserBean() {
        username = "UsuarioX";
        saldo = 100.0;
        dni = "12345678A";
        name = "pepe";
        apellido = "perez";
        tarjetaDeCredito = "1234567891234567";
        apuestasRealizadas = new ArrayList<>();

        showEventos();
    }

    public String logout() {
        return "logout";
    }

    public String showEventos() {
        componentContent = "userNormalComponentes/eventosComponente.xhtml";
        return null;
    }

    public String showApuestas() {
        componentContent = "userNormalComponentes/apuestasRealizadasComponente.xhtml";
        return null;
    }

    public String showAñadirSaldo() {
        componentContent = "userNormalComponentes/añadirSaldoComponente.xhtml";
        return null;
    }

    public String showDatosPersonales() {
        componentContent = "userNormalComponentes/datosPersonalesComponente.xhtml";
        return null;
    }

    /**DATOS DEL USUARIO**/
    public String getUsername() {
        return username;
    }
    public double getSaldo() {
        return saldo;
    }
    public String getDni() {return dni; }
    public String getName() {return name; }
    public String getApellido() {return apellido; }
    public String getTarjetaDeCredito() {return tarjetaDeCredito; }

    /**
     * DATOS APUESTAS
     **/
    public ArrayList<Bet> getApuestasRealizadas() {
        return apuestasRealizadas;
    }
    public String fecha() {
        return "fecha";
    }
    public String evento() {
        return "evento";
    }
    public String pregunta() {
        return "pregunta";
    }
    public String pronostico() {
        return "pronostico";
    }
    public String ganancia() {
        return "1.0";
    }
    public String apostado() {
        return "10.00";
    }
    public String balance() {
        return "20.00";
    }

    /**COMPONENTES**/
    public String getComponentContent() {
        return componentContent;
    }
    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }
}
