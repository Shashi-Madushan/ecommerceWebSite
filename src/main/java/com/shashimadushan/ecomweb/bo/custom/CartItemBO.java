package com.shashimadushan.ecomweb.bo.custom;

import com.shashimadushan.ecomweb.bo.SupperBO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
import java.util.List;

public interface CartItemBO extends SupperBO {

    /**
     * Create a new cart item.
     *
     * @param cartItemDTO the cart item data transfer object
     * @throws Exception if an error occurs during cart item creation
     */
    boolean createCartItem(CartItemDTO cartItemDTO) throws Exception;

    /**
     * Retrieve a cart item by its ID.
     *
     * @param cartItemId the ID of the cart item
     * @return the cart item data transfer object
     * @throws Exception if an error occurs during cart item retrieval
     */
    CartItemDTO getCartItemById(String cartItemId) throws Exception;

    /**
     * Update an existing cart item.
     *
     * @param cartItemDTO the cart item data transfer object
     * @throws Exception if an error occurs during cart item update
     */
    boolean updateCartItem(CartItemDTO cartItemDTO) throws Exception;

    /**
     * Delete a cart item by its ID.
     *
     * @param cartItemId the ID of the cart item
     * @throws Exception if an error occurs during cart item deletion
     */
    boolean deleteCartItem(String cartItemId) throws Exception;

    /**
     * Retrieve cart items by user ID.
     *
     * @param userId the ID of the user
     * @return a list of cart item data transfer objects
     * @throws Exception if an error occurs during cart item retrieval
     */
    List<CartItemDTO> getCartItemsByUserId(String userId) throws Exception;


}