package com.business.project.project03.bo;


import com.business.project.project03.bo.custom.impl.*;
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
        CUSTOMER, DRIVER, EXPORT_COMPANY, IMPORT_COMPANY, PART, STAFF
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case DRIVER:
                return new DriverBOImpl();
            case EXPORT_COMPANY:
                return new ExportCompanyBOImpl();
            case IMPORT_COMPANY:
                return new ImportCompanyBOImpl();
            case PART:
                return new PartBOImpl();
            case STAFF:
                return new StaffBOImpl();
            default:
                return null;
        }
    }
}
