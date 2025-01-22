module com.business.project.project03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.business.project.project03 to javafx.fxml;
    exports com.business.project.project03;
}