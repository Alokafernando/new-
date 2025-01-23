package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.ExportCompanyBO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.model.ExportCompanyDTO;
import com.business.project.project03.view.tdm.ExportCompanyTM;
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
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExportCompanyController implements Initializable {


    @FXML
    private Button btnDeleteExport;

    @FXML
    private Button btnExportCompanyRepo;

    @FXML
    private Button btnExportVehicleRepo;

    @FXML
    private Button btnSaveExport;

    @FXML
    private Button btnUpdateExport;

    @FXML
    private TableColumn<ExportCompanyTM, String> colCOmpanyName;

    @FXML
    private TableColumn<ExportCompanyTM, String> colCompanyID;

    @FXML
    private TableColumn<ExportCompanyTM, String> colContact;

    @FXML
    private TableColumn<ExportCompanyTM, String> colCountry;

    @FXML
    private TableColumn<ExportCompanyTM, String> colEmail;

    @FXML
    private Label lblCompanyID;

    @FXML
    private TableView<ExportCompanyTM> tblExport;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCountry;

    @FXML
    private TextField txtEmail;
    
    ExportCompanyBO exportCompanyBO = (ExportCompanyBO) BOFactory.getInstance().getBO(BOFactory.BOType.EXPORT_COMPANY);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCompanyID.setCellValueFactory(new PropertyValueFactory<>("company_ID"));
        colCOmpanyName.setCellValueFactory(new PropertyValueFactory<>("company_Name"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Export company id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextExportCompanyID();
        loadTableData();

        btnSaveExport.setDisable(false);
        btnUpdateExport.setDisable(true);
        btnDeleteExport.setDisable(true);

        txtCompanyName.setText("");
        txtContact.setText("");
        txtCountry.setText("");
        txtEmail.setText("");
    }

    private void loadTableData() {

        tblExport.getItems().clear();
        try{
            ArrayList<ExportCompanyDTO> exportCompanies = exportCompanyBO.getAll();
            for (ExportCompanyDTO exportCompany : exportCompanies) {
                tblExport.getItems().add(new ExportCompanyTM(exportCompany.getCompany_ID(), exportCompany.getCompany_Name(), exportCompany.getCountry(), exportCompany.getContact(), exportCompany.getEmail()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void loadNextExportCompanyID() throws SQLException, ClassNotFoundException {
        String exportCompanyID = exportCompanyBO.generateNewID();
        lblCompanyID.setText(exportCompanyID);
    }

    @FXML
    void deleteExportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
        String companyID = lblCompanyID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
             exportCompanyBO.delete(companyID);
             refeshPage();
             new Alert(Alert.AlertType.INFORMATION, "Export company deleted").show();
        } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete Export company").show();
        }
    }

    @FXML
    void generateExportComapanyDetailsReport(ActionEvent event) throws ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/ExportRepo.jrxml"
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
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @FXML
    void generateExportVehicleReport(ActionEvent event) {
        //not fill
    }

    @FXML
    void onClckedTable(MouseEvent event) {
        ExportCompanyTM exportCompanyTM = tblExport.getSelectionModel().getSelectedItem();
        if (exportCompanyTM != null) {
            lblCompanyID.setText(exportCompanyTM.getCompany_ID());
            txtCompanyName.setText(exportCompanyTM.getCompany_Name());
            txtContact.setText(exportCompanyTM.getContact());
            txtCountry.setText(exportCompanyTM.getCountry());
            txtEmail.setText(exportCompanyTM.getEmail());

            btnSaveExport.setDisable(true);
            btnDeleteExport.setDisable(false);
            btnUpdateExport.setDisable(false);
        }

    }

    @FXML
    void saveExportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
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
            ExportCompanyDTO exportCompanyDTO = new ExportCompanyDTO(exportCompanyID, exportCompanyName, exportCompanyCountry, exportCompanyContact, exportCompanyEmail);

            exportCompanyBO.save(exportCompanyDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Export company saved..!").show();

        }

    }

    @FXML
    void updateExportCompany(ActionEvent event) throws SQLException, ClassNotFoundException {
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
            ExportCompanyDTO exportCompanyDTO = new ExportCompanyDTO(exportCompanyID, exportCompanyName, exportCompanyCountry, exportCompanyContact, exportCompanyEmail);

            exportCompanyBO.update(exportCompanyDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Export company updated..!").show();

        }
    }

}
