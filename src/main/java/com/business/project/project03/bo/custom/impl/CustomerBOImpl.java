package com.business.project.project03.bo.custom.impl;


import com.business.project.project03.bo.custom.CustomerBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.CustomerDAO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.entity.Customer;
import com.business.project.project03.model.CustomerDTO;
import javafx.scene.control.Alert;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

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
    public void save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAO.save(new Customer(dto.getCust_ID(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail()));

    }

    @Override
    public void update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(dto.getCust_ID(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail()));

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        customerDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        customerDAO.generateReport();
    }
}
