package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.StaffDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StaffBO extends SuperBO {
    ArrayList<StaffDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(StaffDTO staff) throws SQLException, ClassNotFoundException;
    void update(StaffDTO staff) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
}
