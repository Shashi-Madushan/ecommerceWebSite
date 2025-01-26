package com.shashimadushan.ecomweb.dao.custom;

import com.shashimadushan.ecomweb.dao.SupperDAO;
import com.shashimadushan.ecomweb.entity.Order;
import com.shashimadushan.ecomweb.entity.OrderDetail;
import com.shashimadushan.ecomweb.entity.User;

import java.util.List;

public interface OrderDetailDAO extends SupperDAO {

    // Create a new order detail
    boolean addOrderDetail(OrderDetail orderDetail) throws Exception;

    // Retrieve an order detail by its ID
    OrderDetail getOrderDetailById(String orderDetailId) throws Exception;

    // Update an existing order detail
    boolean updateOrderDetail(OrderDetail orderDetail) throws Exception;

    // Delete an order detail by its ID
    boolean deleteOrderDetail(String orderDetailId) throws Exception;

    // Retrieve all order details
    List<OrderDetail> getAllOrderDetails() throws Exception;

    // Save a list of order details
    boolean saveOrderDetails(List<OrderDetail> orderDetails , Order order) throws Exception;

    // Retrieve order details by order ID
    List<OrderDetail> getOrderDetailsByOrderId(String orderId) throws Exception;
}