package lk.ijse.pos.leyard.dao.custom;

import lk.ijse.pos.leyard.dao.CrudDAO;
import lk.ijse.pos.leyard.entity.ImportCompany;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ImportCompanyDAO extends CrudDAO<ImportCompany> {
    ArrayList<String> getAllImportCompanyIDs() throws SQLException, ClassNotFoundException;
}
