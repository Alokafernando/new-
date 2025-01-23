package com.business.project.project03.dao.custom;

import com.business.project.project03.dao.CrudDAO;
import com.business.project.project03.entity.ImportCompany;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ImportCompanyDAO extends CrudDAO<ImportCompany> {
    ArrayList<String> getAllImportCompanyIDs() throws SQLException, ClassNotFoundException;
}
