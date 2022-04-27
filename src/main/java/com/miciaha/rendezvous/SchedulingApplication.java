package com.miciaha.rendezvous;

import com.miciaha.rendezvous.utilities.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Main class for starting the Appointment Scheduling application. Creates the opening stage for the primary view.
 * Javadocs are located in the directory above src as a separate file in the zip folder.
 *
 * @author Miciaha Ivey
 */
public class SchedulingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulingApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1020, 725);

        ScreenController.setMainScene(scene);
        ScreenController.addScreen("mainScreen", FXMLLoader.load(Objects.requireNonNull(SchedulingApplication.class.getResource("main-view.fxml"))));

        stage.setTitle("Rendezvous");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}