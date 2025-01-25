package com.business.project.project03.view.tdm;

public class ReservationTM implements Comparable<ReservationTM> {

    private String cust_id;
    private String reservation_id;
    private String reservation_date;

    public ReservationTM() {}

    public ReservationTM(String cust_id, String reservation_id, String reservation_date) {
        this.cust_id = cust_id;
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
    }

    public String getCust_id() {
        return cust_id;
    }
    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }
    public String getReservation_id() {
        return reservation_id;
    }
    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }
    public String getReservation_date() {
        return reservation_date;
    }
    public void setReservation_date(String reservation_date) {
        this.reservation_date = reservation_date;
    }

    @Override
    public String toString() {
        return "ReservationTM{" +
                "cust_id='" + cust_id + '\'' +
                ", reservation_id='" + reservation_id + '\'' +
                ", reservation_date='" + reservation_date + '\'' +
                '}';
    }

    @Override
    public int compareTo(ReservationTM o) {
        return 0;
    }

}
