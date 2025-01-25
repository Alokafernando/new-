package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.SupplierBO;
import com.business.project.project03.model.SupplierDTO;
import com.business.project.project03.view.tdm.SupplierTM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    void deleteSupplier(ActionEvent event) {

    }

    @FXML
    void generateSupplierDetailsRepo(ActionEvent event) {

    }

    @FXML
    void generateSupplyDetailsRepo(ActionEvent event) {

    }

    @FXML
    void onClickedTable(MouseEvent event) {

    }

    @FXML
    void saveSupplier(ActionEvent event) {

    }

    @FXML
    void updateSupplier(ActionEvent event) {

    }

}
