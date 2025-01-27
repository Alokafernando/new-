package com.business.project.project03.model;

import java.io.Serializable;

public class VehicleDTO implements Serializable {

    private String import_company_id;
    private String import_date;
    private String vehicle_id;
    private String model;
    private int year;
    private String color;
    private String current_status;
    private String export_company_id;
    private String export_date;
    private String sale_date;
    private double import_price;
    private double export_price;
    private String reservation_id;
    private String transport_id;

    public VehicleDTO() {}

    public VehicleDTO(String import_company_id, String import_date, String vehicle_id, String model, int year,
                      String color, String current_status, String export_company_id, String export_date,
                      String sale_date, double import_price, double export_price,
                      String reservation_id, String transport_id) {
        this.import_company_id = import_company_id;
        this.import_date = import_date;
        this.vehicle_id = vehicle_id;
        this.model = model;
        this.year = year;
        this.color = color;
        this.current_status = current_status;
        this.export_company_id = export_company_id;
        this.export_date = export_date;
        this.sale_date = sale_date;
        this.import_price = import_price;
        this.export_price = export_price;
        this.reservation_id = reservation_id;
        this.transport_id = transport_id;
    }

    public String getImport_company_id() {return import_company_id;}

    public void setImport_company_id(String import_company_id) {this.import_company_id = import_company_id;}

    public String getImport_date() {return import_date;}

    public void setImport_date(String import_date) {this.import_date = import_date;}

    public String getVehicle_id() {return vehicle_id;}

    public void setVehicle_id(String vehicle_id) {this.vehicle_id = vehicle_id;}

    public String getModel() {return model;}

    public void setModel(String model) {this.model = model;}

    public int getYear() {return year;}

    public void setYear(int year) {this.year = year;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public String getCurrent_status() {return current_status;}

    public void setCurrent_status(String current_status) {this.current_status = current_status;}

    public String getExport_company_id() {return export_company_id;}

    public void setExport_company_id(String export_company_id) {this.export_company_id = export_company_id;}

    public String getExport_date() {return export_date;}

    public void setExport_date(String export_date) {this.export_date = export_date;}

    public String getSale_date() {return sale_date;}

    public void setSale_date(String sale_date) {this.sale_date = sale_date;}

    public double getImport_price() {return import_price;}

    public void setImport_price(double import_price) {this.import_price = import_price;}

    public double getExport_price() {return export_price;}

    public void setExport_price(double export_price) {this.export_price = export_price;}

    public String getReservation_id() {return reservation_id;}

    public void setReservation_id(String reservation_id) {this.reservation_id = reservation_id;}

    public String getTransport_id() {return transport_id;}

    public void setTransport_id(String transport_id) {this.transport_id = transport_id;}
    @Override
    public String toString() {
        return "VehicleDTO{" +
                "import_company_id='" + import_company_id + '\'' +
                ", import_date='" + import_date + '\'' +
                ", vehicle_id='" + vehicle_id + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", current_status='" + current_status + '\'' +
                ", export_company_id='" + export_company_id + '\'' +
                ", export_date='" + export_date + '\'' +
                ", sale_date='" + sale_date + '\'' +
                ", import_price=" + import_price +
                ", export_price=" + export_price +
                ", reservation_id='" + reservation_id + '\'' +
                ", transport_id='" + transport_id + '\'' +
                '}';
    }
}
