package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.DriverBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.DriverDAO;
import lk.ijse.pos.leyard.entity.Driver;
import lk.ijse.pos.leyard.model.DriverDTO;
import lk.ijse.pos.leyard.view.tdm.DriverTM;

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

    @Override
    public ArrayList<String> getAllDriverIds() throws SQLException, ClassNotFoundException {
        return driverDAO.getAllDrivers();
    }

    @Override
    public DriverTM findById(String selectedDriverId) throws SQLException, ClassNotFoundException {
        return driverDAO.findByID(selectedDriverId);
    }
}
