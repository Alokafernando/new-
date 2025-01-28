package lk.ijse.pos.leyard.bo.custom.impl;


import lk.ijse.pos.leyard.bo.custom.CustomerBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.CustomerDAO;
import lk.ijse.pos.leyard.entity.Customer;
import lk.ijse.pos.leyard.model.CustomerDTO;
import lk.ijse.pos.leyard.view.tdm.CustomerTM;

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
    public void save(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        customerDAO.save(new Customer(customer.getCust_ID(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));

    }

    @Override
    public void update(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        customerDAO.update(new Customer(customer.getCust_ID(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));

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

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCustomerIds();
    }

    @Override
    public CustomerTM findbyId(String selectedCustId) throws SQLException, ClassNotFoundException {
        return customerDAO.findbyId(selectedCustId);
    }
}
