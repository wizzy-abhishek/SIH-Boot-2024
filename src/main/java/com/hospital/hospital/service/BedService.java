package com.hospital.hospital.service;

import com.hospital.hospital.model.Bed;
import com.hospital.hospital.repo.BedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedRepo bedRepo ;

    @Transactional
    public Bed saveBed(Bed bed){
        return bedRepo.save(bed);
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
}

