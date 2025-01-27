package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Vehicle;
import com.business.project.project03.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CheckDAO extends CrudDAO<Vehicle> {
    ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException;
}
