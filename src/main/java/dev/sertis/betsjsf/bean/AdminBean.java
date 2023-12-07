package dev.sertis.betsjsf.bean;



public class AdminBean {
    public AdminBean() {
        System.out.println("AdminBean created");
        componentPath = "adminCerrarEventos.xhtml";
    }
    private String componentPath;

    public String getComponentPath(){
        return componentPath;
    }

    public void cambiarComponenteAnadirPreguntasYPronosticos(){
        System.out.println("cambiando a anadir preguntas y pronosticos");
        cambiarComponente("adminAnadirPreguntasYPronosticos.xhtml");
    }
    public void cambiarComponenteCerrarEventos(){
        System.out.println("cambiando a cerrar evento");
        cambiarComponente("adminCerrarEventos.xhtml");
    }
    public void cambiarComponenteCrearEvento(){
        System.out.println("cambiando a crear evento");
        cambiarComponente("adminCrearEvento.xhtml");
    }
    public void cambiarComponenteListeDeEventos(){
        System.out.println("cambiando a lista de eventos");
        cambiarComponente("adminListaDeEventos.xhtml");
    }

    public void cambiarComponente(String path){
        componentPath = path;
    }


}
