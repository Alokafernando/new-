package com.business.project.project03.dao.custom.impl;

import com.business.project.project03.dao.SQLUtil;
import com.business.project.project03.dao.custom.ExportCompanyDAO;
import com.business.project.project03.entity.Driver;
import com.business.project.project03.entity.ExportCompany;
import com.business.project.project03.model.ExportCompanyDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExportCompanyDAOImpl implements ExportCompanyDAO {

    @Override
    public ArrayList<ExportCompany> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select * from export_company");
        ArrayList<ExportCompany> exportCompanyDTOS = new ArrayList<>();

        while (rst.next()) {
                   exportCompanyDTOS.add(new ExportCompany(rst.getString("company_ID"), rst.getString("company_Name"), rst.getString("country"), rst.getString("contact"), rst.getString("email")));
        }
        return exportCompanyDTOS;
    }

    @Override
    public boolean save(ExportCompany entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into export_company values(?,?,?,?,?)",
                entity.getCompany_ID(), entity.getCompany_Name(), entity.getCountry(), entity.getContact(), entity.getEmail());
    }

    @Override
    public void update(ExportCompany entity) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("update export_company set company_name =?, county=?, contact=?, email=? where company_ID =?",
                entity.getCompany_Name(), entity.getCountry(), entity.getContact(), entity.getEmail(), entity.getCompany_ID());
    }

    @Override
    public void delete(String id) throws SQLException, ClassNotFoundException {
        SQLUtil.execute("delete from export_company where company_ID =?", id);

    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("select company_ID from export_company order by company_ID desc limit 1");

        if (resultSet.next()) {
            String lastID = resultSet.getString(1);
            String subString = lastID.substring(1);
            int i = Integer.parseInt(subString);
            int newIndex = i+1;
            return String.format("E%03d", newIndex);
        }
        return "E001";
    }

    @Override
    public void generateReport() throws SQLException, ClassNotFoundException {
        //empty
    }

    @Override
    public ArrayList<String> getAllExportCompanyIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("select company_ID from export_company");
        ArrayList<String> exportIDS = new ArrayList<>();

        while (rst.next()) {
            exportIDS.add(rst.getString(1));
        }
        return exportIDS;
    }
}
