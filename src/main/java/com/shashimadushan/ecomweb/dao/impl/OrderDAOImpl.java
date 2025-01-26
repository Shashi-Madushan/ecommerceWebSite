package com.shashimadushan.ecomweb.dao.impl;

import com.shashimadushan.ecomweb.dao.custom.OrderDAO;
import com.shashimadushan.ecomweb.entity.Order;
import org.hibernate.Session;
import com.shashimadushan.ecomweb.util.FactoryConfiguration;
import org.hibernate.Transaction;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean addOrder(Order order) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Order getOrderById(String orderId) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(Order.class, Long.parseLong(orderId));
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean updateOrder(Order order) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean deleteOrder(String orderId) throws Exception {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            Order order = session.getReference(Order.class, Long.parseLong(orderId));
            session.remove(order);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Order> getAllOrders() throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.createQuery("FROM Order", Order.class).list();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Order> getOrdersByCustomerId(String customerId) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            String hql = "FROM Order WHERE user.userId = :customerId";
            return session.createQuery(hql, Order.class)
                    .setParameter("customerId", Long.parseLong(customerId))
                    .list();
        } finally {
            if (session != null) session.close();
        }
    }
}