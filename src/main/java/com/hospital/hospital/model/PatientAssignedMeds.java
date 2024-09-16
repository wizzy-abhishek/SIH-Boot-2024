package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Component
@Scope("prototype")
public class PatientAssignedMeds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Surrogate key

    // Many-to-one relationship with Patient
    @ManyToOne
    @JoinColumn(name = "patient_aadhar", referencedColumnName = "aadharNumber")
    private Patient patient;

    // Many-to-one relationship with Medicines
    @ManyToOne
    @JoinColumn(name = "meds_id", referencedColumnName = "meds_name")
    private Medicines medicines;

    private Integer quantity;
    private LocalDateTime localDateTime;

    // Getters and Setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicines getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicines medicines) {
        this.medicines = medicines;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientAssignedMeds that = (PatientAssignedMeds) o;
        return Objects.equals(patient, that.patient) &&
                Objects.equals(medicines, that.medicines) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, medicines, quantity, localDateTime);
    }
}
