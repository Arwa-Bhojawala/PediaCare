package com.cvac.springcvac.utils;

import com.cvac.springcvac.models.Patient;
import com.cvac.springcvac.models.PatientInfo;

public class BaseUtils {
    public static PatientInfo defaultPatientInfo(Patient patient) {
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.setName("John Doe");
        patientInfo.setAge(20);
        patientInfo.setBloodGroup("A+");
        patientInfo.setBloodPressure("120/80 mmHg");
        patientInfo.setPatient(patient);
        patientInfo.setWeight("50 kgs");
        patientInfo.setHeight("125 cm");

        return patientInfo;
    }
}
