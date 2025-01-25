package com.business.project.project03.view.tdm;

public class SupplierTM implements Comparable<SupplierTM>{

    private String supplier_id;
    private String name;
    private String contact;
    private String email;

    public SupplierTM() {}

    public SupplierTM(String supplier_id, String name, String contact, String email) {
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

    @Override
    public String toString() {
        return "SupplierTM{" +
                "supplier_id='" + supplier_id + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(SupplierTM o) {
        return supplier_id.compareTo(o.supplier_id);
    }
}
