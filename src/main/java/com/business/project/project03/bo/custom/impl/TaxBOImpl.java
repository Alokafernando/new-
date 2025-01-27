package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.TaxBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.TaxDAO;
import com.business.project.project03.entity.Tax;
import com.business.project.project03.model.TaxDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaxBOImpl implements TaxBO {

    TaxDAO taxDAO = (TaxDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TAX);

    @Override
    public ArrayList<TaxDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<TaxDTO> taxDTOS = new ArrayList<>();
        ArrayList<Tax> taxes = taxDAO.getAll();
        for (Tax tax : taxes) {
            taxes.add(new Tax(tax.getVehicleId(), tax.getTaxId(), tax.getImportTax(), tax.getExportTax(), tax.getGroundTax()));
        }
        return taxDTOS;
    }

    @Override
    public void save(TaxDTO tax) throws SQLException, ClassNotFoundException {
        taxDAO.save(new Tax(tax.getVehicleId(), tax.getTaxId(), tax.getImportTax(), tax.getExportTax(), tax.getGroundTax()));
    }

    @Override
    public void update(TaxDTO tax) throws SQLException, ClassNotFoundException {
        taxDAO.update(new Tax(tax.getVehicleId(), tax.getTaxId(), tax.getImportTax(), tax.getExportTax(), tax.getGroundTax()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        taxDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return taxDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        taxDAO.generateReport();
    }
}
