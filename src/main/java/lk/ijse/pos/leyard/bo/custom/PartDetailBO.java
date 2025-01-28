package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.PartDTO;
import lk.ijse.pos.leyard.model.PartDetailDTO;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PartDetailBO extends SuperBO {
    boolean placeOrder(List<PartDetailDTO> partDetailDTOS) throws SQLException, ClassNotFoundException ;
    PartDTO searchPart(String partId) throws SQLException, ClassNotFoundException;
    PartDTO findPart(String partId) throws SQLException, ClassNotFoundException;
    VehicleDTO findVehicle(String vehicleId) throws SQLException, ClassNotFoundException;
    ArrayList<PartDTO> getAllPartIds() throws SQLException, ClassNotFoundException;
    ArrayList<VehicleDTO> getAllVehicleIds() throws SQLException, ClassNotFoundException;
}
