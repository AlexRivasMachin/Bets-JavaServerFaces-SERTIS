package dev.sertis.betsjsf.bean;

public class UserBean {

    private String username;
    private double saldo;
    private String componentContent;

    public UserBean() {
        username = "UsuarioX";
        saldo = 100.0;
        showEventos();
    }

    public String logout() {
        return "/login.xhtml?faces-redirect=true";
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

    public String getUsername() {
        return username;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getComponentContent() {
        return componentContent;
    }

    public void setComponentContent(String componentContent) {
        this.componentContent = componentContent;
    }
}
