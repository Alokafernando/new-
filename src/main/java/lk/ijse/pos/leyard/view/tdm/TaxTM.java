package lk.ijse.pos.leyard.view.tdm;

import lk.ijse.pos.leyard.model.TaxDTO;

public class TaxTM extends TaxDTO implements Comparable<TaxTM> {
    private String vehicleId;
    private String taxId;
    private double importTax;
    private double exportTax;
    private double groundTax;

    public TaxTM() {}

    public TaxTM(String vehicleId, String taxId, double importTax, double exportTax, double groundTax) {
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
        return "TaxTM{" +
                "vehicleId='" + vehicleId + '\'' +
                ", taxId='" + taxId + '\'' +
                ", importTax=" + importTax +
                ", exportTax=" + exportTax +
                ", groundTax=" + groundTax +
                '}';
    }

    @Override
    public int compareTo(TaxTM o) {
        return taxId.compareTo(getTaxId());
    }
}
