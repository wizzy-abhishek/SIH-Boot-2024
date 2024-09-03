package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Emergency {

    @Id
    @Column(length = 20)
    private String eBedId;

    private boolean isAllotted;

    @ManyToOne(fetch = FetchType.EAGER) // Keeping as ManyToOne, as a patient can be involved in multiple emergencies over time
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_aadhar", unique = true) // Ensure uniqueness in the database
    private Patient patient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "emergency_doctor",
            joinColumns = @JoinColumn(name = "emergency_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    public Emergency() {
        this.doctors = new ArrayList<>();
    }

    // Getters and Setters
    public String geteBedId() {
        return eBedId;
    }

    public void seteBedId(String eBedId) {
        this.eBedId = eBedId;
    }

    public boolean isAllotted() {
        return isAllotted;
    }

    public void setAllotted(boolean allotted) {
        isAllotted = allotted;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
