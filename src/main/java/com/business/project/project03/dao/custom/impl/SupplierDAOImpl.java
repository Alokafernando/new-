package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.SupplierDAO;
import com.business.project.project03.entity.Supplier;
import com.business.project.project03.model.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from supplier");
        ArrayList<Supplier> suppliers = new ArrayList<>();
        while (rst.next()) {
            suppliers.add(new Supplier(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4)));
        }
        return suppliers;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into supplier values(?,?,?,?)",
                entity.getSupplier_id(), entity.getName(), entity.getContact(), entity.getEmail());
    }

    @Override
    public void update(Supplier entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update supplier set name=?, contact=?, email=? where supplier_id=?",
                entity.getName(), entity.getContact(), entity.getEmail(), entity.getSupplier_id());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("delete from supplier where supplier_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);

            String subString = lastID.replaceAll("[^0-9]", "");

            if (!subString.isEmpty()) {
                int i = Integer.parseInt(subString);
                int newIndex = i + 1;
                return String.format("SU%03d", newIndex);
            }
        }
        return "SU001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public SupplierDTO findById(String selectedSupplierID) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from supplier where supplier_id=?", selectedSupplierID);

        if (rst.next()) {
            return new SupplierDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }

    @Override
    public ArrayList<String> getSupplierIDs() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select supplier_id from supplier");
        ArrayList<String> supplierIDs = new ArrayList<>();
        while (resultSet.next()) {
            supplierIDs.add(resultSet.getString(1));
        }
        return supplierIDs;
    }
}
