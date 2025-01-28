package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.ExportCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExportCompanyBO extends SuperBO {
    ArrayList<ExportCompanyDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(ExportCompanyDTO exportCompany) throws SQLException, ClassNotFoundException;
    void update(ExportCompanyDTO exportCompany) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllExportCompanyIds() throws SQLException, ClassNotFoundException;
}
