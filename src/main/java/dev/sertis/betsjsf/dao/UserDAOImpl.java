package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.User;
import org.hibernate.Session;

public class UserDAOImpl implements UserDAO{

    private final Session session;

    public UserDAOImpl() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public User findByDNI(String dni) throws IllegalArgumentException{
        return session.find(User.class, dni);
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
