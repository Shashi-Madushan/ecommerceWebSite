package com.shashimadushan.ecomweb.dao.impl;

import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.OrderDAO;
import com.shashimadushan.ecomweb.dao.custom.OrderDetailDAO;
import com.shashimadushan.ecomweb.entity.Order;
import com.shashimadushan.ecomweb.entity.OrderDetail;
import com.shashimadushan.ecomweb.entity.User;
import com.shashimadushan.ecomweb.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public boolean addOrderDetail(OrderDetail orderDetail) throws Exception {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(orderDetail);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public OrderDetail getOrderDetailById(String orderDetailId) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(OrderDetail.class, Long.parseLong(orderDetailId));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) throws Exception {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.merge(orderDetail);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteOrderDetail(String orderDetailId) throws Exception {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            OrderDetail orderDetail = session.get(OrderDetail.class, Long.parseLong(orderDetailId));
            if (orderDetail != null) {
                session.remove(orderDetail);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.createQuery("from OrderDetail", OrderDetail.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean saveOrderDetails(List<OrderDetail> orderDetails, Order order) throws Exception {
       Order order1 = orderDAO.getOrderById(String.valueOf(order.getOrderId()));
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            for (OrderDetail orderDetail : orderDetails) {
                orderDetail.setOrder(order1);
                session.persist(orderDetail);
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(String orderId) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Long orderLongId = null;
            try {
                orderLongId = Long.parseLong(orderId);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid order ID format", e);
            }
            return session.createQuery("from OrderDetail od where od.order.id = :orderId", OrderDetail.class)
                    .setParameter("orderId", orderLongId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}