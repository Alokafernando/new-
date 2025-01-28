package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.ReservationBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.ReservationDAO;
import lk.ijse.pos.leyard.entity.Reservation;
import lk.ijse.pos.leyard.model.ReservationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> reservations = new ArrayList<>();
        for (Reservation reservation : reservationDAO.getAll()) {
            reservations.add(new ReservationDTO(reservation.getCust_id(), reservation.getReservation_id(), reservation.getReservation_date()));
        }
        return reservations;
    }



    @Override
    public void save(ReservationDTO reservation) throws SQLException, ClassNotFoundException {
        reservationDAO.save(new Reservation(reservation.getCust_id(), reservation.getReservation_id(), reservation.getReservation_date()));
    }

    @Override
    public void update(ReservationDTO reservation) throws SQLException, ClassNotFoundException {
        reservationDAO.update(new Reservation(reservation.getCust_id(), reservation.getReservation_id(), reservation.getReservation_date()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        reservationDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        reservationDAO.generateReport();
    }

    @Override
    public ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException {
        return reservationDAO.getAllReservationIDS();
    }
}
