package dev.sertis.betsjsf.bean;
import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AdminBean {
    public AdminBean() {
        componentPath = "adminUIComponents/adminCerrarEventos.xhtml";
    }
    private String componentPath, destinoDesdeEventsView;

    public String logout(){
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

    private void setComponentPath(String path){
        componentPath = path;
    }

}
