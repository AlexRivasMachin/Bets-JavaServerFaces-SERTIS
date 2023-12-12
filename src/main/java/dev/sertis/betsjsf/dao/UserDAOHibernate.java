package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Bet;
import dev.sertis.betsjsf.domain.User;
import org.hibernate.Session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOHibernate implements UserDAO{

    private final Session session;

    public UserDAOHibernate() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public User getUserByDNI(String dni) throws IllegalArgumentException{
        return session.find(User.class, dni);
    }

    @Override
    public Set<Bet> getUserPlacedBetsByDNI(String dni) {
        List<Bet> userPlacedBets = session.createQuery("SELECT u.userPlacedBets FROM User u WHERE u.dni = :dni", Bet.class)
                .setParameter("dni", dni)
                .getResultList();
        return new HashSet<>(userPlacedBets);
    }

    @Override
    public void save(User user) {
        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) {
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
    }

    @Override
    public void delete(User user) {
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
    }
}
