package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CategoryBO;
import com.shashimadushan.ecomweb.bo.custom.ProductBO;
import com.shashimadushan.ecomweb.dto.CategoryDTO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.entity.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/updateproduct")
public class UpdateProductServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UpdateProductServlet.class.getName());
    private final ProductBO productBO = (ProductBO) BOFactory.getBO(BOFactory.BOType.PRODUCT);
    CategoryBO categoryBO = (CategoryBO) BOFactory.getBO(BOFactory.BOType.CATEGORY);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String productName = req.getParameter("productName");
        String productDescription = req.getParameter("productDescription");
        String productCategory = req.getParameter("productCategory");
        String productPriceStr = req.getParameter("productPrice");
        String productStockStr = req.getParameter("productStock");
        String productImageUrl = req.getParameter("productImageUrl");

        if (productId == null || productId.trim().isEmpty() ||
            productName == null || productName.trim().isEmpty() ||
            productDescription == null || productDescription.trim().isEmpty() ||
            productCategory == null || productCategory.trim().isEmpty() ||
            productPriceStr == null || productPriceStr.trim().isEmpty() ||
            productStockStr == null || productStockStr.trim().isEmpty() ||
            productImageUrl == null || productImageUrl.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("{\"error\": \"All fields are required\"}");
            return;
        }

        try {
            double productPrice = Double.parseDouble(productPriceStr);
            int productStock = Integer.parseInt(productStockStr);
            CategoryDTO categoryDTO = categoryBO.getCategory(Integer.parseInt(productCategory));
            Category category = modelMapper.map(categoryDTO, Category.class);
            long id = Long.parseLong(productId);
            ProductDTO productDTO = new ProductDTO(id, productName, productDescription, productPrice, productStock, category, productImageUrl);
            boolean success = productBO.updateProduct(productDTO);

            if (success) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("{\"message\": \"Product updated successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("{\"error\": \"Failed to update product\"}");
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.WARNING, "Invalid number format", e);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("{\"error\": \"Invalid price or stock format\"}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating product", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("{\"error\": \"An internal error occurred\"}");
        }
    }
}