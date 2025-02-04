package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.TransportBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.TransportDAO;
import lk.ijse.pos.leyard.entity.Transport;
import lk.ijse.pos.leyard.model.TransportDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransportBOImpl implements TransportBO {

    TransportDAO transportDAO = (TransportDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TRANSPORT);

    @Override
    public ArrayList<TransportDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<TransportDTO> transportDTOS = new ArrayList<>();
        ArrayList<Transport> transports = transportDAO.getAll();
        for (Transport transport : transports) {
            transportDTOS.add(new TransportDTO(transport.getTransport_id(), transport.getTransport_type(), transport.getStart_date(), transport.getEnd_date(), transport.getDriver_id()));
        }
        return transportDTOS;
    }

    @Override
    public void save(TransportDTO transport) throws SQLException, ClassNotFoundException {
        transportDAO.save(new Transport(transport.getTransport_id(), transport.getTransport_type(), transport.getStart_date(), transport.getEnd_date(), transport.getDriver_id()));
    }

    @Override
    public void update(TransportDTO transport) throws SQLException, ClassNotFoundException {
        transportDAO.update(new Transport(transport.getTransport_id(), transport.getTransport_type(), transport.getStart_date(), transport.getEnd_date(), transport.getDriver_id()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        transportDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return transportDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        transportDAO.generateReport();
    }

    @Override
    public ArrayList<String> getAllTransportIDs() throws SQLException, ClassNotFoundException {
        return transportDAO.getAllTransportIds();
    }
}
