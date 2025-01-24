package com.shashimadushan.ecomweb.dto;

import com.shashimadushan.ecomweb.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Category category;
    private String imagepath;



    public ProductDTO(String name, String description, double price, int stock, Category category, String imagepath) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.imagepath = imagepath;
        this.category = category;
    }

    public ProductDTO(String productId, String productName, Category productCategory, double productPrice, int productStock) {
        this.id = Long.parseLong(productId);
        this.name = productName;
        this.category = productCategory;
        this.price = productPrice;
        this.stock = productStock;

    }
}
