package com.example.ecommerce.orderservice.service;

import com.example.ecommerce.orderservice.config.HibernateConfig;
import com.example.ecommerce.orderservice.models.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private HibernateConfig hibernateConfig;

    public void saveOrder(Order order) {
        System.out.println("INSIDE ORDER SERVICE");
        Session session = hibernateConfig.createConfig();
        Transaction transaction = session.beginTransaction();
        session.persist(order);
        transaction.commit();
    }

    public List<Order> getAllOrders() {
        Session session = hibernateConfig.createConfig();
        Transaction transaction = session.beginTransaction();
        Query sessionQuery = session.createQuery("FROM Order", Order.class);
        List resultList = sessionQuery.getResultList();
        transaction.commit();
        return null;
    }
}
