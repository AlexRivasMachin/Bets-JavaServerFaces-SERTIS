package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.EventDAO;
import dev.sertis.betsjsf.dao.EventDAOHibernate;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EventsViewBean implements Serializable {
    private LocalDate eventDate;
    private List<Event> events;

    private final EventDAO eventDAO;

    public EventsViewBean() {
        eventDAO = new EventDAOHibernate();
        // Cargo la fecha actual para que muestre directamente los eventos de hoy
        this.eventDate = LocalDate.now();
        getEventsByDate();
    }

    public void getEventsByDate() {
        this.events = eventDAO.getEventsByDate(this.eventDate);
    }

    public void onDateSelect(SelectEvent<LocalDate> event) {
        this.eventDate = event.getObject();
        getEventsByDate();
    }

    public String getEventLocalTeamLogo(String description){
        if (!description.contains("-"))
            return null;
        String[] teams = description.split("-");
        return String.format("/resources/icons/laliga/%s.png", teams[0].trim().toLowerCase());
    }

    public String getEventVisitorTeamLogo(String description){
        if (!description.contains("-"))
            return null;
        String[] teams = description.split("-");
        return String.format("/resources/icons/laliga/%s.png", teams[1].trim().toLowerCase());
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
