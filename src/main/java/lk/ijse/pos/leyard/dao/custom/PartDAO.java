package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Part;
import lk.ijse.pos.leyard.model.PartDTO;
import lk.ijse.pos.leyard.model.PartDetailDTO;
import lk.ijse.pos.leyard.model.SupplierDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PartDAO extends CrudDAO<Part> {
    ArrayList<String> getAllPartIds() throws SQLException, ClassNotFoundException;
    PartDTO findById(String selectedId) throws SQLException, ClassNotFoundException;
    boolean redQty(SupplierDetailDTO supplierDetailDTOS) throws SQLException, ClassNotFoundException;
    boolean decrementQty(PartDetailDTO partDetailDTO) throws SQLException, ClassNotFoundException;
}
