package com.cvac.springcvac.models;

import com.cvac.springcvac.models.enums.VaccineType;
import jakarta.persistence.*;

@Entity
@Table(name = "VaccineRecord")
public class VaccineRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String vaccineId;

    private String vaccineName;

    private VaccineType vaccineType;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
