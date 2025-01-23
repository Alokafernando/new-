package com.business.project.project03.dao;


import com.business.project.project03.dao.custom.impl.CustomerDAOImpl;
import com.business.project.project03.dao.custom.impl.DriverDAOImpl;
import com.business.project.project03.dao.custom.impl.ExportCompanyDAOImpl;
import com.business.project.project03.dao.custom.impl.ImportCompanyDAOImpl;

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
        CUSTOMER, DRIVER, EXPORT_COMPANY, IMPORT_COMPANY
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DRIVER:
                return new DriverDAOImpl();
            case EXPORT_COMPANY:
                return new ExportCompanyDAOImpl();
            case IMPORT_COMPANY:
                return new ImportCompanyDAOImpl();
            default:
                return null;
        }
    }
}
