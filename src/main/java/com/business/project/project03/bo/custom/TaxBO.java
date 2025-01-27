package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.TaxDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TaxBO extends SuperBO {
    ArrayList<TaxDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(TaxDTO tax) throws SQLException, ClassNotFoundException;
    void update(TaxDTO tax) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
}
