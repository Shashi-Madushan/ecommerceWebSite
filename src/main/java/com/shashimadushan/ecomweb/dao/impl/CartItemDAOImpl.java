package com.shashimadushan.ecomweb.dao.impl;

import com.shashimadushan.ecomweb.dao.custom.CartItemDAO;
import com.shashimadushan.ecomweb.entity.CartItem;
import com.shashimadushan.ecomweb.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    @Override
    public boolean addCartItem(CartItem cartItem) throws Exception {
        cartItem.getUser().getUserId();
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(cartItem);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public CartItem getCartItemById(String cartItemId) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.get(CartItem.class, Long.parseLong(cartItemId));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) throws Exception {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.merge(cartItem);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public boolean deleteCartItem(String cartItemId) throws Exception {
        Transaction transaction = null;
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            CartItem cartItem = session.get(CartItem.class, Long.parseLong(cartItemId));
            if (cartItem != null) {
                session.remove(cartItem);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CartItem> getAllCartItems() throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            return session.createQuery("from CartItem", CartItem.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(String userId) throws Exception {
        Session session = null;
        try {
            session = FactoryConfiguration.getInstance().getSession();
            Long userLongId = null;
            try {
                userLongId = Long.parseLong(userId);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid user ID format", e);
            }
            return session.createQuery("from CartItem ci where ci.user.id = :userId", CartItem.class)
                    .setParameter("userId", userLongId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}