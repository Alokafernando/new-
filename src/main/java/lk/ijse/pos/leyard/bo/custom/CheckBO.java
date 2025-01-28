package lk.ijse.pos.leyard.bo.custom;

import lk.ijse.pos.leyard.bo.SuperBO;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CheckBO extends SuperBO {
    ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException;
}
