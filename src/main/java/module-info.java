module com.business.project.project03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires lombok;

    opens lk.ijse.pos.leyard.view.tdm to javafx.base;

    opens lk.ijse.pos.leyard.controller to javafx.fxml;
    exports lk.ijse.pos.leyard;
    exports lk.ijse.pos.leyard.view.tdm;







}
