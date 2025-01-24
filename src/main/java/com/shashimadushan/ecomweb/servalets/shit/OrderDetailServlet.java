package com.shashimadushan.ecomweb.servalets.shit;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.OrderDetailBO;
import com.shashimadushan.ecomweb.dto.OrderDetailDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.List;

@WebServlet(urlPatterns = "/orderDetail")
public class OrderDetailServlet extends HttpServlet {

     OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBO(BOFactory.BOType.ORDERDETAIL);
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            OrderDetailDTO orderDetail = gson.fromJson(reader, OrderDetailDTO.class);
            orderDetailBO.createOrderDetail(orderDetail);
            out.println("{\"message\": \"Order detail saved successfully\"}");
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
            String orderId = request.getParameter("orderId");
            String detailId = request.getParameter("detailId");
            if (orderId != null && detailId != null) {
                OrderDetailDTO orderDetail = orderDetailBO.getOrderDetailById(orderId, detailId);
                if (orderDetail != null) {
                    out.println(gson.toJson(orderDetail));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"error\": \"Order detail not found\"}");
                }
            } else {
                List<OrderDetailDTO> orderDetails = orderDetailBO.getOrderDetailsByOrderId(orderId);
                out.println(gson.toJson(orderDetails));
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
            OrderDetailDTO orderDetail = gson.fromJson(reader, OrderDetailDTO.class);
            boolean success = orderDetailBO.updateOrderDetail(orderDetail);
            if (success) {
                out.println("{\"message\": \"Order detail updated successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to update order detail\"}");
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
            String orderId = request.getParameter("orderId");
            String detailId = request.getParameter("detailId");
            boolean success = orderDetailBO.deleteOrderDetail(orderId, detailId);
            if (success) {
                out.println("{\"message\": \"Order detail deleted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to delete order detail\"}");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}