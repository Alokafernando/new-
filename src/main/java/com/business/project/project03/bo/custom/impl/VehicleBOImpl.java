package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.VehicleBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.VehicleDAO;
import com.business.project.project03.entity.Vehicle;
import com.business.project.project03.model.TaxDTO;
import com.business.project.project03.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleBOImpl implements VehicleBO {

    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public ArrayList<VehicleDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        ArrayList<Vehicle> vehicles = vehicleDAO.getAll();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(new VehicleDTO(vehicle.getImport_company_id(), vehicle.getImport_date(), vehicle.getVehicle_id(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getCurrent_status(), vehicle.getExport_company_id(), vehicle.getExport_date(), vehicle.getSale_date(), vehicle.getImport_price(), vehicle.getExport_price(), vehicle.getReservation_id(), vehicle.getTransport_id()));
        }
        return vehicleDTOS;

    }

    @Override
    public void save(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
    vehicleDAO.save(new Vehicle( vehicle.getImport_company_id(), vehicle.getImport_date(), vehicle.getVehicle_id(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getCurrent_status(), vehicle.getExport_company_id(), vehicle.getExport_date(), vehicle.getSale_date(), vehicle.getImport_price(), vehicle.getExport_price(), vehicle.getReservation_id(), vehicle.getTransport_id()));
    }

    @Override
    public void update(VehicleDTO vehicle) throws SQLException, ClassNotFoundException {
        vehicleDAO.update(new Vehicle(vehicle.getImport_company_id(), vehicle.getImport_date(), vehicle.getVehicle_id(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getCurrent_status(), vehicle.getExport_company_id(), vehicle.getExport_date(), vehicle.getSale_date(), vehicle.getImport_price(), vehicle.getExport_price(), vehicle.getReservation_id(), vehicle.getTransport_id()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        vehicleDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return vehicleDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
    vehicleDAO.generateReport();
    }

    @Override
    public ArrayList<String> getAllVehicleIDs() throws SQLException, ClassNotFoundException {
        return vehicleDAO.getAllVehicleIDs();
    }

    @Override
    public VehicleDTO findById(String selectedVehicleID) throws SQLException, ClassNotFoundException {
        return vehicleDAO.findById(selectedVehicleID);
    }
}
