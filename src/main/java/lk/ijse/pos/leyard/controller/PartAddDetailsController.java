package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.PartBO;
import lk.ijse.pos.leyard.bo.custom.PartDetailBO;
import lk.ijse.pos.leyard.bo.custom.VehicleBO;
import lk.ijse.pos.leyard.model.PartDTO;
import lk.ijse.pos.leyard.model.PartDetailDTO;
import lk.ijse.pos.leyard.model.VehicleDTO;
import lk.ijse.pos.leyard.view.tdm.PartDetailTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PartAddDetailsController implements Initializable {

    @FXML
    private TableColumn<PartDetailTM, Integer> ColQuantity;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> cmbPartId;

    @FXML
    private ComboBox<String> cmbVehicleID;

    @FXML
    private TableColumn<PartDetailTM, Button> colAction;

    @FXML
    private TableColumn<PartDetailTM, String> colModel;

    @FXML
    private TableColumn<PartDetailTM, Double> colTotal;

    @FXML
    private TableColumn<PartDetailTM, Double> colUnitPrice;

    @FXML
    private TableColumn<PartDetailTM, String> colVehicleID;

    @FXML
    private Label lblPartName;

    @FXML
    private Label lblQuantityOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblVehicleName;

    @FXML
    private TableView<PartDetailTM> tblPartDetails;

    @FXML
    private HBox txtAddQty;

    @FXML
    private TextField txtAddedQuantity;

    PartDetailBO partDetailBO = (PartDetailBO) BOFactory.getInstance().getBO(BOFactory.BOType.PART_DETAIL);
    PartBO partBO = (PartBO) BOFactory.getInstance().getBO(BOFactory.BOType.PART);
    VehicleBO vehicleBO = (VehicleBO) BOFactory.getInstance().getBO(BOFactory.BOType.VEHICLE);

    private final ObservableList<PartDetailTM> cartTMS = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colVehicleID.setCellValueFactory(new PropertyValueFactory<>("partId"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("partName"));
        ColQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQuantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeButton"));

        try{
            refreshPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "cannot load table").show();
        }
    }

    private void refreshPage() throws SQLException, ClassNotFoundException {
        loadPardIds();
        loadVehicleIDs();

        cmbPartId.getSelectionModel().clearSelection();
        cmbVehicleID.getSelectionModel().clearSelection();
        lblVehicleName.setText("");
        lblPartName.setText("");
        lblQuantityOnHand.setText("");
        lblUnitPrice.setText("");
        txtAddedQuantity.setText("");

        cartTMS.clear();
        tblPartDetails.refresh();
    }

    private void loadVehicleIDs() throws SQLException, ClassNotFoundException {
        ArrayList<String> vehicleIDs = vehicleBO.getAllVehicleIDs();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(vehicleIDs);
        cmbVehicleID.setItems(observableList);
    }

    private void loadPardIds() throws SQLException, ClassNotFoundException {
        ArrayList<String> partIDs = partBO.getAllPartIds();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll(partIDs);
        cmbPartId.setItems(observableList);
    }


    @FXML
    void AddtoCartOnAction(ActionEvent event) {
        String selectedPardId = cmbPartId.getSelectionModel().getSelectedItem();
        if (selectedPardId == null) {
            new Alert(Alert.AlertType.WARNING, "please select part Id!").show();
        }

        String selectedVehicleId = cmbVehicleID.getSelectionModel().getSelectedItem();
        if (selectedVehicleId == null) {
            new Alert(Alert.AlertType.WARNING, "please select vehicle Id!").show();
        }

        String QuantityOnHand = txtAddedQuantity.getText();
        String addQuantity = txtAddedQuantity.getText();

        String qtyPattern = "^[0-9]+$";

        if(!addQuantity.matches(qtyPattern)) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid quantity!").show();
            return;
        }

        String partName = lblPartName.getText();
        int Quantity = Integer.parseInt(lblQuantityOnHand.getText());
        int addedQuantity = Integer.parseInt(txtAddedQuantity.getText());

        txtAddedQuantity.setText("");

        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = unitPrice * addedQuantity;

        for (PartDetailTM partDetailCartTM : cartTMS) {
            if (partDetailCartTM.getPartId().equals(selectedPardId)) {
//                int qty = partDetailCartTM.getCartQuantity() + Quantity;
//                partDetailCartTM.setCartQuantity(qty);
//                partDetailCartTM.setTotalPrice(unitPrice * qty);

                tblPartDetails.refresh();
                return;
            }
        }
        Button btn = new Button("remove");

        PartDetailTM partDetailCartTM = new PartDetailTM(
                selectedPardId,
                selectedVehicleId,
                addedQuantity,
                unitPrice,
                total,
                btn
        );

        btn.setOnAction(actionEvent -> {
            cartTMS.remove(partDetailCartTM);
            tblPartDetails.refresh();
        });

        cartTMS.add(partDetailCartTM);
        tblPartDetails.setItems(cartTMS);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(tblPartDetails.getItems().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please add parts to cart..!").show();
            return;
        }
        if (cmbVehicleID.getItems().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select vehicle Id!").show();
            return;
        }
        String selectedPardId = cmbPartId.getSelectionModel().getSelectedItem();
        String selectedVehicleId = cmbVehicleID.getSelectionModel().getSelectedItem();

        ArrayList<PartDetailDTO> partDetailDTOS = new ArrayList<>();

        for (PartDetailTM partDetailCartTM : cartTMS) {
            PartDetailDTO partDetailDTO = new PartDetailDTO(
                    partDetailCartTM.getPartId(),
                    selectedVehicleId,
                    partDetailCartTM.getCartQuantity(),
                    partDetailCartTM.getTotalPrice()


            );
            partDetailDTOS.add(partDetailDTO);
        }

        boolean isSaved = partDetailBO.placeOrder(partDetailDTOS);
        if(isSaved){
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "saved..!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, " fail..!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshPage();
    }

    @FXML
    void cmbPartIdOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedPartId = cmbPartId.getSelectionModel().getSelectedItem();
        PartDTO partDTO = partBO.findById(selectedPartId);
        if(partDTO != null){
            lblPartName.setText(partDTO.getName());
            lblQuantityOnHand.setText(String.valueOf(partDTO.getQuantity()));
            lblUnitPrice.setText(String.valueOf(partDTO.getPrice()));
        }
    }

    @FXML
    void cmbVheicleIDOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String selectedVehicleID = cmbVehicleID.getSelectionModel().getSelectedItem();
        VehicleDTO vehicleDTO = vehicleBO.findById(selectedVehicleID);
        if(vehicleDTO != null){
            lblVehicleName.setText(vehicleDTO.getModel());
        }
    }

}
