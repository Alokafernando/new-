package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.PaymentBO;
import lk.ijse.pos.leyard.bo.custom.ReservationBO;
import lk.ijse.pos.leyard.db.DBConnection;
import lk.ijse.pos.leyard.model.PaymentDTO;
import lk.ijse.pos.leyard.view.tdm.PaymentTM;
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

public class PaymentController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnPayDetails;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbReservationID;

    @FXML
    private TableColumn<PaymentTM, Double> colDeposite;

    @FXML
    private TableColumn<PaymentTM, String> colPayID;

    @FXML
    private TableColumn<PaymentTM, String> colPayMethod;

    @FXML
    private TableColumn<PaymentTM, Double> colRemainAmount;

    @FXML
    private TableColumn<PaymentTM, String> colReserID;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private TableColumn<PaymentTM, Double> colTotalAmount;

    @FXML
    private Label lblPayID;

    @FXML
    private ToggleGroup payMethod;

    @FXML
    private ToggleGroup payStatus;

    @FXML
    private RadioButton rbCash;

    @FXML
    private RadioButton rbCheque;

    @FXML
    private RadioButton rbPaid;

    @FXML
    private RadioButton rbUnpaid;

    @FXML
    private TableView<PaymentTM> tblPayment;

    @FXML
    private TextField txtDeposite;

    @FXML
    private TextField txtTotalAmount;

    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOType.PAYMENT);
    ReservationBO reservationBO = (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colReserID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        colPayID.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
        colPayMethod.setCellValueFactory(new PropertyValueFactory<>("payment_method"));
        colDeposite.setCellValueFactory(new PropertyValueFactory<>("deposite"));
        colTotalAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colRemainAmount.setCellValueFactory(new PropertyValueFactory<>("remain_amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load payment id").show();
        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        lblPayID.setText(paymentBO.generateNewID());
        payMethod.selectToggle(null);
        payStatus.selectToggle(null);
        txtDeposite.clear();
        txtTotalAmount.clear();
        cmbReservationID.getSelectionModel().clearSelection();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        loadReservationIDS();
        loadTableData();
    }

    private void loadTableData() {
        tblPayment.getItems().clear();
        try {
            ArrayList<PaymentDTO> paymentDTOS = paymentBO.getAll();
            for (PaymentDTO paymentDTO : paymentDTOS) {
                tblPayment.getItems().add(new PaymentTM(paymentDTO.getReservation_id(), paymentDTO.getPay_id(), paymentDTO.getPayment_method(), paymentDTO.getDeposite(), paymentDTO.getAmount(), paymentDTO.getRemain_amount(), paymentDTO.getStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load payment id").show();
        }
    }

    private void loadReservationIDS() throws SQLException, ClassNotFoundException {
        ArrayList<String> reserIDS = reservationBO.getAllReservationIDS();
        ObservableList<String> observableList = FXCollections.observableArrayList(reserIDS);
        cmbReservationID.setItems(observableList);
    }

    @FXML
    void deletePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        String PayID = lblPayID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {

            paymentBO.delete(PayID);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment deleted").show();

        }
    }

    @FXML
    void generatePaymentRepo(ActionEvent event) throws ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Payment.jrxml"
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
    void generateReport(MouseEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) throws SQLException, ClassNotFoundException {
        PaymentTM paymentTM = tblPayment.getSelectionModel().getSelectedItem();
        if (paymentTM != null) {
            cmbReservationID.getItems().clear();
            List<String> reservationIDS = reservationBO.getAllReservationIDS();
            if(reservationIDS != null && !reservationIDS.isEmpty()) {
                cmbReservationID.getItems().addAll(reservationIDS);
                cmbReservationID.setValue(paymentTM.getReservation_id());
            }
        }

        lblPayID.setText(paymentTM.getPay_id());
        txtDeposite.setText(String.valueOf(paymentTM.getDeposite()));
        txtTotalAmount.setText(String.valueOf(paymentTM.getAmount()));


        if ("cash".equalsIgnoreCase(paymentTM.getPayment_method())) {
            rbCash.setSelected(true);
            rbCheque.setSelected(false);
        } else if ("cheque".equalsIgnoreCase(paymentTM.getPayment_method())) {
            rbCash.setSelected(false);
            rbCheque.setSelected(true);
        }

        if("paid".equalsIgnoreCase(paymentTM.getStatus())) {
            rbPaid.setSelected(true);
            rbUnpaid.setSelected(false);
        } else if ("unpaid".equalsIgnoreCase(paymentTM.getStatus())) {
            rbPaid.setSelected(false);
            rbUnpaid.setSelected(true);
        }

        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

    @FXML
    void savePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        String reservationId = cmbReservationID.getValue();
        String payId = lblPayID.getText();

        if (reservationId == null || reservationId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a reservation ID.").show();
            return;
        }

        String depositInput = txtDeposite.getText();
        String totalAmountInput = txtTotalAmount.getText();

        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";

        boolean isValidDeposite = depositInput.matches(doubleValuesPattern);
        boolean isValidTotalAmount = totalAmountInput.matches(doubleValuesPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidDeposite) {
            txtDeposite.setStyle(errorStyle);
        }else{
            txtDeposite.setStyle(style);
        }
        if (!isValidTotalAmount) {
            txtTotalAmount.setStyle(errorStyle);
        }else{
            txtTotalAmount.setStyle(style);
        }

        double deposit = 0.0;
        double totalAmount = 0.0;
        double remainingAmount = 0.0;
        try{
            deposit = Double.parseDouble(depositInput);
            totalAmount = Double.parseDouble(totalAmountInput);
            remainingAmount = totalAmount - deposit;

            if (remainingAmount < 0) {
                new Alert(Alert.AlertType.WARNING, "Deposit amount cannot exceed total amount.").show();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numeric values for Deposit and Total Amount.").show();
        }

        String payMethod;
        if (rbCash.isSelected()) {
            payMethod = "cash";
        } else if (rbCheque.isSelected()) {
            payMethod = "cheque";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method.").show();
            return;
        }

        String payStatus;
        if(rbPaid.isSelected()){
            payStatus = "paid";
        } else if (rbUnpaid.isSelected()) {
            payStatus = "unpaid";
        }else{
            new Alert(Alert.AlertType.WARNING, "Please select a pay status.").show();
            return;
        }

        if(isValidDeposite && isValidTotalAmount){
            PaymentDTO paymentDTO = new PaymentDTO(reservationId, payId, payMethod, deposit, totalAmount, remainingAmount, payStatus);

            paymentBO.save(paymentDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment saved..!").show();
        }
    }

    @FXML
    void updatePayment(ActionEvent event) throws SQLException, ClassNotFoundException {
        String reservationId = cmbReservationID.getValue();
        String payId = lblPayID.getText();

        if (reservationId == null || reservationId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a reservation ID.").show();
            return;
        }

        String depositInput = txtDeposite.getText();
        String totalAmountInput = txtTotalAmount.getText();

        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";

        boolean isValidDeposite = depositInput.matches(doubleValuesPattern);
        boolean isValidTotalAmount = totalAmountInput.matches(doubleValuesPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidDeposite) {
            txtDeposite.setStyle(errorStyle);
        }else{
            txtDeposite.setStyle(style);
        }
        if (!isValidTotalAmount) {
            txtTotalAmount.setStyle(errorStyle);
        }else{
            txtTotalAmount.setStyle(style);
        }

        double deposit = 0.0;
        double totalAmount = 0.0;
        double remainingAmount = 0.0;
        try{
            deposit = Double.parseDouble(depositInput);
            totalAmount = Double.parseDouble(totalAmountInput);
            remainingAmount = totalAmount - deposit;

            if (remainingAmount < 0) {
                new Alert(Alert.AlertType.WARNING, "Deposit amount cannot exceed total amount.").show();
                return;
            }

        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Please enter valid numeric values for Deposit and Total Amount.").show();
        }

        String payMethod;
        if (rbCash.isSelected()) {
            payMethod = "cash";
        } else if (rbCheque.isSelected()) {
            payMethod = "cheque";
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a payment method.").show();
            return;
        }

        String payStatus;
        if(rbPaid.isSelected()){
            payStatus = "paid";
        } else if (rbUnpaid.isSelected()) {
            payStatus = "unpaid";
        }else{
            new Alert(Alert.AlertType.WARNING, "Please select a pay status.").show();
            return;
        }

        if(isValidDeposite && isValidTotalAmount){
            PaymentDTO paymentDTO = new PaymentDTO(reservationId, payId, payMethod, deposit, totalAmount, remainingAmount, payStatus);

            paymentBO.update(paymentDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Payment updated..!").show();

        }
    }

}
