package lk.ijse.pos.leyard.entity;

import java.io.Serializable;

public class Staff implements Serializable {
    private String staff_id;
    private String staff_name;
    private String address;
    private double salary;
    private String role;

    public Staff() {}

    public Staff(String staff_id, String staff_name, String address, double salary, String role) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.address = address;
        this.salary = salary;
        this.role = role;
    }

    public String getStaff_id() {return staff_id;}

    public void setStaff_id(String staff_id) {this.staff_id = staff_id;}

    public String getStaff_name() {return staff_name;}

    public void setStaff_name(String staff_name) {this.staff_name = staff_name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}


}
