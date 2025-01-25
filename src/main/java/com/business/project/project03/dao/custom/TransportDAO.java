package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Transport;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransportDAO extends CrudDAO<Transport> {
    ArrayList<String> getAllTransportIds() throws SQLException, ClassNotFoundException;
}
