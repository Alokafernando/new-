package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Part;
import com.business.project.project03.model.PartDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartDAO extends CrudDAO<Part> {
    ArrayList<String> getAllPartIds() throws SQLException, ClassNotFoundException;
    PartDTO findById(String selectedId) throws SQLException, ClassNotFoundException;
    //boolean redQty(SupplierDetailDTO supplierDetailDTOS) throws SQLException, ClassNotFoundException;
    //boolean decrementQty(PartDetailDTO partDetailDTO) throws SQLException, ClassNotFoundException
}
