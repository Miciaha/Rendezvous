package com.miciaha.rendezvous.controllers;

import com.miciaha.rendezvous.entities.Appointment;
import com.miciaha.rendezvous.entities.CurrentUser;
import com.miciaha.rendezvous.interfaces.FormEditor;
import com.miciaha.rendezvous.persistence.observables.AppointmentData;
import com.miciaha.rendezvous.persistence.observables.ContactData;
import com.miciaha.rendezvous.persistence.observables.CustomerData;
import com.miciaha.rendezvous.utilities.Alerts;
import com.miciaha.rendezvous.utilities.FormManager;
import com.miciaha.rendezvous.utilities.fields.FieldTracker;
import com.miciaha.rendezvous.utilities.fields.FieldType;
import com.miciaha.rendezvous.utilities.fields.FormField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * The type Appointment view controller.
 */
public class AppointmentViewController implements Initializable, FormEditor<Appointment> {
    /**
     * The Updating appointment.
     */
    protected boolean updatingAppointment = false;
    /**
     * The Form appointment.
     */
    protected Appointment formAppointment;

    private static final ObservableList<String> startAppointment = AppointmentData.generateAppointmentTimes(8, 22, false);
    private static final ObservableList<String> endAppointment = AppointmentData.generateAppointmentTimes(8, 22, true);

    /**
     * The Time error label.
     */
    @FXML
    public Label timeErrorLabel;

    /**
     * The Contact error label.
     */
    @FXML
    public Label contactErrorLabel;

    /**
     * The Customer error label.
     */
    @FXML
    public Label customerErrorLabel;

    /**
     * The Title tf.
     */
    @FXML
    public TextField titleTF;

    /**
     * The Title error label.
     */
    @FXML
    public Label titleErrorLabel;

    /**
     * The Description tf.
     */
    @FXML
    public TextField descriptionTF;

    /**
     * The Description error label.
     */
    @FXML
    public Label descriptionErrorLabel;

    /**
     * The Location tf.
     */
    @FXML
    public TextField locationTF;

    /**
     * The Location error label.
     */
    @FXML
    public Label locationErrorLabel;

    /**
     * The Type tf.
     */
    @FXML
    public TextField typeTF;

    /**
     * The Type error label.
     */
    @FXML
    public Label typeErrorLabel;

    /**
     * The Date picker.
     */
    @FXML
    public DatePicker datePicker;

    /**
     * The Date error label.
     */
    @FXML
    public Label dateErrorLabel;

    /**
     * The Save button.
     */
    @FXML
    public Button saveButton;

    /**
     * The Cancel button.
     */
    @FXML
    public Button cancelButton;

    /**
     * The Begin time cmb.
     */
    @FXML
    public ComboBox<String> beginTimeCmb;

    /**
     * The End time cmb.
     */
    @FXML
    public ComboBox<String> endTimeCmb;

    /**
     * The Contact cmb.
     */
    @FXML
    public ComboBox<String> contactCmb;

