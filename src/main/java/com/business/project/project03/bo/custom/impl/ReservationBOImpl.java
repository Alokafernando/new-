package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.ReservationBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.ReservationDAO;
import com.business.project.project03.entity.Reservation;
import com.business.project.project03.model.ReservationDTO;
import com.business.project.project03.model.SupplierDTO;

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
}
