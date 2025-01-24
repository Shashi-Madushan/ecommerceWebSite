package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CartItemBO;
import com.shashimadushan.ecomweb.bo.custom.ProductBO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServelet", value = "/index")
public class IndexServelet extends HttpServlet {

    CartItemBO cartItemBO = (CartItemBO) BOFactory.getBO(BOFactory.BOType.CARTITEM);
    ProductBO productBO = (ProductBO) BOFactory.getBO(BOFactory.BOType.PRODUCT);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> productList;
        List<CartItemDTO> cartItemList = null;

        try {
            // Retrieve products
            productList = productBO.getAllProducts();
            req.setAttribute("products", productList);

            // Check if user is available in session
            HttpSession session = req.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                UserDTO user = (UserDTO) session.getAttribute("user");
                String userId = String.valueOf(user.getId());
                cartItemList = cartItemBO.getCartItemsByUserId(userId);
                int cartItemsCount = (cartItemList != null) ? cartItemList.size() : 0;
                req.setAttribute("cartItemsCount", cartItemsCount);
            }

            // Forward the request to the JSP
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("index.jsp?error=Failed to retrieve products or cart items");
        }
    }
}