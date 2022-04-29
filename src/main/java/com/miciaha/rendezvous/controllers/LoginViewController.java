package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.SchedulingApplication;
import com.miciaha.rendezvous.persistence.UserDbManager;
import com.miciaha.rendezvous.utilities.LoginEvent;
import com.miciaha.rendezvous.utilities.ScreenController;
import com.miciaha.rendezvous.utilities.fields.FieldTracker;
import com.miciaha.rendezvous.utilities.fields.FieldType;
import com.miciaha.rendezvous.utilities.fields.FormField;
import com.miciaha.rendezvous.utilities.location.LocaleHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
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

    /**
     * The Btn login.
     */
    @FXML
    public Button btnLogin;

    /**
     * The Blurb text.
     */
    @FXML
    public Text blurbText;

    /**
     * The Welcome text.
     */
    @FXML
    public Text welcomeText;

    /**
     * The Title text.
     */
    @FXML
    public Text titleText;

    /**
     * The Username field.
     */
    @FXML
    public TextField usernameField;

    /**
     * The Password field.
     */
    @FXML
    public PasswordField passwordField;

    /**
     * The Password text.
     */
    @FXML
    public Text passwordText;

    /**
     * The Password error label.
     */
    @FXML
    public Label passwordErrorLabel;

    /**
     * The Username error label.
     */
    @FXML
    public Label usernameErrorLabel;

    /**
     * The Error login label.
     */
    @FXML
    public Label errorLoginLabel;

    /**
     * The Locale label.
     */
    @FXML
    public Label localeLabel;

    /**
     * The Username text.
     */
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
        LocaleHandler.setText(welcomeText.textProperty(), "greeting");
        LocaleHandler.setText(blurbText.textProperty(), "greeting_sub");
        LocaleHandler.setText(passwordText.textProperty(), "password");
        LocaleHandler.setText(usernameText.textProperty(), "username");
        LocaleHandler.setText(errorLoginLabel.textProperty(), "login_fail");
        LocaleHandler.setText(btnLogin.textProperty(), "login");
        localeLabel.textProperty().setValue(TimeZone.getDefault().getID());
    }

    private void setFields() {
        FieldTracker.clear();
        FieldTracker.TextFields.add(new FormField<>(usernameField, usernameErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(passwordField, passwordErrorLabel, FieldType.TEXT));
        FieldTracker.linkToButton(btnLogin);
        btnLogin.setOnAction(new LoginButtonHandler());
    }

    private class LoginButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            String user = usernameField.textProperty().getValue();
            String password = passwordField.textProperty().getValue();
            boolean matchConfirmed;

            try {
                matchConfirmed = UserDbManager.Validator.login(user, password);
                ScreenController.addScreen("mainScreen", FXMLLoader.load(Objects.requireNonNull(SchedulingApplication.class.getResource("main-view.fxml"))));

            } catch (SQLException | IOException e) {
                matchConfirmed = false;
                e.printStackTrace();
            }

            LoginEvent.Log(user, matchConfirmed);

            if (matchConfirmed) {
                ScreenController.activate("mainScreen", 1265, 900);

            } else {
                errorLoginLabel.visibleProperty().setValue(true);
            }
        }
    }
}