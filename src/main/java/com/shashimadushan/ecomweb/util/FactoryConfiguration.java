package com.shashimadushan.ecomweb.util;

import com.shashimadushan.ecomweb.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.Session;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    // Private constructor for Singleton
    private FactoryConfiguration() {
        Configuration config = new Configuration().configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .addAnnotatedClass(CartItem.class);

        sessionFactory = config.buildSessionFactory();
    }

    // Singleton instance getter
    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }

    // Method to get a Hibernate session
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
