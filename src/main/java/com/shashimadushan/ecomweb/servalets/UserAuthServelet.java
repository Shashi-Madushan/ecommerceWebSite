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

@WebServlet("/auth")
public class UserAuthServelet extends HttpServlet {
    UserBO userBO = (UserBO) BOFactory.getBO(BOFactory.BOType.USER);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            UserDTO user = userBO.verifyUser(username, password);
            if (user != null) {
                // User authenticated, save their data to the session
                HttpSession session = req.getSession();


                if (user.getStatus()){
                    session.setAttribute("user", user);
                    if ("admin".equalsIgnoreCase(user.getRole())) {
                        resp.sendRedirect("adminDashboard.jsp");
                    } else if ("customer".equalsIgnoreCase(user.getRole())) {
                        resp.sendRedirect("customerCart.jsp");
                    } else {
                        resp.sendRedirect("signin.jsp?error=Invalid role");
                    }
                }else {
                    resp.sendRedirect("signin.jsp?error=Account deactivated");
                }

            } else {
                resp.sendRedirect("signin.jsp?error=Invalid credentials");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("signin.jsp?error=An error occurred");
        }
    }
}