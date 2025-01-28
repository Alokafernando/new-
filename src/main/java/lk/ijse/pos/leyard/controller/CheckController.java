package lk.ijse.pos.leyard.controller;

import lk.ijse.pos.leyard.bo.BOFactory;
import lk.ijse.pos.leyard.bo.custom.CheckBO;
import lk.ijse.pos.leyard.model.VehicleDTO;
import lk.ijse.pos.leyard.view.tdm.CheckTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckController implements Initializable {


    @FXML
    private TableColumn<CheckTM, String> colColor;

    @FXML
    private TableColumn<CheckTM, String> colID;

    @FXML
    private TableColumn<CheckTM, String> colModel;

    @FXML
    private TableColumn<CheckTM, String> colStatus;

    @FXML
    private TableColumn<CheckTM, Integer> colYear;

    @FXML
    private TableView<CheckTM> tblcheckVehicle;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtYear;

    private ObservableList<CheckTM> vehicleTMS;
    CheckBO checkBO = (CheckBO) BOFactory.getInstance().getBO(BOFactory.BOType.CHECK);
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory<>("vID"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("vModel"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("vYear"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("vType"));

        try {
            loadVehicleData();
            initializeFilters();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeFilters() {
        txtColor.setOnAction(event -> filterTableData());
        txtModel.setOnAction(event -> filterTableData());
        txtYear.setOnAction(event -> filterTableData());
    }

    private void filterTableData() {
        String color = txtColor.getText().toLowerCase();
        String model = txtModel.getText().toLowerCase();
        String year = txtYear.getText().toLowerCase();

        ObservableList<CheckTM> filteredList = FXCollections.observableArrayList();

        for (CheckTM vehicleTM : vehicleTMS) {
            boolean matches = true;

            if (!color.isEmpty() && !vehicleTM.getColor().toLowerCase().contains(color)) {
                matches = false;
            }
            if (!model.isEmpty() && !vehicleTM.getVModel().toLowerCase().contains(model)) {
                matches = false;
            }
            if (!year.isEmpty() && !String.valueOf(vehicleTM.getVYear()).contains(year)) {
                matches = false;
            }

            if (matches) {
                filteredList.add(vehicleTM);
            }
        }

        tblcheckVehicle.setItems(filteredList);

    }

    private void loadVehicleData() throws SQLException, ClassNotFoundException {
        vehicleTMS = FXCollections.observableArrayList();

        ArrayList<VehicleDTO> vehicleDTOS = checkBO.getData();

        for (VehicleDTO vehicleDTO : vehicleDTOS) {

            String status = vehicleDTO.getCurrent_status();

            if ("import".equalsIgnoreCase(status)) {
                status = "importing";
            } else if ("repair".equalsIgnoreCase(status)) {
                status = "repairing";
            } else if ("sale".equalsIgnoreCase(status)) {
                status = "reserved";
            } else {
                status = "export";
            }

            CheckTM vehicleTM = new CheckTM(
                    vehicleDTO.getVehicle_id(),
                    vehicleDTO.getModel(),
                    vehicleDTO.getYear(),
                    vehicleDTO.getColor(),
                    status
            );
            vehicleTMS.add(vehicleTM);
        }

        tblcheckVehicle.setItems(vehicleTMS);
    }

}
