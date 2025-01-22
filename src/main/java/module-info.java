module com.business.project.project03 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires net.sf.jasperreports.core;

    opens com.business.project.project03.view.tdm to javafx.base;

    opens com.business.project.project03.controller to javafx.fxml;
    exports com.business.project.project03;
    exports com.business.project.project03.view.tdm;







}
