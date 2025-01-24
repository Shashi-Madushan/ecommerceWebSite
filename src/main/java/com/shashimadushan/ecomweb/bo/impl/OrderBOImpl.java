package com.shashimadushan.ecomweb.bo.impl;

import com.shashimadushan.ecomweb.bo.custom.OrderBO;
import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.OrderDAO;
import com.shashimadushan.ecomweb.dao.impl.OrderDAOImpl;
import com.shashimadushan.ecomweb.dto.OrderDTO;
import com.shashimadushan.ecomweb.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.ORDER);
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public void createOrder(OrderDTO orderDTO) throws Exception {
        orderDAO.addOrder(modelMapper.map(orderDTO, Order.class));
    }

    @Override
    public OrderDTO getOrderById(String orderId) throws Exception {
        Order order= orderDAO.getOrderById(orderId);
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {
            return orderDAO.updateOrder(modelMapper.map(orderDTO, Order.class));
    }

    @Override
    public boolean deleteOrder(String orderId) throws Exception {
        return orderDAO.deleteOrder(orderId);

    }

    @Override
    public List<OrderDTO> getAllOrders() throws Exception {
       List<Order> orders = orderDAO.getAllOrders();
       return modelMapper.map(orders,new TypeToken<List<OrderDTO>>(){}.getType());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(String customerId) throws Exception {
        List<Order> orders = orderDAO.getOrdersByCustomerId(customerId);
        return modelMapper.map(orders,new TypeToken<List<OrderDTO>>(){}.getType());
    }
}
