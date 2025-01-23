package com.business.project.project03.dao;


import com.business.project.project03.dao.custom.impl.CustomerDAOImpl;
import com.business.project.project03.dao.custom.impl.DriverDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {

    }
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOType {
        CUSTOMER, DRIVER
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            default:
                return null;
        }
    }
}
