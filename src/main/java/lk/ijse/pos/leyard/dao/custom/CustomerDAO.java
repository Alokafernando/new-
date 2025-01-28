package lk.ijse.pos.leyard.dao.custom;


import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Customer;
import lk.ijse.pos.leyard.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer> {
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    CustomerTM findbyId(String selectedCustId) throws SQLException, ClassNotFoundException;

}
