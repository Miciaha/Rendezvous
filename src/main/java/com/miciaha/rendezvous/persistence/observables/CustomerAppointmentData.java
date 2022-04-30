package com.miciaha.rendezvous.persistence.observables;

import com.miciaha.rendezvous.entities.reports.CustomerAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Customer appointment.
 */
public class CustomerAppointmentData {
    public static ObservableList<CustomerAppointment> customerAppointments = FXCollections.observableArrayList();

    public static ObservableList<CustomerAppointment> getCustomerAppointments(){
        setCustomerAppointments();
        return customerAppointments;
    }

    private static void setCustomerAppointments() {
        LinkedHashMap<String, Integer> repeatedTypes = new LinkedHashMap<>();

        List<String> newList = AppointmentData.appointmentList
                .stream()
                .map(x -> (x.getType() + ";" + x.getStart().getMonth().name()))
                .collect(Collectors.toList());

        newList.forEach(x -> {
            if (repeatedTypes.containsKey(x)) {
                repeatedTypes.put(x, repeatedTypes.get(x) + 1);
            } else {
                repeatedTypes.put(x, 1);
            }
        });

        ArrayList<String> combinedTypeMonthValue = new ArrayList<>(repeatedTypes.keySet());
        ArrayList<Integer> counts = new ArrayList<>(repeatedTypes.values());

        ArrayList<CustomerAppointment> tempAppointment = new ArrayList<>();

        for (String combinedValues : combinedTypeMonthValue) {
            int count = counts.get(combinedTypeMonthValue.indexOf(combinedValues));
            CustomerAppointment temp = new CustomerAppointment(combinedValues, count);
            tempAppointment.add(temp);
        }

        customerAppointments = FXCollections.observableArrayList(tempAppointment);
    }
}
