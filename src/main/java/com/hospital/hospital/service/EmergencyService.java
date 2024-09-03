package com.hospital.hospital.service;

import com.hospital.hospital.model.Bed;
import com.hospital.hospital.model.Emergency;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.repo.EmergencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
public class EmergencyService {

    @Autowired
    private EmergencyRepo emergencyRepository;

    @Autowired
    private PatientService patientService;

    @Transactional
    public void add_E_bed(Emergency emergency){
        emergencyRepository.save(emergency);
    }



}
