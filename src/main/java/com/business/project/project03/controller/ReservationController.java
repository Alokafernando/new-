package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.CustomerBO;
import com.business.project.project03.bo.custom.ReservationBO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.model.ReservationDTO;
import com.business.project.project03.view.tdm.CustomerTM;
import com.business.project.project03.view.tdm.ReservationTM;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {


    @FXML
    private Button btnDeleteResetvation;

    @FXML
    private Button btnResercDetails;

    @FXML
    private Button btnSaveReservation;

    @FXML
    private Button btnUpdateReservation;

    @FXML
    private ComboBox<String> cmdCustID;

    @FXML
    private TableColumn<ReservationTM, String> colCustID;

    @FXML
    private TableColumn<ReservationTM, Date> colReservDate;

    @FXML
    private TableColumn<ReservationTM, String> colReserveID;

    @FXML
    private Label lblCustName;

    @FXML
    private Label lblResreveID;

    @FXML
    private TableView<ReservationTM> tblReservation;

    @FXML
    private TextField txtReservDate;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("cust_id"));
        colReserveID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        colReservDate.setCellValueFactory(new PropertyValueFactory<>("reservation_date"));

        try {
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Reservation id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadReservationID();
        txtReservDate.setText(LocalDate.now().toString());
        cmdCustID.getSelectionModel().clearSelection();
        txtReservDate.clear();

        btnSaveReservation.setDisable(false);
        btnUpdateReservation.setDisable(true);
        btnDeleteResetvation.setDisable(true);
        loadCustomerIds();
        loadTableData();
    }

    private void loadReservationID() throws SQLException, ClassNotFoundException {
        String reseID = reservationBO.generateNewID();
        lblResreveID.setText(reseID);
    }

    private void loadTableData() {
        tblReservation.getItems().clear();
        try{
            ArrayList<ReservationDTO> reservationDTOS = reservationBO.getAll();
            for(ReservationDTO reservationDTO : reservationDTOS) {
                tblReservation.getItems().add(new ReservationTM(reservationDTO.getCust_id(), reservationDTO.getReservation_date(), reservationDTO.getReservation_id()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load Reservation").show();
        }

    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> customerIds = customerBO.getAllCustomerIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(customerIds);
        cmdCustID.setItems(observableList);
    }

    @FXML
    void clickedOnTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        ReservationTM reservationTM = tblReservation.getSelectionModel().getSelectedItem();
        if (reservationTM != null) {
            cmdCustID.getItems().clear();
            List<String> customerIds = customerBO.getAllCustomerIds();
            if (customerIds != null && !customerIds.isEmpty()) {
                cmdCustID.getItems().addAll(customerIds);
                cmdCustID.setValue(reservationTM.getCust_id());
            }

            lblResreveID.setText(reservationTM.getReservation_id());
            txtReservDate.setText(reservationTM.getReservation_date());

            btnSaveReservation.setDisable(true);
            btnDeleteResetvation.setDisable(false);
            btnUpdateReservation.setDisable(false);
        }

    }

    @FXML
    void cmbCustIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedCustId = cmdCustID.getSelectionModel().getSelectedItem();
        CustomerTM customerTM = customerBO.findbyId(selectedCustId);
        if (customerTM != null) {
            lblCustName.setText(customerTM.getName());
        }
    }

    @FXML
    void deleteReservation(ActionEvent event) {

    }

    @FXML
    void generateReport(MouseEvent event) {
        //mistake
    }

    @FXML
    void generateReservDetailReport(ActionEvent event) throws ClassNotFoundException{
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Reservation.jrxml"
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
    void saveReservation(ActionEvent event) {

    }

    @FXML
    void updateReservation(ActionEvent event) {

    }

}
