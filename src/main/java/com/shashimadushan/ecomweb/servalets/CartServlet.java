package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CartItemBO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    CartItemBO cartItemBO = (CartItemBO) BOFactory.getBO(BOFactory.BOType.CARTITEM);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CartItemDTO> cartItemList = null;
        String errorMessage = null;

        HttpSession session = req.getSession(false);
        try {
            if (session != null && session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                String userId = String.valueOf(user.getId());
                cartItemList = cartItemBO.getCartItemsByUserId(userId);
                req.setAttribute("cartItems", cartItemList);
                for (CartItemDTO cartItemDTO : cartItemList) {
                    System.out.println(cartItemDTO.getProduct().getName());
                }
            } else {
                errorMessage = "User session not found. Please log in.";
            }
        } catch (Exception e) {
            errorMessage = "An error occurred while retrieving cart items: " + e.getMessage();
        }

        if (errorMessage != null) {
            req.setAttribute("error", errorMessage);
        }

        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }
}