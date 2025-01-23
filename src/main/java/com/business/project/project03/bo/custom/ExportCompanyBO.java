package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.DriverDTO;
import com.business.project.project03.model.ExportCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExportCompanyBO extends SuperBO {
    ArrayList<ExportCompanyDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(ExportCompanyDTO exportCompany) throws SQLException, ClassNotFoundException;
    void update(ExportCompanyDTO exportCompany) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
}
