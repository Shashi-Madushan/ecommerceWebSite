package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.UserBO;
import com.shashimadushan.ecomweb.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/updateuser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        UserBO userBO = (UserBO) BOFactory.getBO(BOFactory.BOType.USER);
        if (user != null) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Update user details
            user.setUsername(username);
            user.setEmail(email);
            if (password != null && !password.isEmpty()) {
                user.setPassword(password); // Assuming you have a method to set a new password
            }

            // Save updated user details
            boolean isUpdated = false;
            try {
                isUpdated = userBO.updateUser(user);
            } catch (Exception e) {
                e.printStackTrace();            }

            if (isUpdated) {
                session.setAttribute("user", user);
                response.sendRedirect("userAccount.jsp?status=success");
            } else {
                response.sendRedirect("userAccount.jsp?status=error");
            }
        } else {
            response.sendRedirect("signin.jsp");
        }
    }
}