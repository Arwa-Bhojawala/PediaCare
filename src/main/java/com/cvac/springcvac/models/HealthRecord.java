package com.cvac.springcvac.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "HealthRecord")
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String appointmentId;

    private String date;

    private String doctorName;

    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
