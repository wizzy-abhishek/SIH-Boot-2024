package com.hospital.hospital.service;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo ;

    @Transactional
    public Doctor saveDoctor(Doctor doctor){
        return doctorRepo.save(doctor);
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
}
