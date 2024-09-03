package com.hospital.hospital.service;

import com.hospital.hospital.model.Bed;
import com.hospital.hospital.model.Emergency;
import com.hospital.hospital.repo.BedRepo;
import com.hospital.hospital.repo.EmergencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedRepo bedRepo ;

    @Autowired
    private PatientService patientService ;

    @Transactional
    public void saveBed(Bed bed){
        bed.setPatient(null);
        bed.setAllotted(false);
        bedRepo.saveAndFlush(bed);
    }

    @Transactional
    public String assignBed(String bedId , String patientId){
        Bed bed = findBedById(bedId);
        if (bed.getPatient() == null) {
            bed.setPatient(patientService.findPatientById(patientId));
            bed.setAllotted(true);
            saveBed(bed);
        }else{
           return "Bed Occupied" ;
        }
        return "Success" ;
    }

    public List<Bed> findByIsAllotted(){
        return bedRepo.findByIsAllotted(false);
    }

    public List<Bed> findAll(){
        return bedRepo.findAll();
    }

    public Bed findById(String bedId){
        return bedRepo.findById(bedId).orElse(null);
    }

    public Bed findBedById(String bedId) {
        return bedRepo.findById(bedId).orElse(null);
    }
}

