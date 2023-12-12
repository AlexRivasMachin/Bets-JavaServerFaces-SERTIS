package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.UserDAO;
import dev.sertis.betsjsf.dao.UserDAOImpl;
import dev.sertis.betsjsf.domain.Bet;
import dev.sertis.betsjsf.domain.User;

import java.util.ArrayList;

public class UserBean {

    private String username;
    private String dni;
    private String name;
    private String apellido;
    private String tarjetaDeCredito;
    private String contraseña;
    private double saldo;
    private double cantidadRetiro;
    private double cantidadDeposito;

    private String componentContent;
    private ArrayList<Bet> apuestasRealizadas;
    private User loggedUser;
    private final UserDAO userDAO;

    public UserBean() {
        username = "UsuarioX";
        saldo = 100.0;
        dni = "12345678A";
        name = "pepe";
        apellido = "perez";
        tarjetaDeCredito = "1234567891234567";
        contraseña = "1234";
        cantidadRetiro = 0.0;
        cantidadDeposito = 0.0;
        apuestasRealizadas = new ArrayList<>();
        userDAO = new UserDAOImpl();
        loggedUser = LoginBean.getLoggedUser();
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
    public void setUsername(String username) {
        this.username = username;
    }

    public double getSaldo() {
        return saldo;
    }
    public double getCantidadRetiro() {
        return cantidadRetiro;
    }
    public void setCantidadRetiro(double cantidadRetiro) {
        this.cantidadRetiro = cantidadRetiro;
    }
    public double getCantidadDeposito() {
        return cantidadDeposito;
    }
    public void setCantidadDeposito(double cantidadDeposito) {
        this.cantidadDeposito = cantidadDeposito;
    }
    public void setSaldo(double nuevoSaldo) {
        this.saldo = nuevoSaldo;
    }
    public void añadirSaldo() {

        this.saldo += cantidadDeposito;
    }
    public void restarSaldo() {
        if(cantidadRetiro <= saldo) {
            this.saldo -= cantidadRetiro;
            cantidadRetiro = 0.0;
        }
    }

    public String getDni() {return dni; }
    public void setDni(String nuevoDni) {
        if(nuevoDni.length() == 9) this.dni = nuevoDni;
    }

    public String getName() {return name; }
    public void setName(String nuevoNombre) {
        if (!nuevoNombre.isEmpty()) this.name = nuevoNombre;
    }

    public String getApellido() {return apellido; }
    public void setApellido(String nuevoApellido) {if(!nuevoApellido.isEmpty()) this.apellido = nuevoApellido; }

    public String getTarjetaDeCredito() {return tarjetaDeCredito; }
    public void setTarjetaDeCredito(String nuevaTarjeta) {if(nuevaTarjeta.length()==16) this.tarjetaDeCredito = nuevaTarjeta; }

    public String getContraseña() {return contraseña; }
    public void setContraseña(String nuevaContraseña) {if(!nuevaContraseña.isEmpty()) this.contraseña = nuevaContraseña; }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

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
