package com.cvac.springcvac.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Cookies")
public class Cookies {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cookie;

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
