package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.persistence.UserDbManager;
import com.miciaha.rendezvous.utilities.ScreenController;
import com.miciaha.rendezvous.utilities.fields.FieldTracker;
import com.miciaha.rendezvous.utilities.fields.FieldType;
import com.miciaha.rendezvous.utilities.fields.FormField;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TimeZone;


/**
 * The LoginViewController manages the login view.
 * By using the JavaFX Initializable interface, the controller is able to set
 * the necessary table links and button managers.
 *
 * @author Miciaha Ivey
 */
public class LoginViewController implements Initializable {

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

    @FXML
    public Label errorLoginLabel;

    @FXML
    public Label localeLabel;

    @FXML
    public Text usernameText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            setLocale();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setFields();
    }

    private void setLocale() throws IOException {
        LocaleHandler.setText(welcomeText.textProperty(),"greeting");
        LocaleHandler.setText(blurbText.textProperty(),"greeting_sub");
        LocaleHandler.setText(passwordText.textProperty(),"password");
        LocaleHandler.setText(usernameText.textProperty(),"username");
        LocaleHandler.setText(errorLoginLabel.textProperty(),"login_fail");
        LocaleHandler.setText(btnLogin.textProperty(),"login");
        localeLabel.textProperty().setValue(TimeZone.getDefault().getID());
    }

    private void setFields(){
        FieldTracker.Fields.clear();
        FieldTracker.Fields.add(new FormField(usernameField,usernameErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.add(new FormField(passwordField,passwordErrorLabel, FieldType.TEXT));
        FieldTracker.Fields.linkToButton(btnLogin);
        btnLogin.setOnAction(new LoginButtonHandler());
    }

    private class LoginButtonHandler implements EventHandler<ActionEvent>{
        @Override
        public void handle(ActionEvent event){
            String user = usernameField.textProperty().getValue();
            String password = passwordField.textProperty().getValue();
            boolean matchConfirmed;

            try {
                matchConfirmed = UserDbManager.Validator.login(user,password);
            } catch (SQLException e) {
                matchConfirmed = false;
                e.printStackTrace();
            }

            if(matchConfirmed){
                ScreenController.activate("mainScreen", 1175, 900 );

            } else{
                errorLoginLabel.visibleProperty().setValue(true);
            }
        }
    }
}