package com.shashimadushan.ecomweb.bo.impl;

import com.shashimadushan.ecomweb.bo.custom.CartItemBO;
import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.CartItemDAO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
import com.shashimadushan.ecomweb.entity.CartItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class CartItemBOImpl implements CartItemBO {
    CartItemDAO cartItemDAO = (CartItemDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.CARTITEM );
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public boolean createCartItem(CartItemDTO cartItemDTO) throws Exception {
     return     cartItemDAO.addCartItem(modelMapper.map(cartItemDTO, CartItem.class));

    }

    @Override
    public CartItemDTO getCartItemById(String cartItemId) throws Exception {
        CartItem cartItem= cartItemDAO.getCartItemById(cartItemId);
        return modelMapper.map(cartItem, CartItemDTO.class);
    }

    @Override
    public boolean updateCartItem(CartItemDTO cartItemDTO) throws Exception {
      return   cartItemDAO.updateCartItem(modelMapper.map(cartItemDTO, CartItem.class));
    }

    @Override
    public boolean deleteCartItem(String cartItemId) throws Exception {
      return   cartItemDAO.deleteCartItem(cartItemId);
    }

    @Override
    public List<CartItemDTO> getCartItemsByUserId(String userId) throws Exception {
        List<CartItem> cartItems = cartItemDAO.getCartItemsByUserId(userId);
        return modelMapper.map(cartItems, new TypeToken<List<CartItemDTO>>(){}.getType());
    }



}
