package com.business.project.project03.dao.custom;


import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Customer;
import com.business.project.project03.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    CustomerTM findbyId(String selectedCustId) throws SQLException, ClassNotFoundException;

}
