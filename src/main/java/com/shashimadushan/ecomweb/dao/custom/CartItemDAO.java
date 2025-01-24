package com.shashimadushan.ecomweb.dao.custom;

import com.shashimadushan.ecomweb.dao.SupperDAO;
import com.shashimadushan.ecomweb.entity.CartItem;
import java.util.List;

public interface CartItemDAO extends SupperDAO {

    // Create a new cart item
    boolean addCartItem(CartItem cartItem) throws Exception;

    // Retrieve a cart item by its ID
    CartItem getCartItemById(String cartItemId) throws Exception;

    // Update an existing cart item
    boolean updateCartItem(CartItem cartItem) throws Exception;

    // Delete a cart item by its ID
    boolean deleteCartItem(String cartItemId) throws Exception;

    // Retrieve all cart items
    List<CartItem> getAllCartItems() throws Exception;

    // Retrieve cart items by cart ID
    List<CartItem> getCartItemsByUserId(String userId) throws Exception;
}