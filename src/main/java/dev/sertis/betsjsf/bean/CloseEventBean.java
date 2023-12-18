package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.BLFacadeImplementation;
import dev.sertis.betsjsf.domain.Event;

import java.io.Serializable;

public class CloseEventBean implements Serializable {
    public CloseEventBean() {
        blFacade = BLFacadeImplementation.getInstance();
    }
    private EventsViewBean eventsViewBean;
    private BLFacade blFacade;
    private Event selectedEvent;
    private String nombreEvento;

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());
        System.out.println("Selected event: " + this.selectedEvent.getEventDescription());

        nombreEvento = this.selectedEvent.getEventDescription();
    }

    public String getNombreDelEvento(){
        return nombreEvento;
    }
    public void setNombreDelEvento(String nombreEvento){
        this.nombreEvento = nombreEvento;
    }


}
