package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.ReservationDTO;
import com.business.project.project03.model.StaffDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(ReservationDTO reservation) throws SQLException, ClassNotFoundException;
    void update(ReservationDTO reservation) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException;
}
