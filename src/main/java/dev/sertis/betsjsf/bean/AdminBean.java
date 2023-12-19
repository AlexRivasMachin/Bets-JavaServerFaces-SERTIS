package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.domain.Question;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;

public class AdminBean implements Serializable {
    public AdminBean() {
        componentPath = "adminUIComponents/adminCrearEvento.xhtml";
    }
    private String componentPath, destinoDesdeEventsView;

    public String logout(){
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logout";
    }
    public String exit(){
        return "exit";
    }

    public String getComponentPath(){
        return componentPath;
    }

    public void changeComponentFromEventList(){
        if(destinoDesdeEventsView.equals("addQuestions")) {
            changeComponentToAddQuestionsAndForecastsAfterEventViewList();
        } else if(destinoDesdeEventsView.equals("closeEvents")) {
            changeComponentToCloseEventsAfterEventViewList();
        }
    }
    public void changeComponentToAddQuestionsAndForecasts(){
        changeComponentToEventsList();
        destinoDesdeEventsView = "addQuestions";
    }
    public void changeComponentToAddQuestionsAndForecastsAfterEventViewList(){
        setComponentPath("adminUIComponents/adminAnadirPreguntasYPronosticos.xhtml");
        reloadPage();
    }
    public void changeComponentToCloseEvents(){
        changeComponentToEventsList();
        destinoDesdeEventsView = "closeEvents";
    }
    public void changeComponentToCloseEventsAfterEventViewList(){
        setComponentPath("adminUIComponents/adminCerrarEventos.xhtml");
        reloadPage();
    }
    public void changeComponentToCreateEvent(){
        setComponentPath("adminUIComponents/adminCrearEvento.xhtml");
        reloadPage();
    }
    public void changeComponentToEventsList(){
        setComponentPath("commonUIComponents/mostrarEventos.xhtml");
        reloadPage();
    }

    public void reloadPage(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/admin.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printQuestion(Question question) {
        System.out.println("Question: " + question.getQuestionDescription());
    }

    private void setComponentPath(String path){
        componentPath = path;
    }

}
