package com.business.project.project03.bo;


import com.business.project.project03.bo.custom.impl.CustomerBOImpl;
import com.business.project.project03.bo.custom.impl.DriverBOImpl;
import com.business.project.project03.dao.custom.impl.DriverDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {

    }
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOType {
        CUSTOMER, DRIVER
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            default:
                return null;
        }
    }
}
