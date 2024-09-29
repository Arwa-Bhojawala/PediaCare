package com.cvac.springcvac.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "PatientInformation")
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String patientInfoId;

    @OneToOne
    @JoinColumn(name = "patient", unique = true)
    private Patient patient;

    private String name;

    private Integer age;

    private String bloodGroup;

    private String bloodPressure;

    private String weight;

    private String height;

    private String notes;

    public String getPatientInfoId() {
        return patientInfoId;
    }

    public void setPatientInfoId(String patientInfoId) {
        this.patientInfoId = patientInfoId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
