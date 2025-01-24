package com.shashimadushan.ecomweb.servalets.shit;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CartItemBO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
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

@WebServlet(urlPatterns = "/shitcart")
public class CartItemsServelet extends HttpServlet {

    private CartItemBO cartItemBO = (CartItemBO) BOFactory.getBO(BOFactory.BOType.CARTITEM);
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        try {
            BufferedReader reader = request.getReader();
            CartItemDTO cartItem = gson.fromJson(reader, CartItemDTO.class);
            cartItemBO.createCartItem(cartItem);
            out.println("{\"message\": \"Cart item created successfully\"}");
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
            String cartItemId = request.getParameter("id");
            String userId = request.getParameter("userId");
            if (cartItemId != null) {
                CartItemDTO cartItem = cartItemBO.getCartItemById(cartItemId);
                if (cartItem != null) {
                    out.println(gson.toJson(cartItem));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"error\": \"Cart item not found\"}");
                }
            } else if (userId != null) {
                List<CartItemDTO> cartItems = cartItemBO.getCartItemsByUserId(userId);
                out.println(gson.toJson(cartItems));
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
            CartItemDTO cartItem = gson.fromJson(reader, CartItemDTO.class);
            cartItemBO.updateCartItem(cartItem);
            out.println("{\"message\": \"Cart item updated successfully\"}");
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
            String cartItemId = request.getParameter("id");
            cartItemBO.deleteCartItem(cartItemId);
            out.println("{\"message\": \"Cart item deleted successfully\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}