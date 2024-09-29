package com.cvac.springcvac.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Patient")
public class Patient implements Serializable {
    @Id
    private String username;

    private String hashedPassword;

    @JsonIgnore
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    private PatientInfo patientInfo;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<HealthRecord> healthRecord;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private Set<VaccineRecord> vaccineRecord;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<PendingVaccine> pendingVaccines;

    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Cookies> validCookies;

    public List<Cookies> getValidCookies() {
        return validCookies;
    }

    public void setValidCookies(List<Cookies> validCookies) {
        this.validCookies = validCookies;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public List<HealthRecord> getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(List<HealthRecord> healthRecord) {
        this.healthRecord = healthRecord;
    }

    public Set<VaccineRecord> getVaccineRecord() {
        return vaccineRecord;
    }

    public void setVaccineRecord(Set<VaccineRecord> vaccineRecord) {
        this.vaccineRecord = vaccineRecord;
    }

    public List<PendingVaccine> getPendingVaccines() {
        return pendingVaccines;
    }

    public void setPendingVaccines(List<PendingVaccine> pendingVaccines) {
        this.pendingVaccines = pendingVaccines;
    }
}
