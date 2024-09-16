package com.hospital.hospital.repo;

import com.hospital.hospital.model.PatientAssignedMeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientAssignedMedsRepo extends JpaRepository<PatientAssignedMeds, Long> {

    List<PatientAssignedMeds> findByPatientAadharNumber(String aadharNumber);
}
