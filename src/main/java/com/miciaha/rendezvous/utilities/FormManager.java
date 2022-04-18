package com.miciaha.rendezvous.utilities;

import com.miciaha.rendezvous.SchedulingApplication;
import com.miciaha.rendezvous.utilities.fields.FieldTracker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Form manager handles the creation and removal of view forms.
 * All logic required to open and close stages in addition to the primary stage is located in this class.
 */
public class FormManager {

    /**
     * The type Form.
     */
    public static class Form {
        /**
         * The Call btn.
         */
        protected Button callBtn;
        /**
         * The Win title.
         */
        protected String winTitle;
        /**
         * The Stylesheet.
         */
        protected String stylesheet;
        /**
         * The Form file.
         */
        protected String formFile;
        /**
         * The Win size x.
         */
        protected double winSizeX;
        /**
         * The Win size y.
         */
        protected double winSizeY;
        /**
         * The Loaded form.
         */
        protected FXMLLoader loadedForm;

        /**
         * Instantiates a new Form.
         */
        public Form() {
        }

        /**
         * Open form.
         */
        public void openForm() {
            Scene primaryStage = callBtn.getScene();
            this.loadedForm = new FXMLLoader(SchedulingApplication.class.getResource(formFile));
            try {
                Scene scene = new Scene(loadedForm.load(), winSizeX, winSizeY);
                scene.getStylesheets().add(String.valueOf(SchedulingApplication.class.getResource(stylesheet)));

                Stage form = new Stage();
                form.setTitle(winTitle);
                form.initModality(Modality.WINDOW_MODAL);
                form.initOwner(primaryStage.getWindow());
                form.setScene(scene);
                form.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close form.
     *
     * @param button the button
     */
    public static void closeForm(Button button) {
        FieldTracker.Fields.clear();
        Stage btnStage = (Stage) button.getScene().getWindow();
        btnStage.close();
    }

}
