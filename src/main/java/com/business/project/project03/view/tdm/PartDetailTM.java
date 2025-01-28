package com.business.project.project03.view.tdm;

import javafx.scene.control.Button;


public class PartDetailTM implements Comparable<PartDetailTM> {
    private String partId;
    private String partName;
    private int cartQuantity;
    private double unitPrice;
    private double totalPrice;
    private Button removeButton;

    public PartDetailTM() {}

    public PartDetailTM(String partId, String partName, int cartQuantity, double unitPrice, double totalPrice, Button removeButton) {
        this.partId = partId;
        this.partName = partName;
        this.cartQuantity = cartQuantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.removeButton = removeButton;
    }

    public String getPartId() {return partId;}

    public void setPartId(String partId) {this.partId = partId;}

    public String getPartName() {return partName;}

    public void setPartName(String partName) {this.partName = partName;}

    public int getCartQuantity() {return cartQuantity;}

    public void setCartQuantity(int cartQuantity) {this.cartQuantity = cartQuantity;}

    public double getUnitPrice() {return unitPrice;}

    public void setUnitPrice(double unitPrice) {this.unitPrice = unitPrice;}

    public double getTotalPrice() {return totalPrice;}

    public void setTotalPrice(double totalPrice) {this.totalPrice = totalPrice;}

    public Button getRemoveButton() {return removeButton;}

    public void setRemoveButton(Button removeButton) {this.removeButton = removeButton;}

    @Override
    public String toString() {
        return "PartDetailCartTM{" +
                "partId='" + partId + '\'' +
                ", partName='" + partName + '\'' +
                ", cartQuantity=" + cartQuantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", removeButton=" + (removeButton != null ? "Button Exists" : "No Button") +
                '}';
    }

    @Override
    public int compareTo(PartDetailTM o) {
        return partId.compareTo(getPartId());
    }
}
