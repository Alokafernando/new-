package lk.ijse.pos.leyard.entity;

import java.io.Serializable;

public class Supplier implements Serializable {
    private String supplier_id;
    private String name;
    private String contact;
    private String email;

    public Supplier() {}

    public Supplier(String supplier_id, String name, String contact, String email) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public String getSupplier_id() {return supplier_id;}

    public void setSupplier_id(String supplier_id) {this.supplier_id = supplier_id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getContact() {return contact;}

    public void setContact(String contact) {this.contact = contact;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}


}
