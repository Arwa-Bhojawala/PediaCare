package com.cvac.springcvac.models;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "PendingVaccine")
public class PendingVaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String pendingVaccineId;

    private String vaccineName;

    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public String getPendingVaccineId() {
        return pendingVaccineId;
    }

    public void setPendingVaccineId(String pendingVaccineId) {
        this.pendingVaccineId = pendingVaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
