package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Event;
import org.hibernate.Session;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class EventDAOHibernate implements EventDAO, Serializable {

    private final Session session;
    private static EventDAOHibernate instance;

    private EventDAOHibernate() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static EventDAOHibernate getInstance() {
        if (instance == null) {
            instance = new EventDAOHibernate();
        }
        return instance;
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
        try{
            session.beginTransaction();
            session.persist(event);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        try {
            session.beginTransaction();
            session.merge(event);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Event event) {
        try{
            session.beginTransaction();
            session.remove(event);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
