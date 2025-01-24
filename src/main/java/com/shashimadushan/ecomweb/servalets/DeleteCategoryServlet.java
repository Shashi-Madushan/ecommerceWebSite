package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CategoryBO;
import com.shashimadushan.ecomweb.dao.custom.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/deletecategory")
public class DeleteCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the category ID from the request
        String categoryId = req.getParameter("categoryId");

        // Validate the input
        if (categoryId == null || categoryId.trim().isEmpty()) {
            // Redirect back with an error message if the category ID is invalid
            req.setAttribute("errorMessage", "Invalid category ID!");
            req.getRequestDispatcher("categorylist").forward(req, resp);
            return;
        }

        try {
            // Parse the category ID to an integer
            int id = Integer.parseInt(categoryId);

            // Delete the category using the DAO layer
            CategoryBO categoryBO = (CategoryBO) BOFactory.getBO(BOFactory.BOType.CATEGORY);
            boolean isDeleted = categoryBO.deleteCategory(id);

            // Redirect or forward based on the result
            if (isDeleted) {
                // Redirect back to the category page with success
                resp.sendRedirect("categorylist");
            } else {
                // Forward back to the category page with an error message
                req.setAttribute("errorMessage", "Failed to delete the category. Please try again.");
                req.getRequestDispatcher("categorylist").forward(req, resp);
            }
        } catch (Exception e) {
            // Handle invalid category ID format
            req.setAttribute("errorMessage", "Invalid category ID format!");
            req.getRequestDispatcher("categorylist").forward(req, resp);
        }
    }
}
