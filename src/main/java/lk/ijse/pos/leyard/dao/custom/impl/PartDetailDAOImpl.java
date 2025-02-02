package lk.ijse.pos.leyard.dao.custom.impl;

import lk.ijse.pos.leyard.dao.SQLUtil;
import lk.ijse.pos.leyard.dao.custom.PartDetailDAO;
import lk.ijse.pos.leyard.entity.PartDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public class PartDetailDAOImpl implements PartDetailDAO {

    @Override
    public ArrayList<PartDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(PartDetail entity) throws SQLException, ClassNotFoundException {
        return  SQLUtil.execute("insert into part_details values(?,?,?,?);",
                entity.getPart_id(),
                entity.getVehicle_id(),
                entity.getQuantity(),
                entity.getPrice());

    }

    @Override
    public void update(PartDetail entity) throws SQLException, ClassNotFoundException {

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
