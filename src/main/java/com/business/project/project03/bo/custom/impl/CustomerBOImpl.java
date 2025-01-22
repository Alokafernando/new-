package com.business.project.project03.bo.custom.impl;

import lk.ijse.gdse.project.bo.custom.CustomerBO;
import lk.ijse.gdse.project.dao.DAOFactory;
import lk.ijse.gdse.project.dao.custom.CustomerDAO;
import lk.ijse.gdse.project.entity.Customer;
import lk.ijse.gdse.project.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO  {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDTOs.add(new CustomerDTO(customer.getCust_ID(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));
        }
        return customerDTOs;
    }

    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
       customerDAO.save(new Customer(dto.getCust_ID(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail()));

        return true;
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(dto.getCust_ID(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail()));
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(id);
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }
}
