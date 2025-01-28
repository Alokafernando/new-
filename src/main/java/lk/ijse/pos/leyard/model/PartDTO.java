package lk.ijse.pos.leyard.model;

import java.io.Serializable;

public class PartDTO implements Serializable {

    private String part_id;
    private String name;
    private double unit_price;
    private int quantity;

    public PartDTO() {}

    public PartDTO(String part_id, String name, double unit_price, int quantity) {
        this.part_id = part_id;
        this.name = name;
        this.unit_price = unit_price;
        this.quantity = quantity;
    }

    public String getPart_id() {return part_id;}

    public void setPart_id(String part_id) {this.part_id = part_id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public double getPrice() {return unit_price;}

    public void setPrice(double price) {this.unit_price = price;}

    public int getQuantity() {return quantity;}

    public void setQuantity(int quantity) {this.quantity = quantity;}

    @Override
    public String toString() {
        return "PartDTO{" +
                "part_id='" + part_id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + unit_price +
                ", quantity=" + quantity +
                '}';
    }

}
