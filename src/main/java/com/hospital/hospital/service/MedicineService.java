package com.hospital.hospital.service;

import com.hospital.hospital.model.Medicines;
import com.hospital.hospital.repo.MedicineRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicineService {


    private final MedicineRepo medicineRepo ;

    public MedicineService(MedicineRepo medicineRepo) {
        this.medicineRepo = medicineRepo;
    }

    @Transactional
    public void saveMeds(Medicines medicines){
        medicineRepo.save(medicines);
    }

    public void addNewMed(String medName){

        Medicines meds = new Medicines();
        meds.setMeds_name(medName);
        saveMeds(meds);

    }

    public List<Medicines> findAllMeds(){
        return medicineRepo.findAll();
    }

    public Medicines findByIdByName(String medsName){
        return medicineRepo.findById(medsName).orElse(null);
    }

    public void addQuantityOfMeds(String medsName, Integer quantity) {

        Medicines meds = findByIdByName(medsName);
        meds.setQuantity(meds.getQuantity() + quantity);
        saveMeds(meds);

    }
}