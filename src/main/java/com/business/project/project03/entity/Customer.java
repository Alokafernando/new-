package com.business.project.project03.entity;

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

    //cust_ID
    public String getCust_ID() {
        return cust_ID;
    }
    public void setCust_ID(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    //Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    //Contact
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    //Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + cust_ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
