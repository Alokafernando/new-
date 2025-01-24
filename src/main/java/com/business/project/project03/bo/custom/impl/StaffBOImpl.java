package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.StaffBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.StaffDAO;
import com.business.project.project03.entity.Staff;
import com.business.project.project03.model.StaffDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class StaffBOImpl implements StaffBO {

    StaffDAO staffDAO = (StaffDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.STAFF);

    @Override
    public ArrayList<StaffDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StaffDTO> staffDTOS = new ArrayList<>();
        ArrayList<Staff> staffs = staffDAO.getAll();
        for (Staff staff : staffs) {
            staffDTOS.add(new StaffDTO(staff.getStaff_id(), staff.getStaff_name(), staff.getAddress(), staff.getSalary(), staff.getRole()));
        }
        return staffDTOS;
    }

    @Override
    public void save(StaffDTO staff) throws SQLException, ClassNotFoundException {
        staffDAO.save(new Staff(staff.getStaff_id(), staff.getStaff_name(), staff.getAddress(), staff.getSalary(), staff.getRole()));
    }

    @Override
    public void update(StaffDTO staff) throws SQLException, ClassNotFoundException {
        staffDAO.update(new Staff(staff.getStaff_id(), staff.getStaff_name(), staff.getAddress(), staff.getSalary(), staff.getRole()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        staffDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return staffDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        staffDAO.generateReport();
    }
}
