package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Vehicle;
import com.business.project.project03.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VehicleDAO extends CrudDAO<Vehicle> {
    ArrayList<String> getAllVehicleIDs() throws SQLException, ClassNotFoundException;
    VehicleDTO findById(String selectedVehicleID) throws SQLException, ClassNotFoundException;
}
