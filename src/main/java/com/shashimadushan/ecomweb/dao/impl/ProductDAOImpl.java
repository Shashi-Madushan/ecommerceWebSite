package com.shashimadushan.ecomweb.dao.impl;

import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.CategoryDAO;
import com.shashimadushan.ecomweb.dao.custom.ProductDAO;
import com.shashimadushan.ecomweb.entity.Category;
import com.shashimadushan.ecomweb.entity.Product;
import com.shashimadushan.ecomweb.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean addProduct(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            // Fetch the category from the database to ensure it's managed
            Category managedCategory = session.get(Category.class, product.getCategory().getCategoryId());
            System.out.println("manage cat"+managedCategory.getCategoryId()+managedCategory.getName());
            if (managedCategory == null) {
                throw new IllegalArgumentException("Category not found in the database.");
            }
            product.setCategory(managedCategory);

            session.persist(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Product getProductById(int productId) {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(Product.class, productId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.merge(product);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, productId);
            if (product != null) {
                session.remove(product);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}