package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Supplier;
import com.business.project.project03.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {
    SupplierDTO findById(String selectedSupplierID) throws SQLException, ClassNotFoundException;
    ArrayList<String> getSupplierIDs() throws SQLException, ClassNotFoundException;
}
