package dev.sertis.betsjsf.bean;

import dev.sertis.betsjsf.dao.EventDAO;
import dev.sertis.betsjsf.dao.EventDAOHibernate;
import dev.sertis.betsjsf.domain.Event;
import dev.sertis.betsjsf.domain.User;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EventsViewBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDate eventDate;
    private List<Event> events;
    private Event selectedEvent;
    private AdminBean adminBean;
    private UserBean userBean;

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
        if (this.eventDate != null && this.eventDate.isBefore(LocalDate.now())){
            FacesContext.getCurrentInstance().addMessage("calendar",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,"Selecciona una fecha posterior a hoy.",null));
            return;
        }
        this.selectedEvent = selectedEvent;
        User loggedUser = LoginBean.getLoggedUser();
        if(loggedUser != null && loggedUser.isAdmin()) {
            adminBean.changeComponentFromEventList();
        } else if (loggedUser != null && !loggedUser.isAdmin()) {
            userBean.showCrearApuesta();
        }
    }

    public void getEventsByDate() {
        this.events = eventDAO.getEventsByDate(this.eventDate);
    }

    public void onDateSelect(SelectEvent<LocalDate> event) {
        if (event.getObject() != null && event.getObject().isBefore(LocalDate.now())){
            FacesContext.getCurrentInstance().addMessage("calendar",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,"Selecciona una fecha posterior a hoy.",null));
        }

        this.eventDate = event.getObject();
        getEventsByDate();
    }

    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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
