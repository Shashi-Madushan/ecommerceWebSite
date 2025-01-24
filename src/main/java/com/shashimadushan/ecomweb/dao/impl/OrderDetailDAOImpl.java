package com.shashimadushan.ecomweb.dao.impl;

import com.shashimadushan.ecomweb.dao.custom.OrderDetailDAO;
import com.shashimadushan.ecomweb.entity.OrderDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean addOrderDetail(OrderDetail orderDetail) throws Exception {
        try {
            entityManager.persist(orderDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderDetail getOrderDetailById(String orderDetailId) throws Exception {
        try {
            return entityManager.find(OrderDetail.class, Long.parseLong(orderDetailId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public boolean updateOrderDetail(OrderDetail orderDetail) throws Exception {
        try {
            entityManager.merge(orderDetail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteOrderDetail(String orderDetailId) throws Exception {
        try {
            OrderDetail orderDetail = getOrderDetailById(orderDetailId);
            if (orderDetail != null) {
                entityManager.remove(orderDetail);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderDetail> getAllOrderDetails() throws Exception {
        try {
            return entityManager.createQuery("SELECT od FROM OrderDetail od", OrderDetail.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    @Transactional
    public boolean saveOrderDetails(List<OrderDetail> orderDetails) throws Exception {
        try {
            for (OrderDetail orderDetail : orderDetails) {
                entityManager.persist(orderDetail);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(String orderId) throws Exception {
        try {
            return entityManager.createQuery("SELECT od FROM OrderDetail od WHERE od.order.id = :orderId", OrderDetail.class)
                    .setParameter("orderId", Long.parseLong(orderId))
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}