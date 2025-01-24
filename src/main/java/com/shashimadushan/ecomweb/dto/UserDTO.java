package com.shashimadushan.ecomweb.dto;

import com.shashimadushan.ecomweb.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role = "CUSTOMER";
    private Boolean status;
    private List<Order> orders;

    public UserDTO(String fullName, String email, String password) {
        this.username = fullName;
        this.email = email;
        this.password = password;
    }

    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }
}