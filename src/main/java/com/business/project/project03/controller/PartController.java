package com.business.project.project03.controller;

import com.business.project.project03.bo.BOFactory;
import com.business.project.project03.bo.custom.PartBO;
import com.business.project.project03.db.DBConnection;
import com.business.project.project03.model.PartDTO;
import com.business.project.project03.view.tdm.PartTM;
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

public class PartController implements Initializable {

    @FXML
    private Button btnDeletePart;

    @FXML
    private Button btnSavePart;

    @FXML
    private Button btnUpdatePart;

    @FXML
    private Button btnpartRep;

    @FXML
    private TableColumn<PartTM, String> colPartID;

    @FXML
    private TableColumn<PartTM, String> colPartName;

    @FXML
    private TableColumn<PartTM, Integer> colQuantity;

    @FXML
    private TableColumn<PartTM, Double> colUnitPrice;

    @FXML
    private Label lblPartID;

    @FXML
    private TableView<PartTM> tblPart;

    @FXML
    private TextField txtPartName;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    PartBO partBO = (PartBO) BOFactory.getInstance().getBO(BOFactory.BOType.PART);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPartID.setCellValueFactory(new PropertyValueFactory<>("part_id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            refeshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load part id").show();

        }
    }

    private void refeshPage() throws SQLException, ClassNotFoundException {
       // lblPartID.setText(partModel.getNextPartID());
        loadNextPartID();
        loadTableData();
        
        txtPartName.setText("");
        txtQuantity.setText("");
        txtUnitPrice.setText("");

        btnSavePart.setDisable(false);
        btnDeletePart.setDisable(true);
        btnUpdatePart.setDisable(true);
    }

    private void loadTableData() {
        tblPart.getItems().clear();
        try{
            ArrayList<PartDTO> partDTOS = partBO.getAll();
            for (PartDTO partDTO : partDTOS) {
                tblPart.getItems().add(new PartTM(partDTO.getPart_id(), partDTO.getName(), partDTO.getPrice(), partDTO.getQuantity()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadNextPartID() throws SQLException, ClassNotFoundException {
        String partId = partBO.generateNewID();
        lblPartID.setText(partId);
    }

    @FXML
    void deletePart(ActionEvent event) throws SQLException, ClassNotFoundException {
        String partID = lblPartID.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if (optionalButtonType.get() == ButtonType.YES) {

            partBO.delete(partID);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Part deleted").show();

        }
    }

    @FXML
    void generatePartRepo(ActionEvent event) throws ClassNotFoundException {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Reports/Part.jrxml"
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
    void onClickedTable(MouseEvent event) {
        PartTM partTM = tblPart.getSelectionModel().getSelectedItem();
        if (partTM != null) {
            lblPartID.setText(partTM.getPart_id());
            txtPartName.setText(partTM.getName());
            txtQuantity.setText(String.valueOf(partTM.getQuantity()));
            txtUnitPrice.setText(String.valueOf(partTM.getPrice()));

            btnSavePart.setDisable(true);
            btnUpdatePart.setDisable(false);
            btnDeletePart.setDisable(false);
        }
    }

    @FXML
    void savePart(ActionEvent event) throws SQLException, ClassNotFoundException {
        String partID = lblPartID.getText();
        String partName = txtPartName.getText();
        String quantity = txtQuantity.getText();
        String unitPrice = txtUnitPrice.getText();

        String namePattern = "^[A-Za-z ]+$";
        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";
        String qtyPattern = "^([1-9][0-9]{0,3})$";


        boolean isValidaName = partName.matches(namePattern);
        boolean isValidPrice = unitPrice.matches(doubleValuesPattern);
        boolean isValidQuantity = quantity.matches(qtyPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidaName){
            txtPartName.setStyle(errorStyle);
        }else {
            txtPartName.setStyle(style);
        }
        if (!isValidQuantity){
            txtQuantity.setStyle(errorStyle);
        }else {
            txtQuantity.setStyle(style);
        }
        if (!isValidPrice){
            txtUnitPrice.setStyle(errorStyle);
        }else {
            txtUnitPrice.setStyle(style);
        }

        int qty = 0;
        double price = 0.0;

        try {
            qty = Integer.parseInt(quantity);
            price = Double.parseDouble(unitPrice);

            if (qty < 0 || price < 0) {
                new Alert(Alert.AlertType.WARNING, "Tax values cannot be negative.").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values for quatity and unit price.").show();
            return;
        }

        if(isValidaName && isValidPrice && isValidQuantity){
            PartDTO partDTO = new PartDTO(partID, partName, price, qty);

            partBO.save(partDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Part saved..!").show();

        }

    }

    @FXML
    void updatePart(ActionEvent event) throws SQLException, ClassNotFoundException {
        String partID = lblPartID.getText();
        String partName = txtPartName.getText();
        String quantity = txtQuantity.getText();
        String unitPrice = txtUnitPrice.getText();

        String namePattern = "^[A-Za-z ]+$";
        String doubleValuesPattern = "^\\d+(\\.\\d{1,2})?$";
        String qtyPattern = "^([1-9][0-9]{0,3})$";


        boolean isValidaName = partName.matches(namePattern);
        boolean isValidPrice = unitPrice.matches(doubleValuesPattern);
        boolean isValidQuantity = quantity.matches(qtyPattern);
        String errorStyle = "-fx-border-color: red; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";
        String style = "-fx-border-color:  #1e3799; -fx-border-width: 0 0 1 0; -fx-background-color: transparent;";

        if (!isValidaName){
            txtPartName.setStyle(errorStyle);
        }else {
            txtPartName.setStyle(style);
        }
        if (!isValidQuantity){
            txtQuantity.setStyle(errorStyle);
        }else {
            txtQuantity.setStyle(style);
        }
        if (!isValidPrice){
            txtUnitPrice.setStyle(errorStyle);
        }else {
            txtUnitPrice.setStyle(style);
        }

        int qty = 0;
        double price = 0.0;

        try {
            qty = Integer.parseInt(quantity);
            price = Double.parseDouble(unitPrice);

            if (qty < 0 || price < 0) {
                new Alert(Alert.AlertType.WARNING, "Tax values cannot be negative.").show();
                return;
            }
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.WARNING, "Please enter valid numeric values for quatity and unit price.").show();
            return;
        }

        if(isValidaName && isValidPrice && isValidQuantity){
            PartDTO partDTO = new PartDTO(partID, partName, price, qty);

            partBO.update(partDTO);
            refeshPage();
            new Alert(Alert.AlertType.INFORMATION, "Part updated..!").show();

        }

    }

}
