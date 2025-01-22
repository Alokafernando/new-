package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.CustomerDAO;
import com.business.project.project03.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
           customers.add(new Customer(
                   rst.getString("cust_ID"), rst.getString("name"), rst.getString("address"), rst.getString("contact"), rst.getString("email")
           ));
        }
        return customers;

    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("insert into customer values(?, ?, ?, ?, ?)",
                dto.getCust_ID(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail());
        return false;
    }

    @Override
    public void update(Customer dto) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update customer set name =?, address =?, contact =?, email =? where cust_ID = ?",
                dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail(), dto.getCust_ID());

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from customer where cust_ID = ?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select cust_ID from customer order by cust_ID desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("C%03d", newIndex);
        }
        return "C001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {

    }


}
