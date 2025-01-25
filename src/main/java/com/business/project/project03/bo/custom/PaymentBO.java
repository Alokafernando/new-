package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException;
    void save(PaymentDTO payment) throws SQLException, ClassNotFoundException;
    void update(PaymentDTO payment) throws SQLException, ClassNotFoundException;
    void delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewID()  throws SQLException, ClassNotFoundException;
    void generateReport() throws SQLException, ClassNotFoundException;
}
