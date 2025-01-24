package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.ImportCompanyDTO;
import com.business.project.project03.model.PartDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartBO extends SuperBO {
    ArrayList<PartDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(PartDTO part) throws SQLException, ClassNotFoundException;
    void update(PartDTO part) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
}
