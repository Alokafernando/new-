package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.StaffDAO;
import com.business.project.project03.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDAOImpl implements StaffDAO {
    @Override
    public ArrayList<Staff> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from staff");
        ArrayList<Staff> staff = new ArrayList<>();
        while (rst.next()) {
            staff.add(new Staff(rst.getString("staff_id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary"), rst.getString("salary")));
        }
        return staff;
    }

    @Override
    public boolean save(Staff entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into staff values(?, ?, ?, ?, ?)",
                entity.getStaff_id(), entity.getStaff_name(), entity.getAddress(), entity.getSalary(), entity.getRole());
    }

    @Override
    public void update(Staff entity) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("update staff set name =?, address =?, salary =?, role =? where staff_id =?",
                 entity.getStaff_name(), entity.getAddress(), entity.getSalary(), entity.getRole(), entity.getStaff_id());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from staff where staff_id =?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select staff_id from staff order by staff_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("S%03d", newIndex);
        }
        return "S001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }
}
