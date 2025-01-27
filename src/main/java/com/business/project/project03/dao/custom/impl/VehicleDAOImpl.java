package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.VehicleDAO;
import com.business.project.project03.entity.Vehicle;
import com.business.project.project03.model.VehicleDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public ArrayList<Vehicle> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from vehicle");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        while (rst.next()) {
            vehicles.add(new Vehicle(
                    rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getDouble(11), rst.getDouble(12), rst.getString(13), rst.getString(14)));
        }
        return vehicles;
    }

    @Override
    public boolean save(Vehicle entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into vehicle values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getImport_company_id(), entity.getImport_date(), entity.getVehicle_id(), entity.getModel(), entity.getYear(), entity.getColor(), entity.getCurrent_status(), entity.getExport_company_id(), entity.getExport_date(), entity.getSale_date(), entity.getImport_price(), entity.getExport_price(), entity.getReservation_id(), entity.getTransport_id());
    }

    @Override
    public void update(Vehicle entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE vehicle SET import_company_id = ?, import_date = ?, model = ?, year = ?, color = ?, current_status = ?, export_company_id = ?, export_date = ?, sale_date = ?, import_price = ?, export_price = ?, reservation_id = ?, transport_id = ? WHERE vehicle_id = ?",
                entity.getImport_company_id(), entity.getImport_date(), entity.getModel(), entity.getYear(), entity.getColor(), entity.getCurrent_status(), entity.getExport_company_id(), entity.getExport_date(), entity.getSale_date(), entity.getImport_price(), entity.getExport_price(), entity.getReservation_id(), entity.getTransport_id(), entity.getVehicle_id());

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from vehicle where vehicle_id =?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select vehicle_id from vehicle order by vehicle_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("V%03d", newIndex);
        }
        return "V001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllVehicleIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select vehicle_id from vehicle");
        ArrayList<String> vehicleIds = new ArrayList<>();

        while (rst.next()) {
            vehicleIds.add(rst.getString(1));
        }
        return vehicleIds;
    }

    @Override
    public VehicleDTO findById(String selectedVehicleID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from vehicle where vehicle_id = ?", selectedVehicleID);
        if (rst.next()) {
            return new VehicleDTO(
                    rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getDouble(11), rst.getDouble(12), rst.getString(13), rst.getString(14));
        }
        return null;
    }

}
