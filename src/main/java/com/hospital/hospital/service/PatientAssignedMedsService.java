package com.hospital.hospital.service;

import com.hospital.hospital.model.PatientAssignedMeds;
import com.hospital.hospital.repo.PatientAssignedMedsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientAssignedMedsService {

    private final PatientAssignedMedsRepo patientAssignedMedsRepository;

    public PatientAssignedMedsService(PatientAssignedMedsRepo patientAssignedMedsRepository) {
        this.patientAssignedMedsRepository = patientAssignedMedsRepository;
    }

    // Fetch all medicines for a specific patient
    public List<PatientAssignedMeds> getMedsByPatient(String aadharNumber) {
        return patientAssignedMedsRepository.findByPatientAadharNumber(aadharNumber);
    }

    // Assign a new medicine to a patient
    public PatientAssignedMeds assignMedicineToPatient(PatientAssignedMeds patientAssignedMeds) {
        return patientAssignedMedsRepository.save(patientAssignedMeds);
    }
}
