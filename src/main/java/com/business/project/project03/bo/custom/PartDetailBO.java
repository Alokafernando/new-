package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.PartDetailDTO;
import com.business.project.project03.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PartDetailBO extends SuperBO {
    boolean placeOrder(String partId, String vevhicle, List<PartDetailDTO> partDetailDTOS) throws SQLException, ClassNotFoundException ;
    ArrayList<PartDTO> getAllPartsIds() throws SQLException, ClassNotFoundException;
    PartDTO searchPart(String partId) throws SQLException, ClassNotFoundException;
    ArrayList<VehicleDTO> getAllVehicleIds() throws SQLException, ClassNotFoundException;
    VehicleDTO searchVehicle(String vehicleId) throws SQLException, ClassNotFoundException;
    String generatePartAddingID()  throws SQLException, ClassNotFoundException;

}
