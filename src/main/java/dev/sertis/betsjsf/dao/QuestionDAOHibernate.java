package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.HibernateUtil;
import dev.sertis.betsjsf.domain.Question;
import org.hibernate.Session;

import java.io.Serializable;

public class QuestionDAOHibernate implements QuestionDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private final Session session;
    private static QuestionDAOHibernate instance;

    private QuestionDAOHibernate() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public static QuestionDAOHibernate getInstance() {
        if (instance == null) {
            instance = new QuestionDAOHibernate();
        }
        return instance;
    }

    @Override
    public void save(Question question) {
        try {
            session.beginTransaction();
            session.persist(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    @Override
    public Question update(Question question) {
        Question q = null;
        try {
            session.beginTransaction();
            q = session.merge(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return q;
    }

    @Override
    public void delete(Question question) {
        try {
            session.beginTransaction();
            session.remove(question);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Question getQuestionById(Long questionId) {
        return session.find(Question.class, questionId);
    }
}
