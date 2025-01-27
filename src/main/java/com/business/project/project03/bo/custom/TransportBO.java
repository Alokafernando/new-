package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.SupplierDTO;
import com.business.project.project03.model.TransportDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransportBO extends SuperBO {
    ArrayList<TransportDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(TransportDTO transport) throws SQLException, ClassNotFoundException;
    void update(TransportDTO transport) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllTransportIDs() throws SQLException, ClassNotFoundException;

}
