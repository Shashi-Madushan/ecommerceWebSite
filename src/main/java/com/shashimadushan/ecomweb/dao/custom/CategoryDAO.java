package com.shashimadushan.ecomweb.dao.custom;

import com.shashimadushan.ecomweb.dao.SupperDAO;
import com.shashimadushan.ecomweb.entity.Category;
import java.util.List;

public interface CategoryDAO extends SupperDAO {

    // Create a new category
    boolean addCategory(Category category) throws Exception;

    // Retrieve a category by its ID
    Category getCategoryById(int    categoryId) throws Exception;

    // Update an existing category
    boolean updateCategory(Category category) throws Exception;

    // Delete a category by its ID
    boolean deleteCategory(int categoryId) throws Exception;

    // Retrieve all categories
    List<Category> getAllCategories() throws Exception;
}