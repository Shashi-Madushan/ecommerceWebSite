package com.shashimadushan.ecomweb.bo;

import com.shashimadushan.ecomweb.bo.impl.*;

public class BOFactory {
    public enum BOType {
        USER, PRODUCT, ORDER, ORDERDETAIL, CATEGORY, CARTITEM
    }

    public static SupperBO getBO(BOType type) {
        switch (type) {
            case USER:
                return new UserBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
            case CATEGORY:
                return new CategoryBOImpl();
            case CARTITEM:
                return new CartItemBOImpl();
            default:
                return null;


        }
    }
}
