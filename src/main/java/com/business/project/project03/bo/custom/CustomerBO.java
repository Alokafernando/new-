package com.business.project.project03.bo.custom;



import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.CustomerDTO;
import com.business.project.project03.view.tdm.CustomerTM;
import net.sf.jasperreports.engine.JasperPrint;

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
