package lk.ijse.pos.leyard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class appInitializer extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Auto Lanka (PVT) LTD");

        Image image = new Image(getClass().getResourceAsStream("/image/car-logo-illustration_910054-55266.jpg"));
        stage.getIcons().add(image);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}