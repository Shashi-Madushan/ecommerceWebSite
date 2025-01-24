package com.shashimadushan.ecomweb.dao.custom;

import com.shashimadushan.ecomweb.dao.SupperDAO;
import com.shashimadushan.ecomweb.entity.Product;
import java.util.List;

public interface ProductDAO extends SupperDAO {

    // Create a new product
    boolean addProduct(Product product);

    // Retrieve a product by ID
    Product getProductById(int productId);

    // Retrieve all products
    List<Product> getAllProducts();

    // Update an existing product
    boolean updateProduct(Product product);

    // Delete a product by ID
    boolean deleteProduct(int productId);
}