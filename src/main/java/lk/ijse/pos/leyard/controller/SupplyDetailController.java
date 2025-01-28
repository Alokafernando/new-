package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.PartBO;
import lk.ijse.pos.leyard.bo.custom.SupplierBO;
import lk.ijse.pos.leyard.bo.custom.SupplyDetailBO;
import lk.ijse.pos.leyard.model.PartDTO;
import lk.ijse.pos.leyard.model.SupplierDTO;
import lk.ijse.pos.leyard.model.SupplierDetailDTO;
import lk.ijse.pos.leyard.view.tdm.SupplyDetailTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SupplyDetailController implements Initializable {


    @FXML
    private Button btnCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbPartID;

    @FXML
    private ComboBox<String> cmbSupplierID;

    @FXML
    private TableColumn<SupplyDetailTM, Button> colAction;

    @FXML
    private TableColumn<SupplyDetailTM, String> colName;

    @FXML
    private TableColumn<SupplyDetailTM, String> colPartID;

    @FXML
    private TableColumn<SupplyDetailTM, Integer> colQuantity;

    @FXML
    private TableColumn<SupplyDetailTM, Double> colTotal;

    @FXML
    private TableColumn<SupplyDetailTM, Double> colUnitPrice;

    @FXML
    private Label lblPartName;

    @FXML
    private Label lblQOnHand;

    @FXML
    private TextField lblQty;

    @FXML
    private Label lblSupplierName;

    @FXML
    private Label lblSupplyDate;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private TableView<SupplyDetailTM> tblSupplyDetails;

    SupplyDetailBO supplyDetailBO = (SupplyDetailBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLY_DETAIL);
    SupplierBO supplierBO = (SupplierBO) BOFactory.getInstance().getBO(BOFactory.BOType.SUPPLIER);
    PartBO partBO = (PartBO) BOFactory.getInstance().getBO(BOFactory.BOType.PART);

    private final ObservableList<SupplyDetailTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPartID.setCellValueFactory(new PropertyValueFactory<>("partId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("partName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

        try {
            refreshPage();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        lblSupplyDate.setText(LocalDate.now().toString());

        loadSupplierID();
        loadPartID();

        cmbPartID.getSelectionModel().clearSelection();
        cmbSupplierID.getSelectionModel().clearSelection();
        lblSupplierName.setText("");
        lblQOnHand.setText("");
        lblUnitPrice.setText("");
        lblQty.setText("");

        cartTMS.clear();
        tblSupplyDetails.refresh();
    }

    private void loadPartID() throws SQLException, ClassNotFoundException {
        ArrayList<String> partIDs = partBO.getAllPartIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(partIDs);
        cmbPartID.setItems(observableList);
    }

    private void loadSupplierID() throws SQLException, ClassNotFoundException {
        ArrayList<String> supplierIDs = supplierBO.getSupplierIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(supplierIDs);
        cmbSupplierID.setItems(observableList);
    }

    @FXML
    void actionOnPlaceorderButton(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(tblSupplyDetails.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please add parts to cart..!").show();
            return;
        }
        if(cmbSupplierID.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please select supplier for place supply..!").show();
            return;
        }
        String selectedSupplierID = cmbSupplierID.getSelectionModel().getSelectedItem();
        String supplyDate = Date.valueOf(LocalDate.now()).toString();
        String partId = cmbPartID.getSelectionModel().getSelectedItem();

        ArrayList<SupplierDetailDTO> supplierDetailDTOS = new ArrayList<>();

        for(SupplyDetailTM supplyCartTM : cartTMS){
            SupplierDetailDTO supplierDetailDTO  = new SupplierDetailDTO(
                    selectedSupplierID,
                    supplyCartTM.getPartId(),
                    supplyDate,
                    supplyCartTM.getCartQuantity(),
                    supplyCartTM.getTotalPrice()
            );
            supplierDetailDTOS.add(supplierDetailDTO);
        }
        boolean isSaved = supplyDetailBO.placeOrder(supplierDetailDTOS);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "saved..!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, " fail..!").show();
        }
    }

    @FXML
    void cartButtonOnAction(ActionEvent event) {
        String selectedPardID = cmbPartID.getSelectionModel().getSelectedItem();

        if(selectedPardID == null){
            new Alert(Alert.AlertType.WARNING, "Please select part ID!").show();
        }

        String selectedSupplierID = cmbSupplierID.getSelectionModel().getSelectedItem();

        if(selectedSupplierID == null){
            new Alert(Alert.AlertType.WARNING, "Please select supplier ID!").show();
        }

        String supplyDate = Date.valueOf(LocalDate.now()).toString();
        String QuantityOnHand = lblQOnHand.getText();
        String addQuantity = lblQty.getText();

        String qtyPattern = "^[0-9]+$";

        if(!addQuantity.matches(qtyPattern)){
            new Alert(Alert.AlertType.ERROR, "Invalid Quantity!").show();
            return;
        }

        String partName = lblPartName.getText();
        int quantity = Integer.parseInt(lblQOnHand.getText());
        int addedQuantity = Integer.parseInt(addQuantity);


        lblQty.setText("");

        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * addedQuantity;

        for (SupplyDetailTM cartQty : cartTMS) {
            if(cartQty.getPartId().equals(selectedPardID)){

//                int qty = cartQty.getCartQuantity() + quantity;
//                cartQty.setCartQuantity(qty);
//                cartQty.setTotalPrice(unitPrice * qty);

                tblSupplyDetails.refresh();
                return;
            }
        }

        Button btn = new Button("remove");

        SupplyDetailTM newCartTM = new SupplyDetailTM(
                selectedPardID,
                partName,
                addedQuantity,
                unitPrice,
                total,
                btn
        );

        btn.setOnAction(actionEvent -> {
            cartTMS.remove(newCartTM);
            tblSupplyDetails.refresh();
        });

        cartTMS.add(newCartTM);
        tblSupplyDetails.setItems(cartTMS);

    }

    @FXML
    void cmbPartIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedPartId = cmbPartID.getSelectionModel().getSelectedItem();
        PartDTO partDTO = partBO.findById(selectedPartId);
        if(partDTO != null){
            lblPartName.setText(partDTO.getName());
            lblQOnHand.setText(String.valueOf(partDTO.getQuantity()));
            lblUnitPrice.setText(String.valueOf(partDTO.getPrice()));
        }
    }

    @FXML
    void cmbSupplierIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedSupplierId = cmbSupplierID.getSelectionModel().getSelectedItem();
        SupplierDTO SupplierDTO = supplierBO.findById(selectedSupplierId);
        if (SupplierDTO != null) {
            lblSupplierName.setText(SupplierDTO.getName());
        }
    }

    @FXML
    void onResetButtonClicked(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

}
