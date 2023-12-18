package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.BLFacade;
import dev.sertis.betsjsf.domain.Event;

import java.io.Serializable;

public class CloseEventBean implements Serializable {
    public CloseEventBean() {

    }
    private EventsViewBean eventsViewBean;
    private BLFacade blFacade;
    private Event selectedEvent;

    public void setEventsViewBean(EventsViewBean eventsViewBean) {
        this.eventsViewBean = eventsViewBean;

        this.selectedEvent = eventsViewBean.getSelectedEvent();
        this.selectedEvent = blFacade.getEventById(this.selectedEvent.getEventId());
        System.out.println("Selected event: " + this.selectedEvent.getEventDescription());
    }


}
