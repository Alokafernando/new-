package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Vehicle;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CheckDAO extends CrudDAO<Vehicle> {
    ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException;
}
