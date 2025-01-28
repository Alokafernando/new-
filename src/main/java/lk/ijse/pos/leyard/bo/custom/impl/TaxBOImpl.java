package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.TaxBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.TaxDAO;
import lk.ijse.pos.leyard.entity.Tax;
import lk.ijse.pos.leyard.model.TaxDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TaxBOImpl implements TaxBO {

    TaxDAO taxDAO = (TaxDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TAX);

    @Override
    public ArrayList<TaxDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<TaxDTO> taxDTOS = new ArrayList<>();
        ArrayList<Tax> taxes = taxDAO.getAll();
        for (Tax tax : taxes) {
            taxDTOS.add(new TaxDTO(tax.getVehicleId(), tax.getTaxId(), tax.getImportTax(), tax.getExportTax(), tax.getGroundTax()));
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
