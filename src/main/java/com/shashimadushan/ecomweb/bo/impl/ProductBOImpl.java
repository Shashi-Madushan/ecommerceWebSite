package com.shashimadushan.ecomweb.bo.impl;

import com.shashimadushan.ecomweb.bo.custom.ProductBO;
import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.ProductDAO;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.PRODUCT);
   ModelMapper modelMapper = new ModelMapper();

    @Override
    public boolean addProduct(ProductDTO productDTO) throws Exception {
        return productDAO.addProduct(modelMapper.map(productDTO, Product.class));

    }

    @Override
    public ProductDTO getProductById(String productId) throws Exception {
        Product product = productDAO.getProductById(Integer.parseInt(productId));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws Exception {

        return productDAO.updateProduct(modelMapper.map(productDTO, Product.class));

    }

    @Override
    public boolean deleteProduct(String productId) throws Exception {
      return productDAO.deleteProduct(Integer.parseInt(productId));
    }

    @Override
    public List<ProductDTO> getAllProducts() throws Exception {
        List<Product> products = productDAO.getAllProducts();
        return modelMapper.map(products, new TypeToken<List<ProductDTO>>() {}.getType());
    }
}
