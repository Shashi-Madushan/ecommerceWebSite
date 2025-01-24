package com.shashimadushan.ecomweb.servalets.shit;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.OrderBO;
import com.shashimadushan.ecomweb.dto.OrderDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderServelet extends HttpServlet {

    private OrderBO orderBO = (OrderBO) BOFactory.getBO(BOFactory.BOType.ORDER);
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            OrderDTO order = gson.fromJson(reader, OrderDTO.class);
            orderBO.createOrder(order);
            String orderJson = gson.toJson(order);
            System.out.println("Stored Order Data: " + orderJson);
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.println("{\"message\": \"Order saved successfully\"}");
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Invalid JSON format\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            String orderId = request.getParameter("id");
            String customerId = request.getParameter("customerId");

            if (orderId != null) {
                OrderDTO order = orderBO.getOrderById(orderId);
                if (order != null) {
                    out.println(gson.toJson(order));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"error\": \"Order not found\"}");
                }
            } else if (customerId != null) {
                List<OrderDTO> orders = orderBO.getOrdersByCustomerId(customerId);
                out.println(gson.toJson(orders));
            } else {
                List<OrderDTO> orders = orderBO.getAllOrders();
                out.println(gson.toJson(orders));
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            OrderDTO order = gson.fromJson(reader, OrderDTO.class);
            boolean success = orderBO.updateOrder(order);
            if (success) {
                out.println("{\"message\": \"Order updated successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to update order\"}");
            }
        } catch (JsonSyntaxException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Invalid JSON format\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            String orderId = request.getParameter("id");
            boolean success = orderBO.deleteOrder(orderId);
            if (success) {
                out.println("{\"message\": \"Order deleted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to delete order\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}