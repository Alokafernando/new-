package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.TaxDTO;

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
