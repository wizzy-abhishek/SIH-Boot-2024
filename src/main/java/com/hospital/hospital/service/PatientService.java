package com.hospital.hospital.service;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.repo.PatientRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo ;

    @Autowired
    private DepartmentService deptService ;

    @Autowired
    private DoctorService doctorService ;

    @Transactional
    public Patient addNewPatient(String  aadhaar, String name, String mobile, String dob, Department department){

        Patient patient = new Patient() ;

        System.out.println("Enter DOB");
        patient.setDate_of_Birth(dob);

        System.out.println("Enter Aadhaar number");
        patient.setAadharNumber(aadhaar);

        System.out.println("Enter name");
        patient.setName(name);

        System.out.println("Enter mobile");
        patient.setMobile(mobile);

        List<Department> deptList = deptService.findAll();
        System.out.println(deptList);

        System.out.println("Enter the Dept for check-up");
        patient.getDepartmentList().add(department);

        patientRepo.save(patient);

/*
        List<Doctor> docList = doctorService.findByDept(deptIn);
        System.out.println(docList);

        System.out.println("Enter the Doc Id");
        String docId = patient.getDoctors().getFirst().getId();
        Doctor doctor = doctorService.findById(docId);
        patient.getDoctors().add(doctor);
        doctor.getPatients().add(patient);

*/
        department.getPatients().add(patient);

        deptService.addDepartment(department);

        return patientRepo.findById(aadhaar).orElse(null) ;

    }

    public void savePatientByObjectForAppointment(Patient patient){
        patientRepo.save(patient);
    }

    public List<Patient> findByMobile(String mobile){
        return patientRepo.findByMobile(mobile);
    }

    public List<Patient> findByName(String name){
        return patientRepo.findByName(name) ;
    }

    public Patient findPatientById(String aadhar){
        return patientRepo.findById(aadhar).orElse(null);
    }

    public List<Patient> findAll(){
        return patientRepo.findAll();
    }

    public Patient updatePatient(Patient patient){
        return patientRepo.save(patient);
    }

    public void delete(String pId){
        patientRepo.deleteById(pId);
    }

    public void deleteAll(){
        patientRepo.deleteAll();
    }


}
