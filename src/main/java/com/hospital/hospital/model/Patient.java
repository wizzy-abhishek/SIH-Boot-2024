package com.hospital.hospital.model;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Scope("prototype")
@Entity
public class Patient {

    @Id
    @Column(length = 12)
    private String aadharNumber;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 10)
    private String mobile;

    @Column(nullable = false , length = 40)
    private String date_of_Birth ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_department",
            joinColumns = @JoinColumn(name = "patient_aadhar"),
            inverseJoinColumns = @JoinColumn(name = "department_id")
    )
    private List<Department> departmentList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_doctor",
            joinColumns = @JoinColumn(name = "patient_aadhar"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_meds",
            joinColumns = @JoinColumn(name = "patient_aadhar"),
            inverseJoinColumns = @JoinColumn(name = "meds_id")
    )
    private List<Medicines> meds;

    private String dates;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Bed> beds;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "patient_appointment",
            joinColumns = @JoinColumn(name = "patient_aadhar"),
            inverseJoinColumns = @JoinColumn(name = "appointment_id")
    )
    private List<Appointment> appointments;

    @OneToOne(mappedBy = "patient", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Emergency emergency;

    public Patient() {
        this.departmentList = new ArrayList<>(5);
        this.doctors = new ArrayList<>(5);
        this.meds = new ArrayList<>(5);
        this.dates = String.valueOf(new ArrayList<>(5));
        this.beds = new ArrayList<>(5);
        this.appointments = new ArrayList<>(5);
    }

    public Patient(String aadharNumber, String name, String mobile, Date date_of_Birth, List<Department> departmentList, List<Doctor> doctors, List<Medicines> meds, List<Date> dates, List<Bed> beds, List<Appointment> appointments, Emergency emergency) {
        this.aadharNumber = aadharNumber;
        this.name = name;
        this.mobile = mobile;
        this.date_of_Birth = String.valueOf(date_of_Birth);
        this.departmentList = departmentList;
        this.doctors = doctors;
        this.meds = meds;
        this.dates = dates.toString();
        this.beds = beds;
        this.appointments = appointments;
        this.emergency = emergency;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDate_of_Birth() {
        return date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        this.date_of_Birth = date_of_Birth;
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

    public List<Medicines> getMeds() {
        return meds;
    }

    public void setMeds(List<Medicines> meds) {
        this.meds = meds;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Emergency getEmergency() {
        return emergency;
    }

    public void setEmergency(Emergency emergency) {
        this.emergency = emergency;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "\naadhaarNumber='" + aadharNumber + '\'' +
                "\nname='" + name + '\'' +
                "\nmobile='" + mobile + '\'' +
                "\ndate_of_Birth=" + date_of_Birth +
                "\ndepartments=" + (departmentList != null ? departmentList.stream().map(Department::getName).toList() : "null") +
                "\ndoctors=" + (doctors != null ? doctors.stream().map(doctor -> "Doctor{name='" + doctor.getName() + "', id=" + doctor.getId() + "}").toList() : "null") +
                "\nmeds=" + (meds != null ? meds.stream().map(medicines -> "Meds Name = " + medicines.getMeds_name()).toList() : "null") +
                "\nbeds=" + (beds != null ? beds.stream().map(Bed::getBedId).toList() : "null") +
                "\nappointments=" + (appointments != null ? appointments.size() + " appointments" : "null") +
                "\nemergency=" + (emergency != null ? emergency.toString() : "null") +
                "}\n";
    }

}