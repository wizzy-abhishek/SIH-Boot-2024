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
public class Department {

    @Id
    @Column(length = 35)
    private String name;

    @OneToMany(mappedBy = "dept", fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "department_patient",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_aadhar")
    )
    private List<Patient> patients;

    @ManyToMany(mappedBy = "departmentList", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Department() {
        this.appointments = new ArrayList<>(5);
        this.doctors = new ArrayList<>(5);
        this.patients = new ArrayList<>(5);
    }

    public Department(String name, List<Doctor> doctors, List<Patient> patients, List<Appointment> appointments) {
        this.name = name;
        this.doctors = doctors;
        this.patients = patients;
        this.appointments = appointments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
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
        return "\nDepartment{" +
                "\nname='" + name + '\'' +
                "\ndoctors=" + (doctors != null ? doctors.size() + " doctors" : "null") +
                "\npatients=" + (patients != null ? patients.size() + " patients" : "null") +
                "\nappointments=" + (appointments != null ? appointments.size() + " appointments" : "null") +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) && Objects.equals(doctors, that.doctors) && Objects.equals(patients, that.patients) && Objects.equals(appointments, that.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, doctors, patients, appointments);
    }
}

