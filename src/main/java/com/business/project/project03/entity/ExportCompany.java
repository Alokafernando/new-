package com.business.project.project03.entity;

import java.io.Serializable;

public class ExportCompany implements Serializable {

    private String company_ID;
    private String company_Name;
    private String country;
    private String contact;
    private String email;

    public ExportCompany() {}

    public ExportCompany(String company_ID, String company_Name, String country, String contact, String email) {
        this.company_ID = company_ID;
        this.company_Name = company_Name;
        this.country = country;
        this.contact = contact;
        this.email = email;
    }

    public String getCompany_ID() {return company_ID;}

    public void setCompany_ID(String company_ID) {this.company_ID = company_ID;}

    public String getCompany_Name() {return company_Name;}

    public void setCompany_Name(String company_Name) {this.company_Name = company_Name;}

    public String getCountry() {return country;}

    public void setCountry(String country) {this.country = country;}

    public String getContact() {return contact;}

    public void setContact(String contact) {this.contact = contact;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "ExportCompanyTM" +
                "id='" + company_ID + '\'' +
                ", name='" + company_Name + '\'' +
                ", country='" + country + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
