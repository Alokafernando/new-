package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.DriverBO;
import com.business.project.project03.bo.custom.impl.DriverBOImpl;
import com.business.project.project03.dao.DAOFactory;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.model.DriverDTO;
import com.business.project.project03.view.tdm.DriverTM;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class DriverController implements Initializable {
   

    @FXML
    private Button btnDriverDetails;

    @FXML
    private Button btnSaveDriver;

    @FXML
    private Button btndeleteDriver;

    @FXML
    private Button btnupdateDriver;

    @FXML
    private TableColumn<DriverTM, String> colContact;

    @FXML
    private TableColumn<DriverTM, String> colDiverID;

    @FXML
    private TableColumn<DriverTM, String> colName;

    @FXML
    private Label lblDiverID;

    @FXML
    private TableView<DriverTM> tblDriver;

    @FXML
    private TextField txtDriverContact;

    @FXML
    private TextField txtDriverName;

    DriverBO driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDiverID.setCellValueFactory(new PropertyValueFactory<>("driver_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        try {
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load driver id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadNextDriverID();
        loadTableData();

        btnSaveDriver.setDisable(false);
        btnupdateDriver.setDisable(true);
        btndeleteDriver.setDisable(true);

        txtDriverName.setText("");
        txtDriverContact.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
       tblDriver.getItems().clear();
       try{
           ArrayList<DriverDTO> drivers = driverBO.getAll();
           for(DriverDTO driver : drivers){
               tblDriver.getItems().add(new DriverTM(driver.getDriver_id(),driver.getName(),driver.getContact()));
           }
       } catch (Exception e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }

    }

    private void loadNextDriverID() throws SQLException, ClassNotFoundException {
        String  driverID = driverBO.generateNewID();
        lblDiverID.setText(driverID);
    }

    @FXML
    void deleteDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverID = lblDiverID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {
            driverBO.delete(driverID);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Driver deletes successfully!").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Failed to delete customer").show();
        }

    }

    @FXML
    void generateDriverdetailRepo(ActionEvent event) throws ClassNotFoundException{
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Driver.jrxml"
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
    void onClickedTable(ActionEvent event) {
        DriverTM driverTM = tblDriver.getSelectionModel().getSelectedItem();
        if (driverTM != null) {
            lblDiverID.setText(driverTM.getDriver_id());
            txtDriverName.setText(driverTM.getName());
            txtDriverContact.setText(driverTM.getContact());

            btnSaveDriver.setDisable(true);
            btnupdateDriver.setDisable(false);
            btndeleteDriver.setDisable(false);
        }
    }

    @FXML
    void saveDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = lblDiverID.getText();
        String driverName = txtDriverName.getText();
        String contact = txtDriverContact.getText();

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = driverName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidName){
            txtDriverName.setStyle(errorStyle);
        }else{
            txtDriverName.setStyle(style);
        }
        if (!isValidContact){
            txtDriverContact.setStyle(errorStyle);
        }else {
            txtDriverContact.setStyle(style);
        }

        if (isValidName && isValidContact){
            DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);

            driverBO.save(driverDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Driver saved successfully!").show();

        }
    }

    @FXML
    void updateDriver(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = lblDiverID.getText();
        String driverName = txtDriverName.getText();
        String contact = txtDriverContact.getText();

        String namePattern = "^[A-Za-z ]+$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = driverName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidName){
            txtDriverName.setStyle(errorStyle);
        }else{
            txtDriverName.setStyle(style);
        }
        if (!isValidContact){
            txtDriverContact.setStyle(errorStyle);
        }else {
            txtDriverContact.setStyle(style);
        }

        if (isValidName && isValidContact){
            DriverDTO driverDTO = new DriverDTO(driverId, driverName, contact);

            driverBO.delete(String.valueOf(driverDTO));
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Driver updated successfully").show();

        }

    }

}
