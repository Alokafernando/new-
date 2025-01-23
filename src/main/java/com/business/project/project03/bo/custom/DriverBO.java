package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.DriverDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverBO extends SuperBO {
    ArrayList<DriverDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(DriverDTO driver) throws SQLException, ClassNotFoundException;
    void update(DriverDTO driver) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
}
