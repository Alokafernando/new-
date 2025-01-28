package lk.ijse.pos.leyard.dao.custom.impl;

import lk.ijse.pos.leyard.dao.SQLUtil;
import lk.ijse.pos.leyard.dao.custom.ReservationDAO;
import lk.ijse.pos.leyard.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAOImpl implements ReservationDAO {


    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from reservation");
        ArrayList<Reservation> reservations = new ArrayList<>();
        while (rst.next()) {
            reservations.add(new Reservation(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return reservations;
    }

    @Override
    public boolean save(Reservation entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into reservation values (?, ?, ?)",
                entity.getCust_id(), entity.getReservation_id(), entity.getReservation_date());
    }

    @Override
    public void update(Reservation entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update reservation set cust_id=?, reservation_date =? where reservation_id = ?",
                entity.getCust_id(), entity.getReservation_date(), entity.getReservation_id());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from reservation where reservation_id=? ", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select reservation_id from reservation order by reservation_id desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            if (lastID != null && lastID.startsWith("R") && lastID.length() > 1) {
                String subString = lastID.substring(1);
                try {
                    int i = Integer.parseInt(subString);
                    int newIndex = i + 1;
                    return String.format("R%03d", newIndex);
                } catch (NumberFormatException e) {
                    throw new SQLException("Invalid reservation ID format: " + lastID);
                }
            }
        }
        return "R001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select reservation_id from reservation");
        ArrayList<String> reservationIds = new ArrayList<>();

        while (rst.next()) {
            reservationIds.add(rst.getString(1));
        }
        return reservationIds;
    }
}
