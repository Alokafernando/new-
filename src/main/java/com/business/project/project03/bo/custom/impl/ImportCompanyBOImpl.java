package com.business.project.project03.bo.custom.impl;

import com.business.project.project03.bo.custom.ImportCompanyBO;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.dao.custom.ImportCompanyDAO;
import com.business.project.project03.entity.ImportCompany;
import com.business.project.project03.model.ImportCompanyDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ImportCompanyBOImpl implements ImportCompanyBO {

    ImportCompanyDAO importCompanyDAO = (ImportCompanyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.IMPORT_COMPANY);

    @Override
    public ArrayList<ImportCompanyDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ImportCompanyDTO> ImportCompanyDTO = new ArrayList<>();
        ArrayList<ImportCompany> importCompanies = importCompanyDAO.getAll();
        for (ImportCompany importCompany : importCompanies) {
            ImportCompanyDTO.add(new ImportCompanyDTO(importCompany.getCompany_ID(), importCompany.getCompany_Name(), importCompany.getCountry(), importCompany.getContact(), importCompany.getEmail()));
        }
        return ImportCompanyDTO;
    }

    @Override
    public void save(ImportCompanyDTO importCompany) throws SQLException, ClassNotFoundException {
        importCompanyDAO.save(new ImportCompany(importCompany.getCompany_ID(), importCompany.getCompany_Name(), importCompany.getCountry(), importCompany.getContact(), importCompany.getEmail()));
    }

    @Override
    public void update(ImportCompanyDTO importCompany) throws SQLException, ClassNotFoundException {
        importCompanyDAO.update(new ImportCompany(importCompany.getCompany_ID(), importCompany.getCompany_Name(), importCompany.getCountry(), importCompany.getContact(), importCompany.getEmail()));
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        importCompanyDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return importCompanyDAO.generateNewID();
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        importCompanyDAO.generateReport();
    }
}
