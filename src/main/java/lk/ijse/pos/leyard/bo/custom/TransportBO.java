package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.TransportDTO;

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
