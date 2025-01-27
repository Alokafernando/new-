package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.CheckBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.CheckDAO;
import com.business.project.project03.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CheckBOImpl implements CheckBO {

    CheckDAO checkDAO = (CheckDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CHECK);

    @Override
    public ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException {
        return checkDAO.getData();
    }
}
