package com.shashimadushan.ecomweb.dto;

import com.shashimadushan.ecomweb.entity.Order;
import com.shashimadushan.ecomweb.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {
    private Long id;
    private Order order;
    private Product product;
    private Integer quantity;
    private Double price; // Price at the time of order
}
