package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.Transport;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TransportDAO extends CrudDAO<Transport> {
    ArrayList<String> getAllTransportIds() throws SQLException, ClassNotFoundException;
}
