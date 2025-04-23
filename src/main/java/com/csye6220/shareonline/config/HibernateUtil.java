//package com.csye6220.shareonline.config;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class HibernateUtil {
//
//    private static final SessionFactory factory = buildSessionFactory();
//
//    private static SessionFactory buildSessionFactory() {
//        try {
//            return new Configuration().configure().buildSessionFactory();
//        } catch (Throwable ex) {
//            throw new ExceptionInInitializerError("Initial SessionFactory creation failed " + ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return factory;
//    }
//}
