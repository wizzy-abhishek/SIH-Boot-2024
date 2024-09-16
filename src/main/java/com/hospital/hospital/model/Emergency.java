package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Scope("prototype")
@Entity
public class Emergency {

    @Id
    @Column(length = 20)
    private String eBedId;

    private boolean isAllotted;

    @ManyToOne(fetch = FetchType.LAZY) // Keeping as ManyToOne, as a patient can be involved in multiple emergencies over time
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_aadhar", unique = true) // Ensure uniqueness in the database
    private Patient patient;

    @ManyToMany(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emergency emergency = (Emergency) o;
        return isAllotted == emergency.isAllotted && Objects.equals(eBedId, emergency.eBedId) && Objects.equals(patient, emergency.patient) && Objects.equals(doctors, emergency.doctors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eBedId, isAllotted, patient, doctors);
    }
}
