package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.SupplyDetailBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.PartDAO;
import com.business.project.project03.dao.custom.SupplierDAO;
import com.business.project.project03.dao.custom.SupplyDetailDAO;
import com.business.project.project03.dao.custom.VehicleDAO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.entity.Part;
import com.business.project.project03.entity.Supplier;
import com.business.project.project03.entity.SupplyDetails;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.model.SupplierDTO;
import com.business.project.project03.model.SupplierDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplyDetailBOImpl implements SupplyDetailBO {

    SupplyDetailDAO supplyDetailDAO = (SupplyDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER_DETAIL);
    PartDAO partDAO = (PartDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PART);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public boolean placeOrder(List<SupplierDetailDTO> supplierDetailDTOS) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);

            for (SupplierDetailDTO dto : supplierDetailDTOS) {
//                boolean isSaved = SQLUtil.execute(
//                        "insert into supplier_details values (?,?,?,?,?)",
//                        dto.getSupplier_id(),
//                        dto.getPart_id(),
//                        dto.getSupply_date(),
//                        dto.getQuantity(),
//                        dto.getTotal()
//                );

                boolean isSaved = supplyDetailDAO.save(new SupplyDetails());

                if (!isSaved) {
                    connection.rollback();
                    return false;
                }

                boolean isQtyReduced = partDAO.redQty(dto);

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
    public PartDTO findPart(String partId) throws SQLException, ClassNotFoundException {
        PartDTO part = partDAO.findById(partId);
        PartDTO partDTO = new PartDTO( part.getPart_id(), part.getName(), part.getPrice(), part.getQuantity());
        return partDTO;
    }

    @Override
    public SupplierDTO findSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        SupplierDTO supplier = supplierDAO.findById(supplierId);
        return new SupplierDTO(supplier.getSupplier_id(), supplier.getName(), supplier.getContact(), supplier.getEmail());
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
    public ArrayList<SupplierDTO> getAllSupplierIds() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOs = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            supplierDTOs.add(new SupplierDTO(supplier.getSupplier_id(), supplier.getName(), supplier.getContact(), supplier.getEmail()));
        }
        return supplierDTOs;
    }
}
