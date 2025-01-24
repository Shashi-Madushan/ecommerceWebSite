package com.shashimadushan.ecomweb.bo.custom;

import com.shashimadushan.ecomweb.bo.SupperBO;
import com.shashimadushan.ecomweb.dto.CategoryDTO;
import java.util.List;

public interface CategoryBO extends SupperBO {

    /**
     * Creates a new category.
     *
     * @param category the CategoryDTO object containing category details
     * @throws Exception if the category cannot be created
     */
    boolean createCategory(CategoryDTO category) throws Exception;

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param categoryId the unique identifier of the category
     * @return the CategoryDTO object representing the category
     * @throws Exception if the category cannot be found
     */
    CategoryDTO getCategory(int categoryId) throws Exception;

    /**
     * Updates an existing category.
     *
     * @param category the CategoryDTO object containing updated category details
     * @throws Exception if the category cannot be updated
     */
    boolean updateCategory(CategoryDTO category) throws Exception;

    /**
     * Deletes a category by its unique identifier.
     *
     * @param categoryId the unique identifier of the category
     * @throws Exception if the category cannot be deleted
     */
    boolean deleteCategory(int categoryId) throws Exception;

    /**
     * Retrieves all categories.
     *
     * @return a list of CategoryDTO objects representing all categories
     * @throws Exception if categories cannot be retrieved
     */
    List<CategoryDTO> getAllCategories() throws Exception;
}