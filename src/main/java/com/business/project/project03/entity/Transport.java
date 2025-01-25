package com.business.project.project03.entity;

import java.io.Serializable;

public class Transport implements Serializable {

    private String transport_id;
    private String transport_type;
    private String start_date;
    private String end_date;
    private String driver_id;

    public Transport() {}

    public Transport(String transport_id, String transport_type, String start_date, String end_date, String driver_id) {
        this.transport_id = transport_id;
        this.transport_type = transport_type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.driver_id = driver_id;
    }

    public String getTransport_id() {return transport_id;}

    public void setTransport_id(String transport_id) {this.transport_id = transport_id;}

    public String getTransport_type() {return transport_type;}

    public void setTransport_type(String transport_type) {this.transport_type = transport_type;}

    public String getStart_date() {return start_date;}

    public void setStart_date(String start_date) {this.start_date = start_date;}

    public String getEnd_date() {return end_date;}

    public void setEnd_date(String end_date) {this.end_date = end_date;}

    public String getDriver_id() {return driver_id;}

    public void setDriver_id(String driver_id) {this.driver_id = driver_id;}

    @Override
    public String toString() {
        return "TransportDTO{" +
                "transport_id='" + transport_id + '\'' +
                ", transport_type='" + transport_type + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", driver_id='" + driver_id + '\'' +
                '}';
    }

}
