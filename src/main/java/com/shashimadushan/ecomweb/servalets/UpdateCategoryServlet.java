package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CategoryBO;
import com.shashimadushan.ecomweb.dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet(urlPatterns = "/updatecategory")
public class UpdateCategoryServlet extends HttpServlet {

    CategoryBO categoryBO = (CategoryBO) BOFactory.getBO(BOFactory.BOType.CATEGORY);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        String categoryName = req.getParameter("categoryName");

        if (categoryId != null && categoryName != null && !categoryName.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(categoryId);
                CategoryDTO category = new CategoryDTO(id, categoryName);
                boolean isUpdated = categoryBO.updateCategory(category);

                if (isUpdated) {
                    resp.sendRedirect("categorylist?success=Category updated successfully");
                } else {
                    req.setAttribute("errorMessage", "Failed to update category");
                    req.getRequestDispatcher("categories.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid category ID");
                req.getRequestDispatcher("categories.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("errorMessage", "Category name cannot be empty");
            req.getRequestDispatcher("categories.jsp").forward(req, resp);
        }
    }
}