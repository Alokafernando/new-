package lk.ijse.pos.leyard.dao.custom.impl;

import lk.ijse.pos.leyard.dao.SQLUtil;
import lk.ijse.pos.leyard.dao.custom.TransportDAO;
import lk.ijse.pos.leyard.entity.Transport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransportDAOImpl implements TransportDAO {
    @Override
    public ArrayList<Transport> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from transport");
        ArrayList<Transport> transports = new ArrayList<>();
        while (rst.next()) {
            transports.add(new Transport(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
      return transports;
    }

    @Override
    public boolean save(Transport entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO transport (transport_id, transport_type, start_date, end_date, driver_id) VALUES (?, ?, ?, ?, ?)",
                entity.getTransport_id(), entity.getTransport_type(), entity.getStart_date(), entity.getEnd_date(), entity.getDriver_id());
    }

    @Override
    public void update(Transport entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("UPDATE transport SET transport_type = ?, start_date = ?, end_date = ?, driver_id = ? WHERE transport_id = ?",
                entity.getTransport_type(), entity.getStart_date(), entity.getEnd_date(), entity.getDriver_id(), entity.getTransport_id());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("delete from transport where transport_id=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select transport_id from transport order by transport_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.replaceAll("[^0-9]", "");
            if (subString.isEmpty()) {
                throw new SQLException("Invalid ID format in the database: " + lastID);
            }
            int i = Integer.parseInt(subString);
            int newIndex = i + 1;
            return String.format("T%03d", newIndex);
        }
        return "T001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
            //empty
    }

    @Override
    public ArrayList<String> getAllTransportIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select transport_id from transport");
        ArrayList<String> transportIds = new ArrayList<>();

        while (rst.next()) {
            transportIds.add(rst.getString(1));
        }
        return transportIds;
    }
}
