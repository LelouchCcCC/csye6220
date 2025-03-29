package com.csye6220.shareonline.dao;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public abstract class DAO {

    private static final Configuration cfg = new Configuration();
    private static final SessionFactory sessionFactory
            = cfg.configure("hibernate.cfg.xml").buildSessionFactory();

    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<>();
    private static final Logger log = Logger.getAnonymousLogger();

    protected DAO() {}

    public static Session getSession() {
        Session session = sessionThread.get();
        if (session == null) {
            session = sessionFactory.openSession();
            sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Cannot Rollback", e);
        }
    }

    public static void close() {
        getSession().close();
        sessionThread.remove();
    }
}
