package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Bet;
import org.hibernate.Session;

import java.util.List;

public class BetDAOHibernate implements BetDAO{

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
        session.beginTransaction();
        session.persist(bet);
        session.getTransaction().commit();
    }

    @Override
    public void update(Bet bet) {
        session.beginTransaction();
        session.merge(bet);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Bet bet) {
        session.beginTransaction();
        session.remove(bet);
        session.getTransaction().commit();
    }

    @Override
    public Bet getBetById(Long id) {
        return session.find(Bet.class, id);
    }
}
