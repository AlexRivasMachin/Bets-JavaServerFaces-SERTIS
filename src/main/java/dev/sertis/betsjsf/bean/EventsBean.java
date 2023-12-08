package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.EventDAO;
import dev.sertis.betsjsf.dao.EventDAOImpl;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EventsBean implements Serializable {
    private String eventDescription;
    private LocalDate eventDate;
    private List<Event> events;

    private final EventDAO eventDAO;

    public EventsBean() {
        eventDAO = new EventDAOImpl();
        /*
        Event e = new Event("Barsa-Madrid", LocalDate.now());
        Event e2 = new Event("Alaves-Barca", LocalDate.now());
        eventDAO.save(e);
        eventDAO.save(e2);*/
        // Esto es para probar
        this.eventDate = LocalDate.now();
        getEventsByDate();
    }

    public void getEventsByDate() {
        this.events = eventDAO.getEventsByDate(this.eventDate);
    }

    public void handleDateSelect(SelectEvent<LocalDate> event) {
        LocalDate selectedDate = event.getObject();

        if (selectedDate != null) {
            System.out.println("Date selected: " + selectedDate);
            this.eventDate = selectedDate;
            getEventsByDate();
        } else {
            System.err.println("Date selected is null!");
        }
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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
