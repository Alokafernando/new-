package com.business.project.project03.view.tdm;

public class CustomerTM implements Comparable<CustomerTM>{

    private String cust_ID;
    private String name;
    private String address;
    private String contact;
    private String email;

    public CustomerTM() {}

    public CustomerTM(String cust_ID, String name, String address, String contact, String email) {}

    public String getCust_ID() {return cust_ID;}
    public void setCust_ID(String cust_ID) {this.cust_ID = cust_ID;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getContact() {return contact;}
    public void setContact(String contact) {this.contact = contact;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "CustomerTM" +
                "id='" + cust_ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\''+
                ", email='" + email + '\'' +
                '}';

    }

    @Override
    public int compareTo(CustomerTM o) {
        return 0;
    }
}
