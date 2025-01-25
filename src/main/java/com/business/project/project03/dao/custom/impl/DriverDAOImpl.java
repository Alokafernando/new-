package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.DriverDAO;
import com.business.project.project03.entity.Driver;
import com.business.project.project03.model.DriverDTO;
import com.business.project.project03.view.tdm.DriverTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public ArrayList<Driver> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from driver");
        ArrayList<Driver> driverDTOS = new ArrayList<>();
        while (rst.next()) {
            driverDTOS.add(new Driver(rst.getString("driver_id"), rst.getString("name"), rst.getString("contact")));
        }
        return driverDTOS;
    }

    @Override
    public boolean save(Driver entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into driver values(?,?,?)",
                entity.getDriver_id(),
                entity.getName(),
                entity.getContact());
    }

    @Override
    public void update(Driver entity) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("update driver set name =?, contact=? where driver_id =?",
                entity.getName(),
                entity.getContact(),
                entity.getDriver_id());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("delete from driver where driver_id =? ", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select driver_id from driver order by driver_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("D%03d", newIndex);
        }
        return "D001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllDrivers() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select driver_id from driver");
        ArrayList<String> driverIds = new ArrayList<>();

        while (rst.next()) {
            driverIds.add(rst.getString(1));
        }
        return driverIds;
    }

    @Override
    public DriverTM findByID(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from driver where driver_id=?", id);

        if (rst.next()) {
            return new DriverTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)

            );
        }
        return null;
    }
}
