package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    void update(SupplierDTO supplier) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getSupplierIDs() throws SQLException, ClassNotFoundException;
    SupplierDTO findById(String selectedSupplierID) throws SQLException, ClassNotFoundException;
}
