package dev.sertis.betsjsf.bean;


public class Bean {
    private String mensaje = "Hola desde el Managed Bean";
    public String getMensaje() {
        return "Hola desde el Managed Bean";
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
