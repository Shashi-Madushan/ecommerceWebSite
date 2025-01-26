package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CartItemBO;
import com.shashimadushan.ecomweb.bo.custom.OrderBO;
import com.shashimadushan.ecomweb.bo.custom.ProductBO;
import com.shashimadushan.ecomweb.dto.OrderDTO;
import com.shashimadushan.ecomweb.dto.OrderDetailDTO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import com.shashimadushan.ecomweb.entity.Order;
import com.shashimadushan.ecomweb.entity.Product;
import com.shashimadushan.ecomweb.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/placeorder")
public class PlaceOrderServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(PlaceOrderServlet.class);

    private final ProductBO productBO = (ProductBO) BOFactory.getBO(BOFactory.BOType.PRODUCT);
    private final ModelMapper modelMapper = new ModelMapper();
    private final OrderBO orderBO = (OrderBO) BOFactory.getBO(BOFactory.BOType.ORDER);
    private final CartItemBO cartItemBO = (CartItemBO) BOFactory.getBO(BOFactory.BOType.CARTITEM);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] selectedItemIds = req.getParameterValues("selectedItems");
        String[] itemQuantities = req.getParameterValues("quantities");
        String[] productIds = req.getParameterValues("productIds");
        String total = req.getParameter("total");
        if (selectedItemIds == null) System.out.println("selcnull");
        if (itemQuantities == null) System.out.println("inull");
        if (productIds == null) System.out.println("pnul");
        if (selectedItemIds != null && itemQuantities != null && productIds != null) {
            List<OrderDetailDTO> orderDetails = new ArrayList<>();
            OrderDTO orderDTO = new OrderDTO();
            for (int i = 0; i < selectedItemIds.length; i++) {
                try {
                    String productId = productIds[i];
                    int quantity = Integer.parseInt(itemQuantities[i]);
                    ProductDTO productDTO = productBO.getProductById(productId);
                    Product product = modelMapper.map(productDTO, Product.class);

                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                    orderDetailDTO.setProduct(product);
                    orderDetailDTO.setQuantity(quantity);
                    orderDetailDTO.setPrice(product.getPrice() * quantity);
                    orderDetailDTO.setOrder(modelMapper.map(orderDTO, Order.class));
                    orderDetails.add(orderDetailDTO);


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            HttpSession session = req.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("user");
            if (userDTO == null) {
                resp.sendRedirect("login.jsp?error=Please login first.");
                return;
            }
            User user = modelMapper.map(userDTO, User.class);


            orderDTO.setOrderDetails(orderDetails);
            orderDTO.setOrderDate(new Date());
            orderDTO.setTotalAmount(Double.parseDouble(total));
            orderDTO.setUser(user);
            orderDTO.setStatus("Completed");

            try {
                boolean isSaved = orderBO.createOrder(orderDTO);
                if (isSaved) {

                    for (String id : selectedItemIds) {
                        cartItemBO.deleteCartItem(id);
                    }
                }
            } catch (Exception e) {
                logger.error("Error creating order", e);
            }

            resp.sendRedirect("cart");
        } else {
            resp.sendRedirect("cart.jsp?error=No items selected for order.");
        }
    }
}