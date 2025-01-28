package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.PartDetailBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.PartDAO;
import com.business.project.project03.dao.custom.PartDetailDAO;
import com.business.project.project03.dao.custom.VehicleDAO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.entity.Part;
import com.business.project.project03.entity.PartDetail;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.PartDetailDTO;
import com.business.project.project03.model.VehicleDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDetailBOImpl implements PartDetailBO {

    PartDAO partDAO = (PartDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PART);
    PartDetailDAO partDetailDAO = (PartDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PART_DETAIL);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);

    @Override
    public boolean placeOrder(String partId, String vevhicle, List<PartDetailDTO> partDetailDTOS) throws SQLException, ClassNotFoundException {

       return false;
    }

    @Override
    public ArrayList<PartDTO> getAllPartsIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PartDTO searchPart(String partId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicleIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public VehicleDTO searchVehicle(String vehicleId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generatePartAddingID() throws SQLException, ClassNotFoundException {
        return "";
    }
}
