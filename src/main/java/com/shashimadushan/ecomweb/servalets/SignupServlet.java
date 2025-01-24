package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.UserBO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/signupAction")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserBO userBO = (UserBO) BOFactory.getBO(BOFactory.BOType.USER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Validate input
        if (fullName == null || email == null || password == null || fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            resp.sendRedirect("signup.jsp?error=Please fill in all fields.");
            return;
        }
        UserDTO userDTO = new UserDTO(fullName,email,password);
        userDTO.setStatus(true);
        try {
            userBO.saveUser(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}