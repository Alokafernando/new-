package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.PartDetailBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.SQLUtil;
import lk.ijse.pos.leyard.dao.custom.PartDAO;
import lk.ijse.pos.leyard.dao.custom.PartDetailDAO;
import lk.ijse.pos.leyard.dao.custom.VehicleDAO;
import lk.ijse.pos.leyard.db.DBConnection;
import lk.ijse.pos.leyard.entity.Part;
import lk.ijse.pos.leyard.entity.PartDetail;
import lk.ijse.pos.leyard.entity.Vehicle;
import lk.ijse.pos.leyard.model.PartDTO;
import lk.ijse.pos.leyard.model.PartDetailDTO;
import lk.ijse.pos.leyard.model.VehicleDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDetailBOImpl implements PartDetailBO {

    PartDAO partDAO = (PartDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PART);
    PartDetailDAO partDetailDAO = (PartDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PART_DETAIL);
    VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.VEHICLE);


    @Override
    public boolean placeOrder(List<PartDetailDTO> partDetailDTOS) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);

            for (PartDetailDTO dto : partDetailDTOS) {
//                boolean isSaved = SQLUtil.execute("insert into part_details values(?,?,?,?);",
//                        dto.getPart_id(),
//                        dto.getVehicle_id(),
//                        dto.getQuantity(),
//                        dto.getPrice());

               boolean isSaved = partDetailDAO.save(new PartDetail(dto.getPart_id(), dto.getVehicle_id(), dto.getQuantity(), dto.getPrice()));

                if (!isSaved) {
                    connection.rollback();
                    return false;
                }

                boolean isQtyReduced = partDAO.decrementQty(dto);

                if (!isQtyReduced) {
                    connection.rollback();
                    return false;
                }
            }


            connection.commit();
            return true;

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public PartDTO searchPart(String partId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public PartDTO findPart(String partId) throws SQLException, ClassNotFoundException {
        PartDTO part = partDAO.findById(partId);
        PartDTO partDTO = new PartDTO( part.getPart_id(), part.getName(), part.getPrice(), part.getQuantity());
        return partDTO;
    }

    @Override
    public VehicleDTO findVehicle(String vehicleId) throws SQLException, ClassNotFoundException {
        VehicleDTO vehicle = vehicleDAO.findById(vehicleId);
        VehicleDTO vehicleDTO = new VehicleDTO(vehicle.getImport_company_id(), vehicle.getImport_date(), vehicle.getVehicle_id(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getCurrent_status(), vehicle.getExport_company_id(), vehicle.getExport_date(), vehicle.getSale_date(), vehicle.getImport_price(), vehicle.getExport_price(), vehicle.getReservation_id(), vehicle.getTransport_id());
        return vehicleDTO;
    }

    @Override
    public ArrayList<PartDTO> getAllPartIds() throws SQLException, ClassNotFoundException {
        ArrayList<Part> parts = partDAO.getAll();
        ArrayList<PartDTO> partDTOs = new ArrayList<>();
        for (Part part : parts) {
            partDTOs.add(new PartDTO(part.getPart_id(), part.getName(), part.getPrice(), part.getQuantity()));
        }
        return partDTOs;
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicleIds() throws SQLException, ClassNotFoundException {
        ArrayList< Vehicle> vehicles = vehicleDAO.getAll();
        ArrayList<VehicleDTO> vehicleDTOS = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            vehicleDTOS.add(new VehicleDTO(vehicle.getImport_company_id(), vehicle.getImport_date(), vehicle.getVehicle_id(), vehicle.getModel(), vehicle.getYear(), vehicle.getColor(), vehicle.getCurrent_status(), vehicle.getExport_company_id(), vehicle.getExport_date(), vehicle.getSale_date(), vehicle.getImport_price(), vehicle.getExport_price(), vehicle.getReservation_id(), vehicle.getTransport_id()));
        }
        return vehicleDTOS;
    }
}
