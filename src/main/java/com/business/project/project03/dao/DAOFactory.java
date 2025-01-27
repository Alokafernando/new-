package com.business.project.project03.dao;


import com.business.project.project03.dao.custom.impl.*;

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
        CUSTOMER, DRIVER, EXPORT_COMPANY, IMPORT_COMPANY, PART, STAFF, SUPPLIER, TRANSPORT, RESERVATION, PAYMENT, VEHICLE, CHECK
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
            case PART:
                return new PartDAOImpl();
            case STAFF:
                return new StaffDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case TRANSPORT:
                return new TransportDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case VEHICLE:
                return new VehicleDAOImpl();
            case CHECK:
                return new CheckDAOImpl();
            default:
                return null;
        }
    }
}
