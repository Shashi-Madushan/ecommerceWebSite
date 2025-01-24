package com.shashimadushan.ecomweb.dto;

import com.shashimadushan.ecomweb.entity.Product;
import com.shashimadushan.ecomweb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    private Long id;

    private User user;


    private Product product;

    private Integer quantity;

    public CartItemDTO(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }
}
