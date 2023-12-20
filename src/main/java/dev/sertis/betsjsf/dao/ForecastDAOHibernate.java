package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Forecast;
import org.hibernate.Session;

import java.io.Serializable;

public class ForecastDAOHibernate implements ForecastDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private final Session session;
    private static ForecastDAOHibernate instance;

    private ForecastDAOHibernate() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static ForecastDAOHibernate getInstance() {
        if (instance == null) {
            instance = new ForecastDAOHibernate();
        }
        return instance;
    }

    @Override
    public void save(Forecast forecast) {
        try {
            session.beginTransaction();
            session.persist(forecast);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Forecast update(Forecast forecast) {
        Forecast f = null;
        try{
            session.beginTransaction();
            f =  session.merge(forecast);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public void delete(Forecast forecast) {
        try{
            session.beginTransaction();
            session.remove(forecast);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Forecast getForecastById(Long forecastId) {
        return session.find(Forecast.class, forecastId);
    }
}
