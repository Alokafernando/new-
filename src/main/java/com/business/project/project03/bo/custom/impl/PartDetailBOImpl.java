package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.PartDetailBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.PartDAO;
import com.business.project.project03.dao.custom.PartDetailDAO;
import com.business.project.project03.dao.custom.VehicleDAO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.entity.Part;
import com.business.project.project03.entity.PartDetail;
import com.business.project.project03.entity.SupplyDetails;
import com.business.project.project03.entity.Vehicle;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.PartDetailDTO;
import com.business.project.project03.model.SupplierDetailDTO;
import com.business.project.project03.model.VehicleDTO;

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
//                boolean isSaved = SQLUtil.execute(
//                        "insert into supplier_details values (?,?,?,?,?)",
//                        dto.getSupplier_id(),
//                        dto.getPart_id(),
//                        dto.getSupply_date(),
//                        dto.getQuantity(),
//                        dto.getTotal()
//                );

                boolean isSaved = partDetailDAO.save(new PartDetail());

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
