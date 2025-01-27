package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.TaxDAO;
import com.business.project.project03.entity.Tax;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaxDAOImpl implements TaxDAO {
    @Override
    public ArrayList<Tax> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from tax");
        ArrayList<Tax> taxes = new ArrayList<>();

        while (rst.next()) {
            taxes.add(new Tax(rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getDouble(4), rst.getDouble(5)));
        }
        return taxes;
    }

    @Override
    public boolean save(Tax entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into tax values (?, ?, ?, ?, ?)",
                entity.getVehicleId(),
                entity.getTaxId(),
                entity.getImportTax(),
                entity.getExportTax(),
                entity.getGroundTax());
    }

    @Override
    public void update(Tax entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update tax set vehicle_id =?, import_tax =?, export_tax =?, ground_tax =? where tax_id =?",
                entity.getVehicleId(),
                entity.getImportTax(),
                entity.getExportTax(),
                entity.getGroundTax(),
                entity.getTaxId());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from tax where tax_id =?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT tax_id FROM tax ORDER BY tax_id DESC LIMIT 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            if (lastID != null && lastID.startsWith("Ta")) {
                try {
                    String numericPart = lastID.substring(2);
                    int currentIndex = Integer.parseInt(numericPart);
                    int newIndex = currentIndex + 1;
                    return String.format("Ta%03d", newIndex);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Invalid numeric part in tax ID: " + lastID, e);
                }
            } else {
                throw new RuntimeException("Invalid tax ID format: " + lastID);
            }
        }
        return "Ta001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        ///empty
    }
}
