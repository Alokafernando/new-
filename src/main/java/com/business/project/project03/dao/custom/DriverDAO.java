package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Driver;
import com.business.project.project03.view.tdm.DriverTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO extends CrudDAO<Driver> {
    ArrayList<String> getAllDrivers() throws SQLException, ClassNotFoundException;
    DriverTM findByID(String id) throws SQLException, ClassNotFoundException;
    //find by id
    //get All ids
}
