package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.PartDTO;
import lk.ijse.pos.leyard.model.SupplierDTO;
import lk.ijse.pos.leyard.model.SupplierDetailDTO;

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
