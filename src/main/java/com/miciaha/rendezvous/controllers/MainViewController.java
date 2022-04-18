package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.utilities.fields.FieldTracker;
import com.miciaha.rendezvous.utilities.fields.FieldType;
import com.miciaha.rendezvous.utilities.fields.FormField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * The InventoryViewController manages the main application view.
 * By using the JavaFX Initializable interface, the controller is able to set
 * the necessary table links and button managers.
 *
 * @author Miciaha Ivey
 */
public class MainViewController implements Initializable {

    @FXML
    public Button btnLogin;

    @FXML
    public Text blurbText;

    @FXML
    public Text welcomeText;

    @FXML
    public Text titleText;

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Text passwordText;

    @FXML
    public Label passwordErrorLabel;

    @FXML
    public Label usernameErrorLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FieldTracker.Fields.clear();
        FieldTracker.Fields.add(new FormField(usernameField,usernameErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(passwordField,passwordErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.linkToButton(btnLogin);
        btnLogin.setOnAction(new LoginButtonHandler());
    }

    private class LoginButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){



//            new Alerts.CustomAlert.WarningAlert("Field Values",
//                        "Username: " + usernameField.getText() + "\nPassword: " + passwordField.getText());

        }
    }

}