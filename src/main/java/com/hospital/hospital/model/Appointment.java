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
public class Appointment {

    @Id
    @Column(length = 60)
    private String id;

    private String date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_aadhar")
    private Patient patient ;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "appointment_department",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departmentList ;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "appointment_doctor",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctorList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "appointment_test",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<Test> tests;

    public Appointment() {

        this.departmentList = new ArrayList<>(5);
        this.doctorList = new ArrayList<>(5);
        this.tests = new ArrayList<>(5);

    }

    public Appointment(String id, String date, Patient patient, List<Department> departmentList, List<Doctor> doctorList, List<Test> tests) {
        this.id = id;
        this.date = date;
        this.patient = patient;
        this.departmentList = departmentList;
        this.doctorList = doctorList;
        this.tests = tests;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        return "\nAppointment{" +
                "id=" + id +
                "\ndate=" + date +
                "\npatient=" + (patient != null ? patient.getAadharNumber() : "null") +  // Avoid recursive call
                "\ndepartmentList=" + (departmentList != null ? departmentList.stream().map(Department::getName).toList() : "null") +
                "\ndoctorList=" + (doctorList != null ? doctorList.stream().map(Doctor::getName).toList() : "null") +
                "\ntests=" + (tests != null ? tests.stream().map(Test::getTestName).toList() : "null") +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(patient, that.patient) && Objects.equals(departmentList, that.departmentList) && Objects.equals(doctorList, that.doctorList) && Objects.equals(tests, that.tests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, patient, departmentList, doctorList, tests);
    }
}
