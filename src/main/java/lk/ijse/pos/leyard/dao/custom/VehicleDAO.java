package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Vehicle;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    ArrayList<String> getAllVehicleIDs() throws SQLException, ClassNotFoundException;
    VehicleDTO findById(String selectedVehicleID) throws SQLException, ClassNotFoundException;
}
