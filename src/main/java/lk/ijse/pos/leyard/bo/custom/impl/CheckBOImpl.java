package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.CheckBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.CheckDAO;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CheckBOImpl implements CheckBO {

    CheckDAO checkDAO = (CheckDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CHECK);

    @Override
    public ArrayList<VehicleDTO> getData() throws SQLException, ClassNotFoundException {
        return checkDAO.getData();
    }
}
