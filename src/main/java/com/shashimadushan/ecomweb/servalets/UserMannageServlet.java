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
import java.util.List;

@WebServlet(urlPatterns = "/manageuser")
public class UserMannageServlet extends HttpServlet {
    UserBO userBO = (UserBO) BOFactory.getBO(BOFactory.BOType.USER);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String userId = req.getParameter("userId");
        try {
            if (action != null && userId != null) {

                if (action.equals("activate")) {
                    userBO.activateUser(Integer.parseInt(userId));
                } else if (action.equals("deactivate")) {
                    userBO.deactivateUser(userId);
                }
            }
            List<UserDTO> userList = userBO.getAllUsers();
            req.setAttribute("userList", userList);
            req.getRequestDispatcher("userMannage.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Retrieve the updated user list and forward it to the JSP

    }
    }

