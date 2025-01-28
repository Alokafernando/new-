package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleBO extends SuperBO {
    ArrayList<VehicleDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(VehicleDTO vehicle) throws SQLException, ClassNotFoundException;
    void update(VehicleDTO vehicle) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
     ArrayList<String> getAllVehicleIDs() throws SQLException, ClassNotFoundException;
     VehicleDTO findById(String selectedVehicleID) throws SQLException, ClassNotFoundException;
}
