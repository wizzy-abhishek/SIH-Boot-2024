package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
public class Bed{

    @Id
    @Column(length = 20)
    private String bedId ;

    private boolean isAllotted ;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_aadhar")
    private Patient patient ;

    public Bed() {
    }

    public Bed(String bedId, boolean isAllotted, Patient patient) {
        this.bedId = bedId;
        this.isAllotted = isAllotted;
        this.patient = patient;
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
}

