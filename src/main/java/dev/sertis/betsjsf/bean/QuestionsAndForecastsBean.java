package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;
import dev.sertis.betsjsf.domain.Forecast;
import dev.sertis.betsjsf.domain.Question;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class QuestionsAndForecastsBean implements Serializable {

    private EventsViewBean eventsViewBean;
    private final BLFacade blFacade;
    private Event selectedEvent;
    private Question selectedQuestion;
    private String newQuestionDescription;
    private double newQuestionMinimumBetAmount = 1.00;
    private String newForecastDescription;
    private double newPotentialGain = 1.00;

    public QuestionsAndForecastsBean() {
        blFacade = BLFacadeImplementation.getInstance();
    }

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        // We need to retrieve the selected event because it is in a detached state
        this.selectedEvent = blFacade.getEventById(eventsViewBean.getSelectedEvent().getEventId());
    }

    public Question getSelectedQuestion() {
        if (selectedQuestion == null && !selectedEvent.getQuestionsForThisEvent().isEmpty()) {
            selectedQuestion = selectedEvent.getQuestionsForThisEvent().get(0);
        }
        return selectedQuestion;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        this.selectedQuestion = selectedQuestion;
    }

    public String getNewQuestionDescription() {
        return newQuestionDescription;
    }

    public void setNewQuestionDescription(String newQuestionDescription) {
        this.newQuestionDescription = newQuestionDescription;
    }

    public Double getNewQuestionMinimumBetAmount() {
        return newQuestionMinimumBetAmount;
    }

    public void setNewQuestionMinimumBetAmount(Double newQuestionMinimumBetAmount) {
        this.newQuestionMinimumBetAmount = newQuestionMinimumBetAmount;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public String getNewForecastDescription() {
        return newForecastDescription;
    }

    public void setNewForecastDescription(String newForecastDescription) {
        this.newForecastDescription = newForecastDescription;
    }

    public double getNewPotentialGain() {
        return newPotentialGain;
    }

    public void setNewPotentialGain(double newPotentialGain) {
        this.newPotentialGain = newPotentialGain;
    }

    public void createQuestion() {
        Question question = new Question(this.newQuestionDescription,
                this.newQuestionMinimumBetAmount,
                this.eventsViewBean.getSelectedEvent());

        // Because selectedEvent is in a detached state we have to use merge
        this.selectedEvent.getQuestionsForThisEvent().add(question);
        this.selectedEvent = blFacade.updateEvent(this.selectedEvent);

        this.eventsViewBean.setSelectedEvent(this.selectedEvent);

        FacesContext.getCurrentInstance().addMessage("question-description-input",
                new FacesMessage("Pregunta creado correctamente"));
    }

    public void createForecast() {
        Forecast forecast = new Forecast(this.newForecastDescription,
                this.newPotentialGain,
                this.selectedQuestion);

        //TODO cuando se crea el segundo forecast se añade a la pregunta per no se guarda en la base de datos

        // Because selectedQuestion is in a detached state we have to use merge
        this.selectedQuestion.getForecastsForThisQuestion().add(forecast);
        this.selectedQuestion = blFacade.updateQuestion(this.selectedQuestion);

        // Refresh the selected event
        final long selectedEventId = this.selectedEvent.getEventId();
        this.selectedEvent = blFacade.getEventById(selectedEventId);

        this.eventsViewBean.setSelectedEvent(this.selectedEvent);

        FacesContext.getCurrentInstance().addMessage("forecast-potentialGain-input",
                new FacesMessage("Pronostico creado correctamente"));
    }
}
