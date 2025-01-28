package lk.ijse.pos.leyard.entity;

import java.io.Serializable;

public class Customer implements Serializable {

    private String cust_ID;
    private String name;
    private String address;
    private String contact;
    private String email;

    public Customer() {

    }

    public Customer(String cust_ID, String name, String address, String contact, String email) {
        this.cust_ID = cust_ID;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

    public String getCust_ID() {
        return cust_ID;
    }

    public void setCust_ID(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
