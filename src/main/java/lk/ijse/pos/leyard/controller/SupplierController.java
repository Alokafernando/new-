package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.SupplierBO;
import lk.ijse.pos.leyard.db.DBConnection;
import lk.ijse.pos.leyard.model.SupplierDTO;
import lk.ijse.pos.leyard.view.tdm.SupplierTM;
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

public class SupplierController implements Initializable {

    @FXML
    private Button btnDeleteSupplier;

    @FXML
    private Button btnSaveSupplier;

    @FXML
    private Button btnSupplierDetailRepo;

    @FXML
    private Button btnSupply;

    @FXML
    private Button btnUpdateSupplier;

    @FXML
    private TableColumn<SupplierTM, String> colCompanyName;

    @FXML
    private TableColumn<SupplierTM, String> colContact;

    @FXML
    private TableColumn<SupplierTM, String> colEmail;

    @FXML
    private TableColumn<SupplierTM, String> colSupplierID;

    @FXML
    private Label lblSupplierID;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtCompanyName;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplier_id"));
        colCompanyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try{
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }

    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
        loadTableData();
        loadNextSupplierID();
        
        txtCompanyName.setText("");
        txtContact.setText("");
        txtEmail.setText("");

        
        btnSaveSupplier.setDisable(false);
        btnDeleteSupplier.setDisable(true);
        btnUpdateSupplier.setDisable(true);
    }

    private void loadTableData() {
        tblSupplier.getItems().clear();
        try{
            ArrayList<SupplierDTO> supplierDTOS = supplierBO.getAll();
            for (SupplierDTO supplierDTO : supplierDTOS) {
                tblSupplier.getItems().add(new SupplierTM(supplierDTO.getSupplier_id(), supplierDTO.getName(), supplierDTO.getContact(), supplierDTO.getEmail()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load supplier id").show();
        }
    }

    private void loadNextSupplierID() throws SQLException, ClassNotFoundException {
        String supID = supplierBO.generateNewID();
        lblSupplierID.setText(supID);
    }

    @FXML
    void deleteSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierID = lblSupplierID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {

            supplierBO.delete(supplierID);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Supplier deleted...!").show();

        }
    }

    @FXML
    void generateSupplierDetailsRepo(ActionEvent event) throws  ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Supplier.jrxml"
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
    void generateSupplyDetailsRepo(ActionEvent event) throws ClassNotFoundException {
        SupplierTM  supplierTM = tblSupplier.getSelectionModel().getSelectedItem();

        if (supplierTM == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/SupplyDetails.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("P_Date", LocalDate.now().toString());
            parameters.put("P_Supplier_id", supplierTM.getSupplier_id());

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
    void onClickedTable(MouseEvent event) {
        SupplierTM staffTM = tblSupplier.getSelectionModel().getSelectedItem();
        if (staffTM != null) {
            lblSupplierID.setText(staffTM.getSupplier_id());
            txtCompanyName.setText(staffTM.getName());
            txtContact.setText(staffTM.getContact());
            txtEmail.setText(staffTM.getEmail());

            btnSaveSupplier.setDisable(true);
            btnUpdateSupplier.setDisable(false);
            btnDeleteSupplier.setDisable(false);
        }
    }

    @FXML
    void saveSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierID = lblSupplierID.getText();
        String companyName = txtCompanyName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = companyName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else{
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidContact && isValidEmail){
            SupplierDTO supplierDTO = new SupplierDTO(supplierID, companyName, contact, email
            );

            supplierBO.save(supplierDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "supplier saved...!").show();

        }
    }

    @FXML
    void updateSupplier(ActionEvent event) throws SQLException, ClassNotFoundException {
        String supplierID = lblSupplierID.getText();
        String companyName = txtCompanyName.getText();
        String contact = txtContact.getText();
        String email = txtEmail.getText();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

        boolean isValidName = companyName.matches(namePattern);
        boolean isValidContact = contact.matches(phonePattern);
        boolean isValidEmail = email.matches(emailPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";


        if(!isValidName){
            txtCompanyName.setStyle(errorStyle);
        }else {
            txtCompanyName.setStyle(style);
        }
        if(!isValidContact){
            txtContact.setStyle(errorStyle);
        }else {
            txtContact.setStyle(style);
        }
        if(!isValidEmail){
            txtEmail.setStyle(errorStyle);
        }else{
            txtEmail.setStyle(style);
        }

        if(isValidName && isValidContact && isValidEmail){
            SupplierDTO supplierDTO = new SupplierDTO(supplierID, companyName, contact, email);

            supplierBO.update(supplierDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "supplier updated...!").show();

        }

    }

}
