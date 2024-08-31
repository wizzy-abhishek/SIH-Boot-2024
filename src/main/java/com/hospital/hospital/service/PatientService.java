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
    public void patientSave(Patient patient) throws ParseException {

        List<Date> date = List.of(new Date());
        patient.setDates(date);

        System.out.println("Enter Aadhaar number");
        String aadhaar = patient.getAadharNumber() ;
        patient.setAadharNumber(aadhaar);

        System.out.println("Enter name");
        String name = patient.getName();
        patient.setName(name);

        System.out.println("Enter mobile");
        String mobile = patient.getMobile();
        patient.setMobile(mobile);

        System.out.println("Enter date of birth yyyy-mm-dd format");
        String dob = patient.getDate_of_Birth();
        patient.setDate_of_Birth(dob);


        List<Department> deptList = deptService.findAll();
        System.out.println(deptList);

        System.out.println("Enter the Dept for check-up");
        String deptInn = patient.getDepartmentList().getFirst().getName();
        Department deptIn = deptService.findById(deptInn);
        patient.getDepartmentList().add(deptIn);
        deptIn.getPatients().add(patient);


/*
        List<Doctor> docList = doctorService.findByDept(deptIn);
        System.out.println(docList);

        System.out.println("Enter the Doc Id");
        String docId = patient.getDoctors().getFirst().getId();
        Doctor doctor = doctorService.findById(docId);
        patient.getDoctors().add(doctor);
        doctor.getPatients().add(patient);

*/

        patientRepo.save(patient);
        deptService.addDepartment(deptIn);
     //   doctorService.saveDoctor(doctor);

      //  patientRepo.save(patient);

    }

    public List<Patient> findByMobile(String mobile){
        return patientRepo.findByMobile(mobile);
    }

    public List<Patient> findByName(String name){
        return patientRepo.findByName(name) ;
    }

    public Patient findById(String aadhar){
        return patientRepo.findById(aadhar).orElse(new Patient());
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
