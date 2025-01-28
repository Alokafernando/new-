package lk.ijse.pos.leyard.dao.custom.impl;

import lk.ijse.pos.leyard.dao.SQLUtil;
import lk.ijse.pos.leyard.dao.custom.StaffDAO;
import lk.ijse.pos.leyard.entity.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDAOImpl implements StaffDAO {
    @Override
    public ArrayList<Staff> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from staff");
        ArrayList<Staff> staff = new ArrayList<>();
        while (rst.next()) {
            staff.add(new Staff(rst.getString("staff_id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary"), rst.getString("role")));
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
