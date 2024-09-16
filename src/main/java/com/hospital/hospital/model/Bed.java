package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
@Entity
public class Bed {

    @Id
    @Column(length = 20)
    private String bedId;

    private boolean isAllotted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_aadhar")
    private Patient patient;

    public Bed() {
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
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

    @Override
    public String toString() {
        return "\nBed{" +
                "\nbedId='" + bedId + '\'' +
                "\nisAllotted=" + isAllotted +
                "\npatientAadhaar=" + (patient != null ? patient.getAadharNumber() : "null") +  // Avoid recursive call
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bed bed = (Bed) o;
        return isAllotted == bed.isAllotted && Objects.equals(bedId, bed.bedId) && Objects.equals(patient, bed.patient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bedId, isAllotted, patient);
    }
}

