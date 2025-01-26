package com.shashimadushan.ecomweb.dto;

import com.shashimadushan.ecomweb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private User user; // To reference the user who placed the order
    private Date createdAt;
    private Double totalAmount;
    private String status; // e.g., "Pending", "Completed"
}
