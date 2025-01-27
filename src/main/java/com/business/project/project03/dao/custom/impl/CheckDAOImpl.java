package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.CheckDAO;
import com.business.project.project03.entity.Vehicle;
import com.business.project.project03.model.VehicleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckDAOImpl implements CheckDAO {
    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Vehicle entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public void update(Vehicle entity) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {

    }

    @Override
    public ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select * from vehicle");
        ArrayList<VehicleDTO> vehicles = new ArrayList<>();

        while (resultSet.next()) {
            VehicleDTO vehicle = new VehicleDTO(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), Integer.parseInt(resultSet.getString(5)), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getDouble(11), resultSet.getDouble(12), resultSet.getString(13), resultSet.getString(14)
            );
            vehicles.add(vehicle);
        }
        return vehicles;
    }
}
