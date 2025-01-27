package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.TaxBO;
import com.business.project.project03.bo.custom.VehicleBO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.model.TaxDTO;
import com.business.project.project03.model.VehicleDTO;
import com.business.project.project03.view.tdm.TaxTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaxController implements Initializable {


    @FXML
    private Button btnDeleteTax;

    @FXML
    private Button btnTaxDetails;

    @FXML
    private Button btnTaxSave;

    @FXML
    private Button btnUpdateTax;

    @FXML
    private ComboBox<String> cmbVehicleID;

    @FXML
    private TableColumn<TaxTM, Double> colExportTax;

    @FXML
    private TableColumn<TaxTM, Double> colGroundTAx;

    @FXML
    private TableColumn<TaxTM, Double> colImportTax;

    @FXML
    private TableColumn<TaxTM, String> colTaxID;

    @FXML
    private TableColumn<TaxTM, String> colVehicleID;

    @FXML
    private Label lblModel;

    @FXML
    private Label lblTaxID;

    @FXML
    private TableView<TaxDTO> tblTax;

    @FXML
    private TextField txtExportTax;

    @FXML
    private TextField txtGroundTax;

    @FXML
    private TextField txtImportTax;

    TaxBO taxBO = (TaxBO) BOFactory.getInstance().getBO(BOFactory.BOType.TAX);
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
        colTaxID.setCellValueFactory(new PropertyValueFactory<>("tax_id"));
        colImportTax.setCellValueFactory(new PropertyValueFactory<>("import_tax"));
        colExportTax.setCellValueFactory(new PropertyValueFactory<>("export_tax"));
        colGroundTAx.setCellValueFactory(new PropertyValueFactory<>("ground_tax"));

        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        cmbVehicleID.getSelectionModel().clearSelection();
        lblTaxID.setText(taxBO.generateNewID());
        txtExportTax.setText("");
        txtImportTax.setText("");
        txtGroundTax.setText("");

        btnTaxSave.setDisable(false);
        btnUpdateTax.setDisable(true);
        btnDeleteTax.setDisable(true);

        loadVehicleIds();
        loadTableData();
    }

    private void loadTableData() throws ClassNotFoundException, SQLException {
        tblTax.getItems().isEmpty();
        try{
           ArrayList<TaxDTO> taxDTOS = taxBO.getAll();
           for (TaxDTO taxDTO :taxDTOS){
               tblTax.getItems().add(new TaxTM(taxDTO.getVehicleId(), taxDTO.getTaxId(), taxDTO.getImportTax(), taxDTO.getExportTax(), taxDTO.getGroundTax()));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadVehicleIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> vehicleIDs = vehicleBO.getAllVehicleIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList(vehicleIDs);
        cmbVehicleID.setItems(observableList);
    }

    @FXML
    void cmbVehicleIDOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedId = cmbVehicleID.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleTM = vehicleBO.findById(selectedId);
        if(vehicleTM != null) {
            lblModel.setText(vehicleTM.getModel());
        }
    }

    @FXML
    void deleteTax(ActionEvent event) throws SQLException, ClassNotFoundException {
        String taxId = lblTaxID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {

            taxBO.delete(taxId);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Tax deleted").show();

        }
    }

    @FXML
    void generateReport(MouseEvent event) {
        ////////////mistake
    }

    @FXML
    void generateTaxDetailsRepo(ActionEvent event) throws ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Tax.jrxml"
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
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
    }

    @FXML
    void onClickedtable(MouseEvent event) throws SQLException, ClassNotFoundException {
        TaxTM taxTM = (TaxTM) tblTax.getSelectionModel().getSelectedItem();/////////////////////////////////////

        if (taxTM != null) {
            cmbVehicleID.getItems().clear();
            List<String> reservationIDS = vehicleBO.getAllVehicleIDs();
            if(reservationIDS != null && !reservationIDS.isEmpty()) {
                cmbVehicleID.getItems().addAll(reservationIDS);
                cmbVehicleID.setValue(taxTM.getVehicleId());
            }
        }

        lblTaxID.setText(taxTM.getTaxId());
        txtImportTax.setText(String.valueOf(taxTM.getImportTax()));
        txtExportTax.setText(String.valueOf(taxTM.getExportTax()));
        txtGroundTax.setText(String.valueOf(taxTM.getGroundTax()));

        btnTaxSave.setDisable(true);
        btnDeleteTax.setDisable(false);
        btnUpdateTax.setDisable(false);
    }

    @FXML
    void saveTax(ActionEvent event) throws SQLException, ClassNotFoundException {
        String vehicleId = cmbVehicleID.getValue();
        String taxId = lblTaxID.getText();

        if (vehicleId == null || vehicleId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a vehicle ID.").show();
            return;
        }

        String importTax = txtImportTax.getText();
        String exportTax = txtExportTax.getText();
        String groundTax = txtGroundTax.getText();

        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";

        boolean isValidImportTax = importTax.matches(doubleValuesPattern);
        boolean isValidExportTax = exportTax.matches(doubleValuesPattern);
        boolean isValidGroundTax = groundTax.matches(doubleValuesPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidImportTax){
            txtImportTax.setStyle(errorStyle);
        }else{
            txtImportTax.setStyle(style);
        }
        if(!isValidExportTax){
            txtExportTax.setStyle(errorStyle);
        }else{
            txtExportTax.setStyle(style);
        }
        if(!isValidGroundTax){
            txtGroundTax.setStyle(errorStyle);
        }else{
            txtGroundTax.setStyle(style);
        }


        double importt = 0.0, export = 0.0, ground = 0.0;

        try {
            importt = Double.parseDouble(importTax);
            export = Double.parseDouble(exportTax);
            ground = Double.parseDouble(groundTax);

            if (importt < 0 || export < 0 || ground < 0) {
                new Alert(Alert.AlertType.WARNING, "Tax values cannot be negative.").show();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values for all taxes.").show();
            return;
        }

        if(isValidExportTax && isValidImportTax && isValidGroundTax){
            TaxDTO taxDTO = new TaxDTO(vehicleId, taxId, importt, export, ground);

            taxBO.save(taxDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Tax was saved.").show();

        }
    }

    @FXML
    void updateTax(ActionEvent event) throws SQLException, ClassNotFoundException {
        String vehicleId = cmbVehicleID.getValue();
        String taxId = lblTaxID.getText();

        if (vehicleId == null || vehicleId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a vehicle ID.").show();
            return;
        }

        String importTax = txtImportTax.getText();
        String exportTax = txtExportTax.getText();
        String groundTax = txtGroundTax.getText();

        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";

        boolean isValidImportTax = importTax.matches(doubleValuesPattern);
        boolean isValidExportTax = exportTax.matches(doubleValuesPattern);
        boolean isValidGroundTax = groundTax.matches(doubleValuesPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidImportTax){
            txtImportTax.setStyle(errorStyle);
        }else{
            txtImportTax.setStyle(style);
        }
        if(!isValidExportTax){
            txtExportTax.setStyle(errorStyle);
        }else{
            txtExportTax.setStyle(style);
        }
        if(!isValidGroundTax){
            txtGroundTax.setStyle(errorStyle);
        }else{
            txtGroundTax.setStyle(style);
        }


        double importt = 0.0, export = 0.0, ground = 0.0;

        try {
            importt = Double.parseDouble(importTax);
            export = Double.parseDouble(exportTax);
            ground = Double.parseDouble(groundTax);

            if (importt < 0 || export < 0 || ground < 0) {
                new Alert(Alert.AlertType.WARNING, "Tax values cannot be negative.").show();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values for all taxes.").show();
            return;
        }

        if(isValidExportTax && isValidImportTax && isValidGroundTax){
            TaxDTO taxDTO = new TaxDTO(vehicleId, taxId, importt, export, ground);

            taxBO.update(taxDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Tax was updated.").show();

        }
    }

}
