package lk.ijse.pos.leyard.bo.custom;



import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.CustomerDTO;
import lk.ijse.pos.leyard.view.tdm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    void update(CustomerDTO customer) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
    CustomerTM findbyId(String selectedCustId) throws SQLException, ClassNotFoundException;
}
