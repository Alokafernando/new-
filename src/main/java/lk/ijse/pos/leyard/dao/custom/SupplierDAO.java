package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Supplier;
import lk.ijse.pos.leyard.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<Supplier> {
    SupplierDTO findById(String selectedSupplierID) throws SQLException, ClassNotFoundException;
    ArrayList<String> getSupplierIDs() throws SQLException, ClassNotFoundException;
}
