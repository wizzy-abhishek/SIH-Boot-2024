package com.hospital.hospital.service;

import com.hospital.hospital.model.Medicines;
import com.hospital.hospital.repo.MedicineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicineService {
    @Autowired
    private MedicineRepo medicineRepo ;

    @Transactional
    public Medicines saveMeds(Medicines medicines){
        return medicineRepo.save(medicines);
    }

}