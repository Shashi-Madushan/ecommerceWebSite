package com.shashimadushan.ecomweb.bo.impl;

import com.shashimadushan.ecomweb.bo.custom.OrderDetailBO;
import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.OrderDetailDAO;
import com.shashimadushan.ecomweb.dto.OrderDetailDTO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.entity.Order;
import com.shashimadushan.ecomweb.entity.OrderDetail;
import com.shashimadushan.ecomweb.entity.Product;
import com.shashimadushan.ecomweb.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void createOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        orderDetailDAO.addOrderDetail(modelMapper.map(orderDetailDTO, OrderDetail.class));
    }

    @Override
    public OrderDetailDTO getOrderDetailById(String orderDetailId, String detailId) throws Exception {
        return null;
    }

    @Override
    public boolean updateOrderDetail(OrderDetailDTO orderDetailDTO) throws Exception {
        return true;
    }

    @Override
    public boolean deleteOrderDetail(String orderDetailId, String detailId) throws Exception {
        return true;

    }

    /*    @Override
        public List<OrderDetailDTO> getAllOrderDetails() throws Exception {
            return List.of();
        }*/
    @Override
    public boolean saveOrderDetails(List<OrderDetailDTO> orderDetailDTOs, Order order) throws Exception {
        return orderDetailDAO.saveOrderDetails(modelMapper.map(orderDetailDTOs, new TypeToken<List<OrderDetail>>() {
        }.getType()), order);

    }

    @Override
    public List<OrderDetailDTO> getOrderDetailsByOrderId(String orderId) throws Exception {
        List<OrderDetail> orderDetails = orderDetailDAO.getOrderDetailsByOrderId(orderId);
        return modelMapper.map(orderDetails, new TypeToken<List<OrderDetailDTO>>() {
        }.getType());
    }
}
