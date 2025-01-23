package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.dao.SuperDAO;
import com.business.project.project03.entity.Driver;
import com.business.project.project03.entity.ExportCompany;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExportCompanyDAO extends CrudDAO<ExportCompany> {
    ArrayList<String> getAllExportCompanyIDs() throws SQLException, ClassNotFoundException;
}
