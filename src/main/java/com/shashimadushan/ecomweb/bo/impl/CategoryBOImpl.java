package com.shashimadushan.ecomweb.bo.impl;

import com.shashimadushan.ecomweb.bo.custom.CategoryBO;
import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.CategoryDAO;
import com.shashimadushan.ecomweb.dto.CartItemDTO;
import com.shashimadushan.ecomweb.dto.CategoryDTO;
import com.shashimadushan.ecomweb.entity.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.CATEGORY);
    ModelMapper modelMapper = new ModelMapper();
    @Override
    public boolean createCategory(CategoryDTO category) throws Exception {
     return categoryDAO.addCategory(modelMapper.map(category, Category.class));
    }

    @Override
    public CategoryDTO getCategory(int   categoryId) throws Exception {
        Category category = categoryDAO.getCategoryById(categoryId);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public boolean updateCategory(CategoryDTO category) throws Exception {
    return categoryDAO.updateCategory(modelMapper.map(category, Category.class));
    }

    @Override
    public boolean deleteCategory(int categoryId) throws Exception {
        return categoryDAO.deleteCategory(categoryId);
    }

    @Override
    public List<CategoryDTO> getAllCategories() throws Exception {
        List<Category> categoryList = categoryDAO.getAllCategories();
        return modelMapper.map(categoryList,new TypeToken<List<CategoryDTO>>(){}.getType());
    }
}
