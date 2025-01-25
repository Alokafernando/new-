package com.business.project.project03.entity;

public class Reservation {

    private String cust_id;
    private String reservation_id;
    private String reservation_date;

    public Reservation() {}

    public Reservation(String cust_id, String reservation_id, String reservation_date) {
        this.cust_id = cust_id;
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
    }

    public String getCust_id() {return cust_id;}

    public void setCust_id(String cust_id) {this.cust_id = cust_id;}

    public String getReservation_id() {return reservation_id;}

    public void setReservation_id(String reservation_id) {this.reservation_id = reservation_id;}

    public String getReservation_date() {return reservation_date;}

    public void setReservation_date(String reservation_date) {this.reservation_date = reservation_date;}

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "cust_id='" + cust_id + '\'' +
                ", reservation_id='" + reservation_id + '\'' +
                ", reservation_date='" + reservation_date + '\'' +
                '}';
    }
}
