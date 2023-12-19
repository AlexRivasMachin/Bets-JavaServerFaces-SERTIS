package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Forecast;
import org.hibernate.Session;

public class ForecastDAOHibernate implements ForecastDAO{

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
        session.beginTransaction();
        session.persist(forecast);
        session.getTransaction().commit();
    }

    @Override
    public void update(Forecast forecast) {
        try{
            session.beginTransaction();
            session.merge(forecast);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Forecast forecast) {
        session.beginTransaction();
        session.remove(forecast);
        session.getTransaction().commit();
    }

    @Override
    public Forecast getForecastById(Long forecastId) {
        return session.find(Forecast.class, forecastId);
    }
}
