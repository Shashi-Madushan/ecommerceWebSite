package com.shashimadushan.ecomweb.bo.custom;

import com.shashimadushan.ecomweb.bo.SupperBO;
import com.shashimadushan.ecomweb.dto.OrderDetailDTO;
import java.util.List;

public interface OrderDetailBO extends SupperBO {

    /**
     * Create a new order detail.
     *
     * @param orderDetailDTO the order detail data transfer object
     * @throws Exception if an error occurs during order detail creation
     */
    void createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    /**
     * Retrieve an order detail by its ID.
     *
     * @param orderDetailId the ID of the order detail
     * @param detailId
     * @return the order detail data transfer object
     * @throws Exception if an error occurs during order detail retrieval
     */
    OrderDetailDTO getOrderDetailById(String orderDetailId, String detailId) throws Exception;

    /**
     * Update an existing order detail.
     *
     * @param orderDetailDTO the order detail data transfer object
     * @throws Exception if an error occurs during order detail update
     */
    boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception;

    /**
     * Delete an order detail by its ID.
     *
     * @param orderDetailId the ID of the order detail
     * @param detailId
     * @throws Exception if an error occurs during order detail deletion
     */
    boolean deleteOrderDetail(String orderDetailId, String detailId) throws Exception;

    /**
     * Retrieve all order details.
     *
     * @return a list of order detail data transfer objects
     * @throws Exception if an error occurs during order detail retrieval
     */
//    List<OrderDetailDTO> getAllOrderDetails() throws Exception;

    /**
     * Save a list of order details.
     *
     * @param orderDetailDTOs the list of order detail data transfer objects
     * @throws Exception if an error occurs during order detail saving
     */
    boolean saveOrderDetails(List<OrderDetailDTO> orderDetailDTOs) throws Exception;

    /**
     * Retrieve order details by order ID.
     *
     * @param orderId the ID of the order
     * @return a list of order detail data transfer objects
     * @throws Exception if an error occurs during order detail retrieval
     */
    List<OrderDetailDTO> getOrderDetailsByOrderId(String orderId) throws Exception;
}