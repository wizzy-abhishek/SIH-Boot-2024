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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_aadhar")
    private Patient patient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "emergency_department",
            joinColumns = @JoinColumn(name = "emergency_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departmentList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "emergency_doctor",
            joinColumns = @JoinColumn(name = "emergency_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bed_id")
    private Bed bed;

    public Emergency() {
        this.doctors = new ArrayList<>(5);
        this.departmentList = new ArrayList<>(5);
    }

    public Emergency(Patient patient, List<Department> departmentList, List<Doctor> doctors, Bed bed) {
        this.patient = patient;
        this.departmentList = departmentList;
        this.doctors = doctors;
        this.bed = bed;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }

    @Override
    public String toString() {
        return "\nEmergency{" +
                "patient=" + patient +
                "\ndepartmentList=" + departmentList +
                "\ndoctors=" + doctors +
                "\nbed=" + bed +
                "}\n";
    }
}


