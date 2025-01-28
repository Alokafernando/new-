package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.SupplierDTO;
import com.business.project.project03.model.SupplierDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplyDetailBO extends SuperBO {
    boolean placeOrder(List<SupplierDetailDTO> orderDetails) throws SQLException, ClassNotFoundException;
    PartDTO findPart(String partId) throws SQLException, ClassNotFoundException;
    SupplierDTO findSupplier(String supplierId) throws SQLException, ClassNotFoundException;
    ArrayList<PartDTO> getAllPartIds() throws SQLException, ClassNotFoundException;
    ArrayList<SupplierDTO> getAllSupplierIds() throws SQLException, ClassNotFoundException;
}
