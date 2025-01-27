package com.business.project.project03.entity;

public class Tax {
    private String vehicleId;
    private String taxId;
    private double importTax;
    private double exportTax;
    private double groundTax;

    public Tax() {}

    public Tax(String vehicleId, String taxId, double importTax, double exportTax, double groundTax) {
        this.vehicleId = vehicleId;
        this.taxId = taxId;
        this.importTax = importTax;
        this.exportTax = exportTax;
        this.groundTax = groundTax;
    }

    public String getVehicleId() {return vehicleId;}

    public void setVehicleId(String vehicleId) {this.vehicleId = vehicleId;}

    public String getTaxId() {return taxId;}

    public void setTaxId(String taxId) {this.taxId = taxId;}

    public double getImportTax() {return importTax;}

    public void setImportTax(double importTax) {this.importTax = importTax;}

    public double getExportTax() {return exportTax;}

    public void setExportTax(double exportTax) {this.exportTax = exportTax;}

    public double getGroundTax() {return groundTax;}

    public void setGroundTax(double groundTax) {this.groundTax = groundTax;}

    @Override
    public String toString() {
        return "Tax{" +
                "vehicleId='" + vehicleId + '\'' +
                ", taxId='" + taxId + '\'' +
                ", importTax=" + importTax +
                ", exportTax=" + exportTax +
                ", groundTax=" + groundTax +
                '}';
    }
}
