package com.hospital.hospital.service;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.repo.DoctorRepo;
import com.hospital.hospital.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo ;

    @Autowired
    private DepartmentService departmentService ;

    @Autowired
    private PatientRepo patientRepo ;

    @Transactional
    public void saveDoctor(Doctor doctor){
        doctorRepo.save(doctor);
    }

    public List<Doctor> findAll(){
        return doctorRepo.findAll();
    }

    public Doctor findByName(String name){
        return doctorRepo.findByName(name);
    }

    public List<Doctor> findByDept(Department deptIn) {
        return doctorRepo.findByDept(deptIn);
    }

    public Doctor findById(String docId) {
        return doctorRepo.findById(docId).orElse(null);
    }

    public boolean isAvailable(String doctorId){
        return  doctorRepo.existsById(doctorId);
    }

    public void addNewDoctor(String doctorId , String docName , Department department){

        Doctor doctor = new Doctor() ;

        doctor.setId(doctorId);
        doctor.setName(docName);
        doctor.setDept(department);

        doctorRepo.save(doctor);

    }

    public boolean assignDoctorToPatient(String patientId, String doctorId) {
        Doctor doctor = doctorRepo.findById(doctorId).orElse(null);
        Patient patient = patientRepo.findById(patientId).orElse(null);

        if (doctor == null || patient == null) {
            throw new IllegalArgumentException("Invalid patient ID or doctor ID");
        }

        // Check if the doctor is already assigned to the patient
        if (doctor.getPatients().contains(patient)) {
            return false; // Doctor is already assigned to this patient
        }

        // Assign the doctor to the patient
        doctor.getPatients().add(patient);
        patient.getDoctors().add(doctor);

        // Save the changes
        doctorRepo.save(doctor);
        patientRepo.save(patient);

        return true; // Doctor successfully assigned to the patient
    }
}
