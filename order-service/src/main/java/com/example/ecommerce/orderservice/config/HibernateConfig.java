package com.example.ecommerce.orderservice.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfig {

    private static Session session;

    public static Session getSession() {
        return session;
    }

    public Session createConfig() {
        SessionFactory sessionFactory = new Configuration()
                .addClass(com.example.ecommerce.orderservice.models.Order.class)
                .buildSessionFactory();
        session = sessionFactory.openSession();
        return session;
    }

}
