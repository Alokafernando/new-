package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.ImportCompanyBO;
import lk.ijse.pos.leyard.db.DBConnection;
import lk.ijse.pos.leyard.model.ImportCompanyDTO;
import lk.ijse.pos.leyard.view.tdm.ImportCompanyTM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class ImportCompanyController implements Initializable {

    @FXML
    private Button btnDeleteImport;

    @FXML
    private Button btnImportCompanyRepo;

    @FXML
    private Button btnImportVehicleRepo;

    @FXML
    private Button btnSaveImport;

    @FXML
    private Button btnUpdateImport;

    @FXML
    private TableColumn<ImportCompanyTM, String> colCompanyID;

    @FXML
    private TableColumn<ImportCompanyTM, String> colCompanyName;

    @FXML
    private TableColumn<ImportCompanyTM, String> colContact;

    @FXML
    private TableColumn<ImportCompanyTM, String> colCountry;

    @FXML
    private TableColumn<ImportCompanyTM, String> colEmail;

    @FXML
    private Label lblCompanyID;

    @FXML
    private TableView<ImportCompanyTM> tblImport;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;

    ImportCompanyBO importCompanyBO = (ImportCompanyBO) BOFactory.getInstance().getBO(BOFactory.BOType.IMPORT_COMPANY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCompanyID.setCellValueFactory(new PropertyValueFactory<>("company_ID"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("company_Name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Import company id").show();
        }

    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextImportCompanyID();
        loadTableDat();

        btnSaveImport.setDisable(false);
        btnDeleteImport.setDisable(true);
        btnUpdateImport.setDisable(true);

        txtCompanyName.setText("");
        txtContact.setText("");
        txtCountry.setText("");
        txtEmail.setText("");

    }

    private void loadTableDat() {
        tblImport.getItems().clear();
        try{
            ArrayList<ImportCompanyDTO> importCompanyTMs = importCompanyBO.getAll();
            for (ImportCompanyDTO importCompanyDTO : importCompanyTMs) {
                tblImport.getItems().add(new ImportCompanyTM(importCompanyDTO.getCompany_ID(), importCompanyDTO.getCompany_Name(), importCompanyDTO.getCountry(), importCompanyDTO.getContact(), importCompanyDTO.getEmail()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadNextImportCompanyID() throws SQLException, ClassNotFoundException {
        String importCompanyID = importCompanyBO.generateNewID();
        lblCompanyID.setText(importCompanyID);
    }

    @FXML
    void deleteImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String companyID = lblCompanyID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            importCompanyBO.delete(companyID);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Import company deleted").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Fail to delete Import company").show();
        }
    }

    @FXML
    void generateImportComapnyDetailsReport(ActionEvent event) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/ImportCompanyRepo.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
           e.printStackTrace();
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }

    }

    @FXML
    void generateImportVehicleReport(ActionEvent event) throws ClassNotFoundException {
        ImportCompanyTM importCompanyTM = tblImport.getSelectionModel().getSelectedItem();

        if (importCompanyTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/importVehicle.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Vehicle_ID", importCompanyTM.getCompany_ID());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @FXML
    void onClickedTable(MouseEvent event) {
        ImportCompanyTM importCompanyTM = tblImport.getSelectionModel().getSelectedItem();
        if (importCompanyTM != null) {
            lblCompanyID.setText(importCompanyTM.getCompany_ID());
            txtCompanyName.setText(importCompanyTM.getCompany_Name());
            txtContact.setText(importCompanyTM.getContact());
            txtCountry.setText(importCompanyTM.getCountry());
            txtEmail.setText(importCompanyTM.getEmail());

            btnSaveImport.setDisable(true);
            btnDeleteImport.setDisable(false);
            btnUpdateImport.setDisable(false);

        }
    }

    @FXML
    void saveImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String exportCompanyID = lblCompanyID.getText();
        String exportCompanyName = txtCompanyName.getText();
        String exportCompanyContact = txtContact.getText();
        String exportCompanyCountry = txtCountry.getText();
        String exportCompanyEmail = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String countryPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = exportCompanyName.matches(namePattern);
        boolean isValidCountry = exportCompanyCountry.matches(countryPattern);
        boolean isValidEmail = exportCompanyEmail.matches(emailPattern);
        boolean isValidPhone = exportCompanyContact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidCountry){
            txtCountry.setStyle(errorStyle);
        }else {
            txtCountry.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }
        if(!isValidPhone){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }

        if(isValidName && isValidCountry && isValidEmail && isValidPhone){
            ImportCompanyDTO importCompanyDTO = new ImportCompanyDTO(exportCompanyID, exportCompanyName, exportCompanyCountry, exportCompanyContact, exportCompanyEmail);

            importCompanyBO.save(importCompanyDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Export company saved..!").show();

        }

    }

    @FXML
    void updateImportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String exportCompanyID = lblCompanyID.getText();
        String exportCompanyName = txtCompanyName.getText();
        String exportCompanyContact = txtContact.getText();
        String exportCompanyCountry = txtCountry.getText();
        String exportCompanyEmail = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String countryPattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = exportCompanyName.matches(namePattern);
        boolean isValidCountry = exportCompanyCountry.matches(countryPattern);
        boolean isValidEmail = exportCompanyEmail.matches(emailPattern);
        boolean isValidPhone = exportCompanyContact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidCountry){
            txtCountry.setStyle(errorStyle);
        }else {
            txtCountry.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }
        if(!isValidPhone){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }

        if(isValidName && isValidCountry && isValidEmail && isValidPhone){
            ImportCompanyDTO importCompanyDTO = new ImportCompanyDTO(exportCompanyID, exportCompanyName, exportCompanyCountry, exportCompanyContact, exportCompanyEmail);

            importCompanyBO.update(importCompanyDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Export company saved..!").show();

        }
    }

}
