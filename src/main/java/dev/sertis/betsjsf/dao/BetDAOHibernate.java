package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Bet;
import dev.sertis.betsjsf.domain.Forecast;
import dev.sertis.betsjsf.domain.User;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public class BetDAOHibernate implements BetDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private final Session session;
    private static BetDAOHibernate instance;

    private BetDAOHibernate() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static BetDAOHibernate getInstance() {
        if (instance == null) {
            instance = new BetDAOHibernate();
        }
        return instance;
    }

    public List<Bet> getBetsByForecastId(Long forecastID){
        return session.createQuery("SELECT b FROM Bet b WHERE b.associatedForecast.id = :forecastID", Bet.class)
                .setParameter("forecastID", forecastID)
                .getResultList();
    }

    @Override
    public void save(Bet bet) {
        try{
            session.beginTransaction();
            session.persist(bet);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Bet update(Bet bet) {
        Bet b = null;
        try{
            session.beginTransaction();
            b = session.merge(bet);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void delete(Bet bet) {
        try {
            session.beginTransaction();
            session.remove(bet);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Bet getBetById(Long id) {
        return session.find(Bet.class, id);
    }
}
