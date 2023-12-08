package dev.sertis.betsjsf.bean;



public class AdminBean {
    public AdminBean() {
        componentPath = "adminUIComponents/adminCerrarEventos.xhtml";
    }
    private String componentPath;

    public String getComponentPath(){
        return componentPath;
    }

    public void changeComponentToAddQuestionsAndForecasts(){
        setComponentPath("adminUIComponents/adminAnadirPreguntasYPronosticos.xhtml");
    }
    public void changeComponentToCloseEvents(){
        setComponentPath("adminUIComponents/adminCerrarEventos.xhtml");
    }
    public void changeComponentToCreateEvent(){
        setComponentPath("adminUIComponents/adminCrearEvento.xhtml");
    }
    public void changeComponentToEventsList(){
        setComponentPath("adminUIComponents/adminListaDeEventos.xhtml");
    }

    private void setComponentPath(String path){
        componentPath = path;
    }


}
