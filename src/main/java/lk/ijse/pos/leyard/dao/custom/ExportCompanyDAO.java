package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.ExportCompany;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExportCompanyDAO extends CrudDAO<ExportCompany> {
    ArrayList<String> getAllExportCompanyIDs() throws SQLException, ClassNotFoundException;
}
