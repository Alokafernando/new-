package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.CustomerBO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.model.CustomerDTO;
import com.business.project.project03.view.tdm.CustomerTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class CustomerController implements Initializable {

    @FXML
    private Button btnCustReservRepo;

    @FXML
    private Button btnCustomerRepo;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btncheckVehicle;

    @FXML
    private TableColumn<CustomerTM, String> colAddress;

    @FXML
    private TableColumn<CustomerTM, String> colContact;

    @FXML
    private TableColumn<CustomerTM, String> colCustID;

    @FXML
    private TableColumn<CustomerTM, String> colEmail;

    @FXML
    private TableColumn<CustomerTM, String> colName;

    @FXML
    private Label lblCustomerID;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;


    CustomerBO customerBO= (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("cust_ID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load customer id").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadNextCustomerId();
        loadTableData();

        btnSave.setDisable(false);
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);

        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtEmail.setText("");
    }

    private void loadTableData() throws SQLException, ClassNotFoundException {
        tblCustomer.getItems().clear();
        try{
            ArrayList<CustomerDTO> allCustomers = customerBO.getAll();
            for(CustomerDTO customer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(customer.getCust_ID(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));
            }
        }catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void loadNextCustomerId() throws SQLException, ClassNotFoundException {
        String nextCustomerID = customerBO.generateNewID();
        lblCustomerID.setText(nextCustomerID);
    }

    @FXML
    void btnCheckVehicleOnAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CheckVehicle.fxml"));
            AnchorPane pane = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("Check Vehicle");
            Scene scene = new Scene(pane);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = tblCustomer.getSelectionModel().getSelectedItem().getCust_ID();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES){
            customerBO.delete(id);
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer deleted successfully!").show();
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Failed to delete customer").show();
        }

    }

    @FXML
    void generateCistomerReport(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Customer.jrxml"
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
    void generateCustomerReservReport(ActionEvent event) throws ClassNotFoundException {
        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();

        if (customerTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/CustReservationRepo.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("p_Cust_ID", customerTM.getCust_ID());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
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
        //mistake
    }

    @FXML
    void onClickTable(MouseEvent event) {
        CustomerTM customerTM = tblCustomer.getSelectionModel().getSelectedItem();
        if (customerTM != null) {
            lblCustomerID.setText(customerTM.getCust_ID());
            txtName.setText(customerTM.getName());
            txtAddress.setText(customerTM.getAddress());
            txtContact.setText(customerTM.getContact());
            txtEmail.setText(customerTM.getEmail());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void saveCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerID = lblCustomerID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "[a-zA-Z0-9@.]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if (!isValidName) {
            txtName.setStyle(errorStyle);
        } else {
            txtName.setStyle(style);
        }
        if (!isValidAddress) {
            txtAddress.setStyle(errorStyle);
        } else {
            txtAddress.setStyle(style);
        }
        if (!isValidContact) {
            txtContact.setStyle(errorStyle);
        } else {
            txtContact.setStyle(style);
        }
        if (!isValidEmail) {
            txtEmail.setStyle(errorStyle);
        } else {
            txtEmail.setStyle(style);
        }

        if (isValidName && isValidAddress && isValidContact && isValidEmail) {
            CustomerDTO customerDTO = new CustomerDTO(customerID, name, address, contact, email);

            customerBO.save(customerDTO);
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input! Please ensure all fields are correctly filled.").show();
        }
    }

    @FXML
    void updateCustomer(ActionEvent event) throws SQLException, ClassNotFoundException {
        String customerID = lblCustomerID.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String addressPattern = "[a-zA-Z0-9@.]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = name.matches(namePattern);
        boolean isValidAddress = address.matches(addressPattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtName.setStyle(errorStyle);
        }else{
            txtName.setStyle(style);
        }
        if(!isValidAddress){
            txtAddress.setStyle(errorStyle);
        }else{
            txtAddress.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else {
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidAddress && isValidContact && isValidEmail){
            CustomerDTO customerDTO = new CustomerDTO(customerID, name, address, contact, email);

             customerBO.update(customerDTO);
             refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Customer updated successfully!").show();

        }else{
            new Alert(Alert.AlertType.ERROR, "Invalid input! Please ensure all fields are correctly filled.").show();
        }
    }

}
