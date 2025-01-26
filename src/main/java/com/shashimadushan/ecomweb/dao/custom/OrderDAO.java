package com.shashimadushan.ecomweb.dao.custom;

import com.shashimadushan.ecomweb.dao.SupperDAO;
import com.shashimadushan.ecomweb.entity.Order;
import java.util.List;

public interface OrderDAO extends SupperDAO {

    // Create a new order
    Order addOrder(Order order) throws Exception;

    // Retrieve an order by its ID
    Order getOrderById(String orderId) throws Exception;

    // Update an existing order
    boolean updateOrder(Order order) throws Exception;

    // Delete an order by its ID
    boolean deleteOrder(String orderId) throws Exception;

    // Retrieve all orders
    List<Order> getAllOrders() throws Exception;

    // Retrieve orders by customer ID
    List<Order> getOrdersByCustomerId(String customerId) throws Exception;
}