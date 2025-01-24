package com.shashimadushan.ecomweb.dao.impl;

import com.shashimadushan.ecomweb.dao.custom.CategoryDAO;
import com.shashimadushan.ecomweb.entity.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.shashimadushan.ecomweb.util.FactoryConfiguration;
import java.util.List;
import org.hibernate.query.Query;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public boolean addCategory(Category category) throws Exception {
        Transaction transaction = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction(); // Start the transaction
            session.persist(category); // Save the category
            transaction.commit(); // Commit the transaction
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback on error
            }
            e.printStackTrace(); // Log the exception
            return false;
        }
    }


    @Override
    public Category getCategoryById(int categoryId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Category.class, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateCategory(Category category) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(category);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int categoryId) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            if (category != null) {
                session.delete(category);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Category> getAllCategories() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Category> query = session.createQuery("FROM Category", Category.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}