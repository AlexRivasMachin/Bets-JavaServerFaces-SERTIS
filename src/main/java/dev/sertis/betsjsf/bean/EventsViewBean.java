package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.EventDAO;
import dev.sertis.betsjsf.dao.EventDAOHibernate;
import dev.sertis.betsjsf.domain.Event;
import dev.sertis.betsjsf.domain.User;
import org.primefaces.event.SelectEvent;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EventsViewBean implements Serializable {
    private LocalDate eventDate;
    private List<Event> events;
    private Event selectedEvent;
    private AdminBean adminBean;

    private final EventDAO eventDAO;

    public EventsViewBean() {
        eventDAO = EventDAOHibernate.getInstance();
        this.eventDate = LocalDate.now();
        getEventsByDate();
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

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
        User loggedUser = LoginBean.getLoggedUser();
        if(loggedUser != null && loggedUser.isAdmin()) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("questionAndForecasts", null);
            adminBean.changeComponentFromEventList();
        }
    }

    public void getEventsByDate() {
        this.events = eventDAO.getEventsByDate(this.eventDate);
    }

    public void onDateSelect(SelectEvent<LocalDate> event) {
        this.eventDate = event.getObject();
        getEventsByDate();
    }

    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }

    public String getEventLocalTeamLogo(String description){
        if (!description.contains("-"))
            return null;
        String[] teams = description.split("-");
        String logo = String.format("/resources/icons/laliga/%s.png", teams[0].trim().toLowerCase());
        return logo;
    }

    public String getEventVisitorTeamLogo(String description){
        if (!description.contains("-"))
            return null;
        String[] teams = description.split("-");
        return String.format("/resources/icons/laliga/%s.png", teams[1].trim().toLowerCase());
    }
}
