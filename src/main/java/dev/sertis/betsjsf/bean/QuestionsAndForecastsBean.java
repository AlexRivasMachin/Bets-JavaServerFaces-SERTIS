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
        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());
    }

    public Question getSelectedQuestion() {
        return selectedQuestion;
    }

    public void setSelectedQuestion(Question selectedQuestion) {
        System.out.println("Selected question: " + selectedQuestion.getQuestionDescription());
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
        if (this.newQuestionDescription == null || this.newQuestionDescription.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("question-description-input",
                    new FacesMessage("La descripción de la pregunta es obligatoria"));
            return;
        }else if (this.newQuestionMinimumBetAmount < 1.00) {
            FacesContext.getCurrentInstance().addMessage("question-minimumBet-input",
                    new FacesMessage("La apuesta mínima debe ser mayor de 1.00"));
            return;
        }

        Question question = new Question(this.newQuestionDescription,
                this.newQuestionMinimumBetAmount,
                this.eventsViewBean.getSelectedEvent());

        // Because selectedEvent is in a detached state we have to use merge
        this.selectedEvent.getQuestionsForThisEvent().add(question);
        blFacade.updateEvent(this.selectedEvent);

        // Refresh the selected event
        final long selectedEventId = this.selectedEvent.getEventId();
        this.selectedEvent = blFacade.getEventById(selectedEventId);

        this.eventsViewBean.setSelectedEvent(this.selectedEvent);
    }

    public void createForecast() {
        if (this.newForecastDescription == null || this.newForecastDescription.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("forecast-description-input",
                    new FacesMessage("La descripción de la apuesta es obligatoria"));
            return;
        }else if (this.newPotentialGain < 1.00) {
            FacesContext.getCurrentInstance().addMessage("forecast-potentialGain-input",
                    new FacesMessage("El potencial de ganancia debe ser mayor de 1.00"));
            return;
        }

        Forecast forecast = new Forecast(this.newForecastDescription,
                this.newPotentialGain,
                this.selectedQuestion);

        // Because selectedQuestion is in a detached state we have to use merge
        this.selectedQuestion.getForecastsForThisQuestion().add(forecast);
        blFacade.updateQuestion(this.selectedQuestion);

        // Refresh the selected event
        final long selectedEventId = this.selectedEvent.getEventId();
        this.selectedEvent = blFacade.getEventById(selectedEventId);

        this.eventsViewBean.setSelectedEvent(this.selectedEvent);
    }
}
