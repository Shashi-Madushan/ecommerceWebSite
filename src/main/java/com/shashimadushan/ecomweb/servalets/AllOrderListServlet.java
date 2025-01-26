package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.OrderBO;
import com.shashimadushan.ecomweb.dto.OrderDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet(urlPatterns = "/orderlist")
public class AllOrderListServlet extends HttpServlet {
    private OrderBO orderBO;

    @Override
    public void init() throws ServletException {
        orderBO = (OrderBO) BOFactory.getBO(BOFactory.BOType.ORDER) ;// Initialize your business object (or inject it if using dependency injection)
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String orderId = request.getParameter("id");
            String customerId = request.getParameter("customerId");

            if (orderId != null) {
                // Fetch a single order by ID
                OrderDTO order = orderBO.getOrderById(orderId);
                if (order != null) {
                    request.setAttribute("order", order); // Set the single order as an attribute
                    request.getRequestDispatcher("/orderDetails.jsp").forward(request, response); // Forward to details page
                } else {
                    request.setAttribute("error", "Order not found");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } else if (customerId != null) {
                // Fetch orders by customer ID
                List<OrderDTO> orders = orderBO.getOrdersByCustomerId(customerId);
                request.setAttribute("orders", orders); // Set the orders list as an attribute
                request.getRequestDispatcher("/customerOrders.jsp").forward(request, response); // Forward to customer orders page
            } else {
                // Fetch all orders
                List<OrderDTO> orders = orderBO.getAllOrders();
                request.setAttribute("orders", orders); // Set the orders list as an attribute
                request.getRequestDispatcher("/orderView.jsp").forward(request, response); // Forward to all orders page
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response); // Forward to an error page
        }
    }
}
