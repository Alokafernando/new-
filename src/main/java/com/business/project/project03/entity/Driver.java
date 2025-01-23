package com.business.project.project03.entity;

import java.io.Serializable;

public class Driver implements Serializable {

    private String driver_id;
    private String name;
    private String contact;

    public Driver() {}

    public Driver(String driver_id, String name, String contact) {
        this.driver_id = driver_id;
        this.name = name;
        this.contact = contact;}

    public String getDriver_id() {return driver_id;}

    public void setDriver_id(String driver_id) {this.driver_id = driver_id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getContact() {return contact;}

    public void setContact(String contact) {this.contact = contact;}

    @Override
    public String toString() {
        return "id='" + driver_id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\''+
                '}';
    }
}
