package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.SupplierBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.SupplierDAO;
import com.business.project.project03.entity.Supplier;
import com.business.project.project03.model.ReservationDTO;
import com.business.project.project03.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        for (Supplier supplier : suppliers) {
            supplierDTOS.add(new SupplierDTO(supplier.getSupplier_id(), supplier.getName(), supplier.getContact(), supplier.getEmail()));
        }
        return supplierDTOS;
    }

    @Override
    public void save(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        supplierDAO.save(new Supplier(supplier.getSupplier_id(), supplier.getName(), supplier.getContact(), supplier.getEmail()));
    }

    @Override
    public void update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        supplierDAO.update(new Supplier(supplier.getSupplier_id(), supplier.getName(), supplier.getContact(), supplier.getEmail()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        supplierDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        supplierDAO.generateReport();
    }

    @Override
    public ArrayList<String> getSupplierIDs() throws SQLException, ClassNotFoundException {
        return supplierDAO.getSupplierIDs();
    }

    @Override
    public SupplierDTO findById(String selectedSupplierID) throws SQLException, ClassNotFoundException {
        return supplierDAO.findById(selectedSupplierID);
    }
}
