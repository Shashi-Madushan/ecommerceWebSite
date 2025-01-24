package com.shashimadushan.ecomweb.bo.custom;

import com.shashimadushan.ecomweb.bo.SupperBO;
import com.shashimadushan.ecomweb.dto.OrderDTO;
import com.shashimadushan.ecomweb.entity.Order;

import java.util.List;

public interface OrderBO extends SupperBO {

    /**
     * Create a new order.
     *
     * @param orderDTO the order data transfer object
     * @throws Exception if an error occurs during order creation
     */
    void createOrder(OrderDTO orderDTO) throws Exception;

    /**
     * Retrieve an order by its ID.
     *
     * @param orderId the ID of the order
     * @return the order data transfer object
     * @throws Exception if an error occurs during order retrieval
     */
    OrderDTO getOrderById(String orderId) throws Exception;

    /**
     * Update an existing order.
     *
     * @param orderDTO the order data transfer object
     * @throws Exception if an error occurs during order update
     */
    boolean updateOrder(OrderDTO orderDTO) throws Exception;

    /**
     * Delete an order by its ID.
     *
     * @param orderId the ID of the order
     * @throws Exception if an error occurs during order deletion
     */
    boolean deleteOrder(String orderId) throws Exception;

    /**
     * Retrieve all orders.
     *
     * @return a list of order data transfer objects
     * @throws Exception if an error occurs during order retrieval
     */
    List<OrderDTO> getAllOrders() throws Exception;

    List<OrderDTO> getOrdersByCustomerId(String customerId) throws Exception;
}