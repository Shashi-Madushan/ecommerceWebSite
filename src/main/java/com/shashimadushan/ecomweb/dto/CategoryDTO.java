package com.shashimadushan.ecomweb.dto;

import com.shashimadushan.ecomweb.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int categoryId;
    private String name;


    public CategoryDTO(String categoryName) {
        this.name= categoryName;
    }
}
