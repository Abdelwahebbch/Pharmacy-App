package com.pharmacy.Validation;

import com.pharmacy.Model.Medication;
import com.pharmacy.Model.Prescription;

import javafx.collections.ObservableList;

public abstract class Validators {

    public static boolean prespectionSearch(ObservableList<Prescription> prescriptionList, String id) {

        for (Prescription p : prescriptionList) {
            if (p.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }
    public static boolean MedicationSearch(ObservableList<Medication> medicationList, String name) {

        for (Medication m : medicationList) {
            if (m.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }
}
