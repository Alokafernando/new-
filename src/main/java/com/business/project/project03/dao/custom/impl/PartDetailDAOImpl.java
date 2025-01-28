package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.PartDetailDAO;
import com.business.project.project03.entity.PartDetail;

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
