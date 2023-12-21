package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public class CreateBetBean implements Serializable {
    private EventsViewBean eventsViewBean;
    private UserBean userBean;
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
        }else if( LoginBean.getLoggedUser().getCreditCard() == null){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Debes añadir una tarjeta de crédito"));
            return;
        }

        User managedUser = blFacade.updateUser(userBean.getLoggedUser());
        // TODO ver porq dupilca tuplas de Forecast
        //TODO si la apuesta ya existe que no intente introducirla y que muestre un mensaje
        Forecast managedForecast = blFacade.updateForecast(this.selectedForecast);

        Bet bet = new Bet(managedUser,
                this.newBetAmountPlaced,
                managedForecast);

        managedUser.addBet(bet);

        blFacade.updateBet(bet);

        //managedUser = blFacade.updateUser(managedUser);

        userBean.setLoggedUser(managedUser);

        LoginBean.setLoggedUser(managedUser);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Apuesta realizada con éxito"));
    }

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        // We need to retrieve the selected event because it is in a detached state
        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());
        System.out.println("Selected event actualizado");
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

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
