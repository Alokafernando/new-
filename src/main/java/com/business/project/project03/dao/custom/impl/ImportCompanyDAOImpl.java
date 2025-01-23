package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.ImportCompanyDAO;
import com.business.project.project03.entity.ExportCompany;
import com.business.project.project03.entity.ImportCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImportCompanyDAOImpl implements ImportCompanyDAO {


    @Override
    public ArrayList<ImportCompany> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from import_company");
        ArrayList<ImportCompany> ImportCompanies = new ArrayList<>();

        while (rst.next()) {
            ImportCompanies.add(new ImportCompany(rst.getString("company_ID"), rst.getString("company_name"), rst.getString("county"), rst.getString("contact"), rst.getString("email")));
        }
        return ImportCompanies;
    }

    @Override
    public boolean save(ImportCompany entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into import_company values(?,?,?,?,?)",
                entity.getCompany_ID(), entity.getCompany_Name(), entity.getCountry(), entity.getContact(), entity.getEmail());
    }

    @Override
    public void update(ImportCompany entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update import_company set company_name =?, county=?, contact=?, email=? where company_ID =?",
                entity.getCompany_Name(), entity.getCountry(), entity.getContact(), entity.getEmail(), entity.getCompany_ID());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
         SQLUtil.execute("delete from import_company where company_ID=?", id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select company_ID from import_company order by company_ID desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("I%03d", newIndex);
        }
        return "I001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllImportCompanyIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select company_ID from import_company");
        ArrayList<String> importIDS = new ArrayList<>();

        while (rst.next()) {
            importIDS.add(rst.getString(1));
        }
        return importIDS;
    }
}
