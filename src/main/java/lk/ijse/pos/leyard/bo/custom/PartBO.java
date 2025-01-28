package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.PartDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartBO extends SuperBO {
    ArrayList<PartDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(PartDTO part) throws SQLException, ClassNotFoundException;
    void update(PartDTO part) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllPartIds() throws SQLException, ClassNotFoundException;
    PartDTO findById(String selectedId) throws SQLException, ClassNotFoundException;

}
