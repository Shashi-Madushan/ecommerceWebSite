package com.shashimadushan.ecomweb.dao;


import com.shashimadushan.ecomweb.dao.custom.UserDAO;
import com.shashimadushan.ecomweb.dao.impl.*;

public class DAOFactory {
    public enum DAOTypes {
        USER, PRODUCT, ORDERDETAIL, ORDER, CATEGORY, CARTITEM
    }

    public static SupperDAO getDAO(DAOTypes type) {
        switch (type) {
            case USER:
                return new UserDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case CATEGORY:
                return new CategoryDAOImpl();
            case CARTITEM:
                return new CartItemDAOImpl();
            default:
                return null;

        }
    }
}
