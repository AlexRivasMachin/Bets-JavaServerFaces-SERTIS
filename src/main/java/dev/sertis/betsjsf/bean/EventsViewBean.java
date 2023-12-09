package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.EventDAO;
import dev.sertis.betsjsf.dao.EventDAOImpl;
import dev.sertis.betsjsf.domain.Event;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class EventsViewBean implements Serializable {
    private String eventDescription;
    private LocalDate eventDate;
    private List<Event> events;

    private final EventDAO eventDAO;

    public EventsViewBean() {
        eventDAO = new EventDAOImpl();
        // Inserto dos eventos para que se muestren en la vista

        Event e = new Event("Barsa-Madrid", LocalDate.now());
        Event e2 = new Event("Alaves-Barca", LocalDate.now());
        eventDAO.save(e);
        eventDAO.save(e2);

        // Cargo la fecha actual para que muestre directamente los eventos de hoy
        this.eventDate = LocalDate.now();
        getEventsByDate();
    }

    public void getEventsByDate() {
        this.events = eventDAO.getEventsByDate(this.eventDate);
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", event.getObject().toString()));
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
