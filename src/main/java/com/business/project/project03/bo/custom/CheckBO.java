package com.business.project.project03.bo.custom;

import com.business.project.project03.bo.SuperBO;
import com.business.project.project03.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CheckBO extends SuperBO {
    ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException;
}
