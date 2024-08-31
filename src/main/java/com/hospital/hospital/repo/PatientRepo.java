package com.hospital.hospital.repo;

import com.hospital.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepo extends JpaRepository<Patient , String> {

    List<Patient> findByMobile(String mobile);
    List<Patient> findByName(String name) ;
}
