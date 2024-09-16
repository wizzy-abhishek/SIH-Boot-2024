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
public class Doctor {
    @Id
    @Column(length = 10)
    private String id;

    @Column(length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department dept;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_aadhar")
    )
    private List<Patient> patients;

    @ManyToMany(mappedBy = "doctorList", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Doctor() {
        this.patients = new ArrayList<>(5);
        this.appointments = new ArrayList<>(5);
    }

    public Doctor(String id, String name, Department dept, List<Patient> patients, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.patients = patients;
        this.appointments = appointments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "\nDoctor{" +
                "id='" + id + '\'' +
                "\nname='" + name + '\'' +
                "\ndept=" + (dept != null ? dept.getName() : "null") +
                "\npatients=" + (patients != null ? patients.size() + " patients" : "null") +
                "\nappointments=" + (appointments != null ? appointments.size() + " appointments" : "null") +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id) && Objects.equals(name, doctor.name) && Objects.equals(dept, doctor.dept) && Objects.equals(patients, doctor.patients) && Objects.equals(appointments, doctor.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dept, patients, appointments);
    }
}

