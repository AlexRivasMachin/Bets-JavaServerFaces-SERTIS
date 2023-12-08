package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Event;
import org.hibernate.Session;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EventDAOImpl implements EventDAO, Serializable {

    private final Session session;

    public EventDAOImpl() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public Event getEventById(Long eventId) {
        return session.find(Event.class, eventId);
    }

    @Override
    public List<Event> getEventsByDate(LocalDate date) {
        return session.createQuery("SELECT e FROM Event e WHERE e.eventDate = :date", Event.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public void save(Event event) {
        session.beginTransaction();
        session.persist(event);
        session.getTransaction().commit();
    }

    @Override
    public void update(Event event) {
        session.beginTransaction();
        session.merge(event);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Event event) {
        session.beginTransaction();
        session.remove(event);
        session.getTransaction().commit();
    }
}
