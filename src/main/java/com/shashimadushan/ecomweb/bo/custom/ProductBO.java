package com.shashimadushan.ecomweb.bo.custom;

import com.shashimadushan.ecomweb.bo.SupperBO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import java.util.List;

public interface ProductBO extends SupperBO {

    /**
     * Adds a new product.
     *
     * @param productDTO the product data transfer object
     * @throws Exception if an error occurs during the operation
     */
    boolean addProduct(ProductDTO productDTO) throws Exception;

    /**
     * Retrieves a product by its ID.
     *
     * @param productId the ID of the product
     * @return the product data transfer object
     * @throws Exception if the product is not found or an error occurs
     */
    ProductDTO getProductById(String productId) throws Exception;

    /**
     * Updates an existing product.
     *
     * @param productDTO the product data transfer object
     * @throws Exception if an error occurs during the operation
     */
    boolean updateProduct(ProductDTO productDTO) throws Exception;

    /**
     * Deletes a product by its ID.
     *
     * @param productId the ID of the product
     * @throws Exception if the product is not found or an error occurs
     */
    boolean deleteProduct(String productId) throws Exception;

    /**
     * Retrieves all products.
     *
     * @return a list of product data transfer objects
     * @throws Exception if an error occurs during the operation
     */
    List<ProductDTO> getAllProducts() throws Exception;
}