package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.PartDAO;
import com.business.project.project03.entity.Part;
import com.business.project.project03.model.PartDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartDAOImpl implements PartDAO {

    @Override
    public ArrayList<Part> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from part");
        ArrayList<Part> parts = new ArrayList<>();
        while (rst.next()) {
            parts.add(new Part(rst.getString("part_id"), rst.getString("name"),  rst.getDouble("unit_price"), rst.getInt("quantity")));
        }
        return parts;
    }

    @Override
    public boolean save(Part dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into part  VALUES (?, ?, ?, ?)",
                dto.getPart_id(), dto.getName(), dto.getPrice(), dto.getQuantity());
    }

    @Override
    public void update(Part dto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update part set name = ?, unit_price = ?, quantity = ? where part_id = ?",
                dto.getName(), dto.getPrice(), dto.getQuantity(), dto.getPart_id()
        );
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from part where part_id = ?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT part_id FROM part ORDER BY part_id DESC LIMIT 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.replaceAll("[^0-9]", "");
            if (!subString.isEmpty()) {
                int i = Integer.parseInt(subString);
                int newIndex = i + 1;
                return String.format("PA%03d", newIndex);
            }
        }
        return "PA001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllPartIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select part_id from part");
        ArrayList<String> partIds = new ArrayList<>();

        while (rst.next()) {
            partIds.add(rst.getString(1));
        }
        return partIds;
    }

    @Override
    public PartDTO findById(String selectedId) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from part where part_id=?", selectedId);

        if (rst.next()) {
            return new PartDTO(
                    rst.getString(1), rst.getString(2), rst.getDouble(3), rst.getInt(4));
        }

        return null;
    }
}
