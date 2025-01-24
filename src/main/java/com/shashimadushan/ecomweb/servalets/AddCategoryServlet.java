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

@WebServlet(urlPatterns = "/addcategory")
public class AddCategoryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the category name from the request
        String categoryName = req.getParameter("categoryName");
        System.out.println(categoryName);
        // Validate the input
        if (categoryName == null || categoryName.trim().isEmpty()) {
            // Redirect back with an error message if the name is invalid
            req.setAttribute("errorMessage", "Category name cannot be empty!");
            req.getRequestDispatcher("/categories.jsp").forward(req, resp);
            return;
        }

        // Add the category using the DAO layer
        CategoryBO categoryBO = (CategoryBO) BOFactory.getBO(BOFactory.BOType.CATEGORY);
        try {
            boolean isAdded = categoryBO.createCategory(new CategoryDTO(categoryName));
            if (isAdded) {
                // Redirect back to the category page with success
                resp.sendRedirect("categorylist");
            } else {
                // Forward back to the category page with an error message
                req.setAttribute("errorMessage", "Failed to add the category. Please try again.");
                req.getRequestDispatcher("categorylist").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}

