package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationDAO extends CrudDAO<Reservation> {
    ArrayList<String> getAllReservationIDS() throws SQLException, ClassNotFoundException;
}
