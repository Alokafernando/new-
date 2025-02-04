package lk.ijse.pos.leyard.bo;


import lk.ijse.pos.leyard.bo.custom.impl.*;

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
    CUSTOMER, DRIVER, EXPORT_COMPANY, IMPORT_COMPANY, PART, STAFF, SUPPLIER, TRANSPORT, RESERVATION, PAYMENT, VEHICLE, CHECK, TAX, PART_DETAIL, SUPPLY_DETAIL
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
            case SUPPLIER:
                return new SupplierBOImpl();
            case TRANSPORT:
                return new TransportBOImpl();
            case RESERVATION:
                return new ReservationBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case CHECK:
                return new CheckBOImpl();
            case TAX:
                return new TaxBOImpl();
            case PART_DETAIL:
                return new PartDetailBOImpl();
            case SUPPLY_DETAIL:
                return new SupplyDetailBOImpl();
            default:
                return null;
        }
    }
}