    /**
     * The Customer cmb.
     */
    @FXML
    public ComboBox<String> customerCmb;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        endAppointment.add("10:00PM EST");
        beginTimeCmb.setItems(startAppointment);
        endTimeCmb.setItems(endAppointment);
        customerCmb.setItems(CustomerData.getAllCustomerNames());
        contactCmb.setItems(ContactData.getContactNamesList());
        saveButton.setOnAction(new SaveAppointmentButtonHandler());
        cancelButton.setOnAction(new CancelAppointmentFormButtonHandler());
        setFields();
    }

    @Override
    public void initData(Appointment appointment) {

        // Base appointment info
        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String type = appointment.getType();

        // Database entities
        String customer = appointment.getCustomer().getName();
        String contact = appointment.getContact().getName();

        // Time configuration
        LocalDate date = appointment.getStart().toLocalDate();
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[:mm]a");
        String startTime = appointment.getStart().toLocalTime().format(parser);
        String endTime = appointment.getEnd().toLocalTime().format(parser);

        titleTF.textProperty().setValue(title);
        descriptionTF.textProperty().setValue(description);
        locationTF.textProperty().setValue(location);
        typeTF.textProperty().setValue(type);

        customerCmb.setValue(customer);
        contactCmb.setValue(contact);

        datePicker.setValue(date);
        beginTimeCmb.setValue(startTime);
        endTimeCmb.setValue(endTime);

        FieldTracker.editFields();

        updatingAppointment = true;
        formAppointment = appointment;
    }

    private void setFields() {
        FieldTracker.clear();

        FieldTracker.TextFields.add(new FormField<>(titleTF, titleErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(descriptionTF, descriptionErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(locationTF, locationErrorLabel, FieldType.TEXT));
        FieldTracker.TextFields.add(new FormField<>(typeTF, typeErrorLabel, FieldType.TEXT));
        FieldTracker.ComboBoxes.add(new FormField<>(customerCmb, customerErrorLabel, FieldType.TEXT));
        FieldTracker.ComboBoxes.add(new FormField<>(contactCmb, contactErrorLabel, FieldType.TEXT));
        FieldTracker.ComboBoxes.add(new FormField<>(beginTimeCmb, timeErrorLabel, FieldType.TEXT));
        FieldTracker.ComboBoxes.add(new FormField<>(endTimeCmb, timeErrorLabel, FieldType.TEXT));
        FieldTracker.DateTimeFields.add(new FormField<>(datePicker, dateErrorLabel, FieldType.TEXT));

        FieldTracker.linkToButton(saveButton);

        // Limits available appointment days to weekdays and does not allow the user to select days before the current day.
        datePicker.setDayCellFactory(datePicker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                int dayOfWeek = date.getDayOfWeek().getValue();

                setDisable(empty || date.compareTo(today) < 0 || dayOfWeek == 6 || dayOfWeek == 7);
            }
        });
    }

    private boolean checkAppointmentTimeLogic() {
        int selectedBeginTime = beginTimeCmb.getSelectionModel().getSelectedIndex();
        int selectedEndTime = endTimeCmb.getSelectionModel().getSelectedIndex();

        if (selectedBeginTime > selectedEndTime) {
            new Alerts.CustomAlert.WarningAlert("Appointment Time Error",
                    "Appointment cannot end before it begins. Please select proper end time.");
            return false;
        } else {
            return true;
        }
    }

    private LocalDateTime createLocalDateTime(String timeString) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("h[:mm]a");
        LocalDate date = datePicker.getValue();
        LocalTime time = LocalTime.parse(timeString, parser);

        return LocalDateTime.of(date, time);
    }

    private boolean createAppointment(String title, String description, LocalDateTime begin, LocalDateTime end,
                                      String location, String type, String customer, String contact) {

        int customerID = CustomerData.getCustomerID(customer);
        int contactID = ContactData.getContactID(contact);

        Appointment newAppointment = new Appointment(title, description, location, type, begin, end, CurrentUser.getID(), customerID, contactID);
        return AppointmentData.addAppointment(newAppointment);
    }

    private boolean updateAppointment(String title, String description, LocalDateTime begin, LocalDateTime end,
                                      String location, String type, String customer, String contact) {

        formAppointment.setTitle(title);
        formAppointment.setDescription(description);
        formAppointment.setStart(begin);
        formAppointment.setEnd(end);
        formAppointment.setLocation(location);
        formAppointment.setType(type);
        formAppointment.setCustomer(CustomerData.getCustomer(customer));
        formAppointment.setContact(ContactData.getContact(contact));

        return AppointmentData.updateAppointment(formAppointment);
    }

    private class SaveAppointmentButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            String title = titleTF.textProperty().getValue();
            String description = descriptionTF.textProperty().getValue();
            String location = locationTF.textProperty().getValue();
            String type = typeTF.textProperty().getValue();
            String customerName = customerCmb.getValue();
            String contactName = contactCmb.getValue();
            LocalDateTime beginDateTime = createLocalDateTime(beginTimeCmb.getValue());
            LocalDateTime endDateTime = createLocalDateTime(endTimeCmb.getValue());
            boolean taskSuccess;

            if (checkAppointmentTimeLogic()) {
                if (updatingAppointment) {
                    taskSuccess = updateAppointment(title, description, beginDateTime, endDateTime, location, type, customerName, contactName);
                } else {
                    taskSuccess = createAppointment(title, description, beginDateTime, endDateTime, location, type, customerName, contactName);
                }

                if (taskSuccess) {
                    FormManager.closeForm(saveButton);
                }
            }
        }
    }

    private class CancelAppointmentFormButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {
            FormManager.closeForm(cancelButton);
        }
    }
}
