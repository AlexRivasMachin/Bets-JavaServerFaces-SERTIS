package dev.sertis.betsjsf;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class HibernateUtil implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Fallo creando el SessionFactory." + ex);
            throw new ExceptionInInitializerError(ex);}
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
