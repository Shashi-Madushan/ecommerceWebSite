package com.shashimadushan.ecomweb.servalets;

import com.shashimadushan.ecomweb.bo.BOFactory;
import com.shashimadushan.ecomweb.bo.custom.CartItemBO;
import com.shashimadushan.ecomweb.bo.custom.ProductBO;
import com.shashimadushan.ecomweb.bo.custom.UserBO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import com.shashimadushan.ecomweb.entity.Product;
import com.shashimadushan.ecomweb.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.BufferedReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.modelmapper.ModelMapper;

@WebServlet(urlPatterns = "/adtocart")
public class AddToCartServlet extends HttpServlet {
    CartItemBO cartItemBO = (CartItemBO) BOFactory.getBO(BOFactory.BOType.CARTITEM);
    UserBO userBO = (UserBO) BOFactory.getBO(BOFactory.BOType.USER);
    ModelMapper modelMapper = new ModelMapper();
    ProductBO productBO = (ProductBO) BOFactory.getBO(BOFactory.BOType.PRODUCT);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set response content type
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Read JSON data from request
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        try (BufferedReader reader = req.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuffer.append(line);
            }
        }

        // Parse JSON data using Gson
        JsonObject jsonData = JsonParser.parseString(jsonBuffer.toString()).getAsJsonObject();

        // Extract product details
        String name = jsonData.get("name").getAsString();
        long productId = jsonData.get("productId").getAsLong();
        String description = jsonData.get("description").getAsString();
        String image = jsonData.get("image").getAsString();
        int stock = jsonData.get("stock").getAsInt();
        int quantity = jsonData.get("quantity").getAsInt();
        long   userId = jsonData.get("userId").getAsLong();

        System.out.println("Name: " + name);
        System.out.println("ProductId: " + productId);
        System.out.println("Description: " + description);
        System.out.println("Image: " + image);
        System.out.println("Stock: " + stock);
        System.out.println("Quantity: " + quantity);
        System.out.println("UserId: " + userId);

        try {
          UserDTO userDTO = userBO.getUser(String.valueOf(userId));
            System.out.println(userDTO.toString());
          User user = modelMapper.map(userDTO, User.class);
         ProductDTO productDTO = productBO.getProductById(String.valueOf(productId));
            Product product = modelMapper.map(productDTO, Product.class);
            System.out.println(product.toString());
            CartItemDTO cartItemDTO = new CartItemDTO(user,product,quantity);
           boolean saved = cartItemBO.createCartItem(cartItemDTO);
            System.out.println("saved "+saved);
            if (saved) {
                productDTO.setStock(stock-quantity);
              boolean updated=  productBO.updateProduct(productDTO);
                System.out.println("updated "+updated);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Send response back to client
        resp.getWriter().write("{\"status\":\"success\"}");
    }
}