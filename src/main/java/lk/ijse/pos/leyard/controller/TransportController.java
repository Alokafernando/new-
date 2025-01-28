package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.DriverBO;
import lk.ijse.pos.leyard.bo.custom.TransportBO;
import lk.ijse.pos.leyard.db.DBConnection;
import lk.ijse.pos.leyard.model.TransportDTO;
import lk.ijse.pos.leyard.view.tdm.DriverTM;
import lk.ijse.pos.leyard.view.tdm.TransportTM;
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

public class TransportController implements Initializable {

    TransportBO transportBO = (TransportBO) BOFactory.getInstance().getBO(BOFactory.BOType.TRANSPORT);
    DriverBO driverBO = (DriverBO) BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);

    @FXML
    private ToggleGroup TransportType1;

    @FXML
    private Button btnDeleteTransport;

    @FXML
    private Button btnSaveTransport;

    @FXML
    private Button btnTransportDetail;

    @FXML
    private Button btnUpdateTransport;

    @FXML
    private ComboBox<String> cmdDriverID;

    @FXML
    private TableColumn<TransportTM, String> colDriverID;

    @FXML
    private TableColumn<TransportTM, String> colEndDate;

    @FXML
    private TableColumn<TransportTM, String> colStartDate;

    @FXML
    private TableColumn<TransportTM, String> colTransportID;

    @FXML
    private TableColumn<TransportTM, String> colTransportType;

    @FXML
    private Label lblDriverName;

    @FXML
    private Label lblTransportID;

    @FXML
    private RadioButton rbExport;

    @FXML
    private RadioButton rbImport;

    @FXML
    private TableView<TransportTM> tblTransport;

    @FXML
    private TextField txtEndDate;

    @FXML
    private TextField txtStartDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colTransportID.setCellValueFactory(new PropertyValueFactory<>("transport_id"));
        colTransportType.setCellValueFactory(new PropertyValueFactory<>("transport_type"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        colDriverID.setCellValueFactory(new PropertyValueFactory<>("driver_id"));

        try{
            refeshpage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load transport id").show();
        }

    }

    private void refeshpage() throws SQLException, ClassNotFoundException {
        lblTransportID.setText(transportBO.generateNewID());
        cmdDriverID.getSelectionModel().clearSelection();
        TransportType1.selectToggle(null);
        txtStartDate.setText("");
        txtEndDate.setText("");

        btnSaveTransport.setDisable(false);
        btnUpdateTransport.setDisable(true);
        btnDeleteTransport.setDisable(true);

        loadDriverIds();
        loadTableData();
    }

    private void loadTableData() {
        tblTransport.getItems().clear();
        try{
            ArrayList<TransportDTO> transportDTOS = transportBO.getAll();
            for (TransportDTO transportDTO : transportDTOS) {
                tblTransport.getItems().add(new TransportTM(transportDTO.getTransport_id(), transportDTO.getTransport_type(), transportDTO.getStart_date(), transportDTO.getEnd_date(), transportDTO.getDriver_id()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load transport id").show();
        }
    }

    private void loadDriverIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> driverIds = driverBO.getAllDriverIds();
        ObservableList<String> observableList = FXCollections.observableArrayList(driverIds);
        cmdDriverID.setItems(observableList);
    }

    @FXML
    void cmbDriverOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedDriverID = cmdDriverID.getSelectionModel().getSelectedItem();
        DriverTM driverTM = driverBO.findById(selectedDriverID);
        if (driverTM != null) {
            lblDriverName.setText(driverTM.getName());
        }
    }

    @FXML
    void deleteTransport(ActionEvent event) throws SQLException, ClassNotFoundException {
        String transportId = lblTransportID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {

            transportBO.delete(transportId);
            refeshpage();
            new Alert(Alert.AlertType.INFORMATION, "Transport deleted").show();

        }
    }

    @FXML
    void generateTransportRepo(ActionEvent event) throws ClassNotFoundException{
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Transport.jrxml"
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
    void onClickedTabel(MouseEvent event) throws SQLException, ClassNotFoundException {
        TransportTM transportTM = tblTransport.getSelectionModel().getSelectedItem();
        if (transportTM != null) {
            cmdDriverID.getItems().clear();
            List<String> driverIds = driverBO.getAllDriverIds();
            if(driverIds != null && !driverIds.isEmpty()) {
                cmdDriverID.getItems().addAll(driverIds);
                cmdDriverID.setValue(transportTM.getDriver_id());
            }
        }

        lblTransportID.setText(transportTM.getTransport_id());
        txtStartDate.setText(transportTM.getStart_date());
        txtEndDate.setText(transportTM.getEnd_date());

        if("Export".equalsIgnoreCase(transportTM.getTransport_type())) {
            rbExport.setSelected(true);
            rbImport.setSelected(false);
        } else if ("Import".equalsIgnoreCase(transportTM.getTransport_type())) {
            rbExport.setSelected(false);
            rbImport.setSelected(true);
        }

        btnSaveTransport.setDisable(true);
        btnDeleteTransport.setDisable(false);
        btnUpdateTransport.setDisable(false);

    }

    @FXML
    void saveTransport(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = cmdDriverID.getValue();
        String transportId = lblTransportID.getText();
        String startDate = txtStartDate.getText();
        String endDate = txtEndDate.getText();

        String transportType;
        if (rbExport.isSelected()) {
            transportType = "export";
        } else if (rbImport.isSelected()) {
            transportType = "import";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
            return;
        }

        if (driverId == null || driverId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a driver ID.").show();
            return;
        }
        if(startDate == null || startDate.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Please input start date").show();
            return;
        }

        String datePattern = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";

        boolean isValidStartDate = startDate.matches(datePattern);
        boolean isValidEndDate = true;
        if (endDate != null && !endDate.isEmpty()) {
            isValidEndDate = endDate.matches(datePattern);
        }

        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color: #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidStartDate){
            txtStartDate.setStyle(errorStyle);
        }else {
            txtStartDate.setStyle(style);
        }
        if(!isValidEndDate){
            txtEndDate.setStyle(errorStyle);
        }else {
            txtEndDate.setStyle(style);
        }

        if(isValidStartDate && isValidEndDate){
            TransportDTO transportDTO = new TransportDTO(transportId, transportType, startDate, endDate, driverId);

            transportBO.save(transportDTO);
            refeshpage();
            new Alert(Alert.AlertType.INFORMATION, "Transport saved..!").show();

        }
    }

    @FXML
    void updateTranport(ActionEvent event) throws SQLException, ClassNotFoundException {
        String driverId = cmdDriverID.getValue();
        String transportId = lblTransportID.getText();
        String startDate = txtStartDate.getText();
        String endDate = txtEndDate.getText();

        String transportType;
        if (rbExport.isSelected()) {
            transportType = "export";
        } else if (rbImport.isSelected()) {
            transportType = "import";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a transport type.").show();
            return;
        }

        if (driverId == null || driverId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a driver ID.").show();
            return;
        }
        if(startDate == null || startDate.isEmpty()){
            new Alert(Alert.AlertType.WARNING, "Please input start date").show();
            return;
        }

        String datePattern = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";

        boolean isValidStartDate = startDate.matches(datePattern);
        boolean isValidEndDate = true; // Default to true if endDate is null or empty
        if (endDate != null && !endDate.isEmpty()) {
            isValidEndDate = endDate.matches(datePattern);
        }
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color: #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if(!isValidStartDate){
            txtStartDate.setStyle(errorStyle);
        }else {
            txtStartDate.setStyle(style);
        }
        if(!isValidEndDate){
            txtEndDate.setStyle(errorStyle);
        }else {
            txtEndDate.setStyle(style);
        }

        if(isValidStartDate && isValidEndDate){
            TransportDTO transportDTO = new TransportDTO(transportId, transportType, startDate, endDate, driverId);

            transportBO.update(transportDTO);
            refeshpage();
            new Alert(Alert.AlertType.INFORMATION, "Transport updated..!").show();

        }
    }


}
