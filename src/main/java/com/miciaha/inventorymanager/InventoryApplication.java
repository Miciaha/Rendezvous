package com.miciaha.inventorymanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for starting the Inventory Management application. Creates the opening stage for the primary view.
 * Javadocs are located in the directory above src as a separate file in the zip folder.
 * FUTURE_ENHANCEMENT Replace the default toolbar with a custom one.
 *
 * @author Miciaha Ivey
 */
public class InventoryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InventoryApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1040, 410);

        scene.getStylesheets().add(String.valueOf(InventoryApplication.class.getResource("main.css")));
        Font.loadFont(String.valueOf(InventoryApplication.class.getResourceAsStream("Roboto-Medium.ttf")),14);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}