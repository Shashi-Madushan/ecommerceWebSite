package com.shashimadushan.ecomweb.servalets.shit;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CategoryBO;
import com.shashimadushan.ecomweb.dto.CategoryDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryServelet extends HttpServlet {
    CategoryBO categoryBO = (CategoryBO) BOFactory.getBO(BOFactory.BOType.CATEGORY);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            // Parse JSON data from the request body
            BufferedReader reader = request.getReader();
            StringBuilder jsonBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBody.append(line);
            }

            // Convert JSON string to an object using Gson or Jackson
            String jsonData = jsonBody.toString();
            Gson gson = new Gson(); // If you are using Gson library
            CategoryDTO categoryDTO = gson.fromJson(jsonData, CategoryDTO.class);

            // Pass the object to your business logic
            boolean success = categoryBO.createCategory(categoryDTO);

            // Prepare the response
            if (success) {
                response.setStatus(HttpServletResponse.SC_CREATED);
                out.println("{\"message\": \"Category created successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to create category\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        } finally {
            out.close();
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            String categoryId = request.getParameter("id");
            Gson gson = new Gson();

            if (categoryId != null) {
                CategoryDTO categoryDTO = categoryBO.getCategory(Integer.parseInt(categoryId));
                if (categoryDTO != null) {
                    out.println(gson.toJson(categoryDTO));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.println("{\"error\": \"Category not found\"}");
                }
            } else {
                List<CategoryDTO> categories = categoryBO.getAllCategories();
                out.println(gson.toJson(categories)); // Convert list to JSON array
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            int categoryId = (int) Long.parseLong(request.getParameter("id"));
            String categoryName = request.getParameter("name");

            CategoryDTO categoryDTO = new CategoryDTO(categoryId,categoryName);
            boolean success = categoryBO.updateCategory(categoryDTO);

            if (success) {
                out.println("{\"message\": \"Category updated successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to update category\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try {
            String categoryId = request.getParameter("id");

            boolean success = categoryBO.deleteCategory(Integer.parseInt(categoryId));

            if (success) {
                out.println("{\"message\": \"Category deleted successfully\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.println("{\"error\": \"Failed to delete category\"}");
            }

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}