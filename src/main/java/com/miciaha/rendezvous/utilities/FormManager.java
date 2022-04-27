package com.miciaha.rendezvous.utilities;

import com.miciaha.rendezvous.SchedulingApplication;
import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.Customer;
import com.miciaha.rendezvous.interfaces.FormEditor;
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

    public static class AppointmentForm extends Form{

        public AppointmentForm(Button callBtn){
            this.winSizeX = 410;
            this.winSizeY = 450;
            this.winTitle = "Appointment Form";
            this.formFile = "appointment-view.fxml";
            this.callBtn = callBtn;
        }
    }

    public static class CreateAppointmentForm extends AppointmentForm{

        public CreateAppointmentForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Appointment Form";
            openForm();
        }
    }

    public static class EditAppointmentForm extends AppointmentForm{


        public EditAppointmentForm(Button callBtn, Appointment editAppointment) {
            super(callBtn);
            this.winTitle = "Edit Appointment Form";

            openForm();
            FormEditor<Appointment> controller = loadedForm.getController();
            controller.initData(editAppointment);
        }
    }

    public static class CustomerForm extends Form{

        public CustomerForm(Button callBtn){
            this.winSizeX = 330;
            this.winSizeY = 580;
            this.winTitle = "Customer Form";
            this.formFile = "customer-view.fxml";
            this.callBtn = callBtn;
        }
    }

    public static class CreateCustomerForm extends CustomerForm{

        public CreateCustomerForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Customer Form";
            openForm();
        }
    }

    public static class EditCustomerForm extends CustomerForm{


        public EditCustomerForm(Button callBtn, Customer editCustomer) {
            super(callBtn);
            this.winTitle = "Edit Customer Form";

            openForm();
            FormEditor<Customer> controller = loadedForm.getController();
            controller.initData(editCustomer);
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
