package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.ImportCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ImportCompanyBO extends SuperBO {
    ArrayList<ImportCompanyDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(ImportCompanyDTO importCompany) throws SQLException, ClassNotFoundException;
    void update(ImportCompanyDTO importCompany) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllImportCompanyIDs() throws SQLException, ClassNotFoundException;
}
