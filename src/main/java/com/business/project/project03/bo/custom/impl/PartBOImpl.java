package com.business.project.project03.bo.custom.impl;


import com.business.project.project03.bo.custom.PartBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.PartDAO;
import com.business.project.project03.entity.Part;
import com.business.project.project03.model.PartDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class PartBOImpl implements PartBO {

    PartDAO partDAO = (PartDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PART);

    @Override
    public ArrayList<PartDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PartDTO> partDTOS = new ArrayList<>();
        ArrayList<Part> parts = partDAO.getAll();
        for (Part part : parts) {
            partDTOS.add(new PartDTO(part.getPart_id(), part.getName(), part.getPrice(), part.getQuantity()));
        }
        return partDTOS;
    }

    @Override
    public void save(PartDTO part) throws SQLException, ClassNotFoundException {
        partDAO.save(new Part(part.getPart_id(), part.getName(), part.getPrice(), part.getQuantity()));
    }

    @Override
    public void update(PartDTO part) throws SQLException, ClassNotFoundException {
        partDAO.update(new Part(part.getPart_id(), part.getName(), part.getPrice(), part.getQuantity()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        partDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return partDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        partDAO.generateReport();
    }

    @Override
    public ArrayList<String> getAllPartIds() throws SQLException, ClassNotFoundException {
        return partDAO.getAllPartIds();
    }

    @Override
    public PartDTO findById(String selectedId) throws SQLException, ClassNotFoundException {
        return partDAO.findById(selectedId);
    }
}
