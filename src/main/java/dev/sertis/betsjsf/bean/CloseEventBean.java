package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;
import dev.sertis.betsjsf.domain.Forecast;
import dev.sertis.betsjsf.domain.Question;
import org.hibernate.query.sqm.internal.QuerySqmImpl;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class CloseEventBean implements Serializable {
    public CloseEventBean() {
        blFacade = BLFacadeImplementation.getInstance();
    }
    private EventsViewBean eventsViewBean;
    private BLFacade blFacade;
    private Event selectedEvent;
    private String imgLocal, imgVisitante;
    private Question selectedQuestion;
    private Forecast selectedForecast;

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());

        imgLocal = eventsViewBean.getEventLocalTeamLogo(selectedEvent.getEventDescription());
        imgVisitante = eventsViewBean.getEventVisitorTeamLogo(selectedEvent.getEventDescription());
    }

    public String getImgLocal() {
        return imgLocal;
    }
    public String getImgVisitante() {
        return imgVisitante;
    }
    public Event getSelectedEvent() {
        return selectedEvent;
    }
    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }
    public Question getSelectedQuestion() {
        return selectedQuestion;
    }
    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }
    public Forecast getSelectedForecast() {
        return selectedForecast;
    }
    public void setSelectedForecast(Forecast selectedForecast) {
        this.selectedForecast = selectedForecast;
    }

    public void onAsignarBotonSelected(){
        setForecastWinner();
        setMensajeForecastAsignado();
    }
    public void setMensajeForecastAsignado(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Forecast asignado: " + selectedForecast.getForecastDescription()));
    }

    private void setForecastWinner(){
        blFacade.assignResultForecastToQuestion(selectedForecast.getForecastId(), selectedQuestion.getQuestionId());
    }






}
