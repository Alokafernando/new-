package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.DriverBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.DriverDAO;
import com.business.project.project03.entity.Driver;
import com.business.project.project03.model.DriverDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class DriverBOImpl implements DriverBO {

   DriverDAO  driverDAO = (DriverDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);

    @Override
    public ArrayList<DriverDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<DriverDTO> driverDTOS = new ArrayList<>();
        ArrayList<Driver> drivers = driverDAO.getAll();
        for (Driver driver : drivers) {
         driverDTOS.add(new DriverDTO(driver.getDriver_id(), driver.getName(), driver.getContact()));
        }
        return driverDTOS;
    }

    @Override
    public void save(DriverDTO driver) throws SQLException, ClassNotFoundException {
       driverDAO.save(new Driver(driver.getDriver_id(), driver.getName(), driver.getContact()));

    }

    @Override
    public void update(DriverDTO driver) throws SQLException, ClassNotFoundException {
      driverDAO.update(new Driver(driver.getDriver_id(), driver.getName(), driver.getContact()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
      driverDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return driverDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
       driverDAO.generateReport();
    }
}
