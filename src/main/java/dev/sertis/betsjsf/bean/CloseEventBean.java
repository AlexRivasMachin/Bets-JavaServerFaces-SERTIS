package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;
import dev.sertis.betsjsf.domain.Forecast;
import dev.sertis.betsjsf.domain.Question;
import org.hibernate.query.sqm.internal.QuerySqmImpl;

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

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());

        imgLocal = eventsViewBean.getEventLocalTeamLogo(selectedEvent.getEventDescription());
        imgVisitante = eventsViewBean.getEventVisitorTeamLogo(selectedEvent.getEventDescription());
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }
    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }
    public String getSelectedEventDescription() {
        return selectedEvent.getEventDescription();
    }
    public EventsViewBean getEventsViewBean() {
        return eventsViewBean;
    }

    public String getImgLocal() {
        return imgLocal;
    }
    public String getImgVisitante() {
        return imgVisitante;
    }

    public Question getSelectedQuestion() {
        return selectedQuestion;
    }
    public void setSelectedQuestion(Question selectedQuestion) {
        System.out.println("Selected question: " + selectedQuestion.getQuestionDescription());
        this.selectedQuestion = selectedQuestion;
    }

    public void printQuestion(Question question){
        System.out.println("Selected question: " + question.getQuestionDescription());
    }

    public void closeEvent(Forecast forecast){
        System.out.println("Ha ganado: " + forecast.getForecastDescription() + "de la pregunta: " + selectedQuestion.getQuestionDescription());
    }



}
