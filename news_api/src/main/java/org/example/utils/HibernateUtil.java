package org.example.utils;

import org.example.models.Category;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory =
            new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}