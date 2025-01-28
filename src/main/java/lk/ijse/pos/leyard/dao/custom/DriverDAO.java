package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Driver;
import lk.ijse.pos.leyard.view.tdm.DriverTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DriverDAO extends CrudDAO<Driver> {
    ArrayList<String> getAllDrivers() throws SQLException, ClassNotFoundException;
    DriverTM findByID(String id) throws SQLException, ClassNotFoundException;
    //find by id
    //get All ids
}
