package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.ProductBO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/deleteproduct")
public class DeleteProductServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(DeleteProductServlet.class.getName());
    private final ProductBO productBO = (ProductBO) BOFactory.getBO(BOFactory.BOType.PRODUCT);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");

        if (productId == null || productId.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("{\"error\": \"Product ID is required\"}");
            return;
        }

        try {
            // Retrieve the product to get the image URL
            ProductDTO product = productBO.getProductById(productId);
            String imageUrl = product.getImagepath();

            // Delete the product
            boolean success = productBO.deleteProduct(productId);

            if (success) {
                // Delete the image file
                if (imageUrl != null && !imageUrl.trim().isEmpty()) {
                    File imageFile = new File(imageUrl);
                    if (imageFile.exists() && imageFile.delete()) {
                        LOGGER.info("Image file deleted successfully: " + imageUrl);
                    } else {
                        LOGGER.warning("Failed to delete image file: " + imageUrl);
                    }
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("{\"message\": \"Product and associated image deleted successfully\"}");
                resp.sendRedirect("/product-list");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("{\"error\": \"Failed to delete product\"}");
                resp.sendRedirect("/product-list");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting product", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("{\"error\": \"An internal error occurred\"}");
            resp.sendRedirect("/product-list");
        }
    }
}