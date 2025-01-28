package lk.ijse.pos.leyard.entity;

public class PartDetail {
    private String part_id;
    private String vehicle_id;
    private int quantity;
    private double price;

    public PartDetail() {}

    public PartDetail(String part_id, String vehicle_id, int quantity, double price) {
        this.part_id = part_id;
        this.vehicle_id = vehicle_id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getPart_id() {return part_id;}

    public void setPart_id(String part_id) {this.part_id = part_id;}

    public String getVehicle_id() {return vehicle_id;}

    public void setVehicle_id(String vehicle_id) {this.vehicle_id = vehicle_id;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    public double getPrice() {return price;}

    public void setPrice(double price) {this.price = price;}


}
