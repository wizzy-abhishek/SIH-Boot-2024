package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Department {

    @Id
    @Column(length = 35)
    private String name;

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER)
    private List<Doctor> doctors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "department_patient",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_aadhar")
    )
    private List<Patient> patients;

    @ManyToMany(mappedBy = "departmentList", fetch = FetchType.EAGER)
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
}

