package lk.ijse.pos.leyard.dao.custom.impl;

import lk.ijse.pos.leyard.dao.SQLUtil;
import lk.ijse.pos.leyard.dao.custom.SupplyDetailDAO;
import lk.ijse.pos.leyard.entity.SupplyDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplyDateilDAOImpl implements SupplyDetailDAO {
    @Override
    public ArrayList<SupplyDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SupplyDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(
                "insert into supplier_details values (?,?,?,?, ?)",
                entity.getSupplier_id(), entity.getPart_id(), entity.getSupply_date(), entity.getQuantity(), entity.getTotal()
        );
    }

    @Override
    public void update(SupplyDetails entity) throws SQLException, ClassNotFoundException {

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
}
