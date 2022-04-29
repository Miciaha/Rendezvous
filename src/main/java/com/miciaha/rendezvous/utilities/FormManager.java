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

    /**
     * The type Report form.
     */
    public static class ReportForm extends Form {

        /**
         * Instantiates a new Report form.
         *
         * @param callBtn the call btn
         */
        public ReportForm(Button callBtn) {
            this.winSizeX = 950;
            this.winSizeY = 670;
            this.winTitle = "Reports Dashboard";
            this.formFile = "reports-view.fxml";
            this.callBtn = callBtn;
            openForm();
        }
    }

    /**
     * The type Appointment form.
     */
    public static class AppointmentForm extends Form {

        /**
         * Instantiates a new Appointment form.
         *
         * @param callBtn the call btn
         */
        public AppointmentForm(Button callBtn) {
            this.winSizeX = 410;
            this.winSizeY = 450;
            this.winTitle = "Appointment Form";
            this.formFile = "appointment-view.fxml";
            this.callBtn = callBtn;
        }
    }

    /**
     * The type Create appointment form.
     */
    public static class CreateAppointmentForm extends AppointmentForm {

        /**
         * Instantiates a new Create appointment form.
         *
         * @param callBtn the call btn
         */
        public CreateAppointmentForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Appointment Form";
            openForm();
        }
    }

    /**
     * The type Edit appointment form.
     */
    public static class EditAppointmentForm extends AppointmentForm {


        /**
         * Instantiates a new Edit appointment form.
         *
         * @param callBtn         the call btn
         * @param editAppointment the edit appointment
         */
        public EditAppointmentForm(Button callBtn, Appointment editAppointment) {
            super(callBtn);
            this.winTitle = "Edit Appointment Form";

            openForm();
            FormEditor<Appointment> controller = loadedForm.getController();
            controller.initData(editAppointment);
        }
    }

    /**
     * The type Customer form.
     */
    public static class CustomerForm extends Form {

        /**
         * Instantiates a new Customer form.
         *
         * @param callBtn the call btn
         */
        public CustomerForm(Button callBtn) {
            this.winSizeX = 330;
            this.winSizeY = 540;
            this.winTitle = "Customer Form";
            this.formFile = "customer-view.fxml";
            this.callBtn = callBtn;
        }
    }

    /**
     * The type Create customer form.
     */
    public static class CreateCustomerForm extends CustomerForm {

        /**
         * Instantiates a new Create customer form.
         *
         * @param callBtn the call btn
         */
        public CreateCustomerForm(Button callBtn) {
            super(callBtn);
            this.winTitle = "Create Customer Form";
            openForm();
        }
    }

    /**
     * The type Edit customer form.
     */
    public static class EditCustomerForm extends CustomerForm {


        /**
         * Instantiates a new Edit customer form.
         *
         * @param callBtn      the call btn
         * @param editCustomer the edit customer
         */
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
        FieldTracker.clear();
        Stage btnStage = (Stage) button.getScene().getWindow();
        btnStage.close();
    }

}
