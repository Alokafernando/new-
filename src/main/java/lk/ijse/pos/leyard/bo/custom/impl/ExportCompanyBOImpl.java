package lk.ijse.pos.leyard.bo.custom.impl;

import lk.ijse.pos.leyard.bo.custom.ExportCompanyBO;
import lk.ijse.pos.leyard.dao.DAOFactory;
import lk.ijse.pos.leyard.dao.custom.ExportCompanyDAO;
import lk.ijse.pos.leyard.entity.ExportCompany;
import lk.ijse.pos.leyard.model.ExportCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExportCompanyBOImpl implements ExportCompanyBO {

    ExportCompanyDAO exportCompanyDAO = (ExportCompanyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXPORT_COMPANY);

    @Override
    public ArrayList<ExportCompanyDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ExportCompanyDTO> exportCompanyDTOS = new ArrayList<>();
        ArrayList<ExportCompany> exportCompanies = exportCompanyDAO.getAll();
        for (ExportCompany exportCompany : exportCompanies) {
            exportCompanyDTOS.add(new ExportCompanyDTO(exportCompany.getCompany_ID(), exportCompany.getCompany_Name(), exportCompany.getCountry(), exportCompany.getContact(), exportCompany.getEmail()));
        }
        return exportCompanyDTOS;
    }

    @Override
    public void save(ExportCompanyDTO exportCompany) throws SQLException, ClassNotFoundException {
        exportCompanyDAO.save(new ExportCompany(exportCompany.getCompany_ID(), exportCompany.getCompany_Name(), exportCompany.getCountry(), exportCompany.getContact(), exportCompany.getEmail()));

    }

    @Override
    public void update(ExportCompanyDTO exportCompany) throws SQLException, ClassNotFoundException {
        exportCompanyDAO.update(new ExportCompany(exportCompany.getCompany_ID(), exportCompany.getCompany_Name(), exportCompany.getCountry(), exportCompany.getContact(), exportCompany.getEmail()));

    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        exportCompanyDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return exportCompanyDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        exportCompanyDAO.generateReport();
    }

    @Override
    public ArrayList<String> getAllExportCompanyIds() throws SQLException, ClassNotFoundException {
        return exportCompanyDAO.getAllExportCompanyIDs();
    }
}
