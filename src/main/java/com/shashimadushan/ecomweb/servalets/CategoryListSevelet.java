package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CategoryBO;
import com.shashimadushan.ecomweb.dto.CategoryDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryListSevelet", value= "/categorylist")
public class CategoryListSevelet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryDTO> categoryList;
        CategoryBO categoryBO = (CategoryBO) BOFactory.getBO(BOFactory.BOType.CATEGORY);
        try {
            // Fetch all categories
            categoryList = categoryBO.getAllCategories();
            // Pass the list of categories to the JSP
            req.setAttribute("categorie-list", categoryList);

            // Forward the request to the JSP
            RequestDispatcher rd = req.getRequestDispatcher("categories.jsp");
            rd.forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("categories.jsp?error=Failed to retrieve categories");
        }
    }

}
