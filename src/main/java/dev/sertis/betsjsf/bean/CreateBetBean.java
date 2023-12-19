package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class CreateBetBean {
    private EventsViewBean eventsViewBean;
    private final BLFacade blFacade;
    private Event selectedEvent;
    private Question selectedQuestion;
    private Forecast selectedForecast;
    private double newBetAmountPlaced = 1.00;

    public CreateBetBean() {
        blFacade = BLFacadeImplementation.getInstance();
    }

    public void createBet() {
        if (this.newBetAmountPlaced < this.selectedQuestion.getMinimumBetAmount()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("La apuesta mínima es de " + this.selectedQuestion.getMinimumBetAmount() + "€"));
            return;
        } else if (this.selectedQuestion == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Debes seleccionar una pregunta"));
            return;
        } else if (this.selectedForecast == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Debes seleccionar un pronóstico"));
            return;
        }

        Bet bet = new Bet(LoginBean.getLoggedUser(),
                this.newBetAmountPlaced,
                this.selectedForecast);

        this.selectedForecast.getBetsForThisForecast().add(bet);

        blFacade.saveBet(bet);
        blFacade.updateForecast(this.selectedForecast);

        // Because loggedUser is in a detached state we have to use merge
        LoginBean.getLoggedUser().getUserPlacedBets().add(bet);
        blFacade.updateUser(LoginBean.getLoggedUser());

        final User userWhoPlacedBet = blFacade.getUserByDni(LoginBean.getLoggedUser().getDni());
        System.out.println(userWhoPlacedBet.getUserPlacedBets());
        LoginBean.setLoggedUser(userWhoPlacedBet);
    }

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        // We need to retrieve the selected event because it is in a detached state
        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());
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

    public double getNewBetAmountPlaced() {
        return newBetAmountPlaced;
    }

    public void setNewBetAmountPlaced(double newBetAmountPlaced) {
        this.newBetAmountPlaced = newBetAmountPlaced;
    }
}
