package com.hospital.hospital.repo;

import com.hospital.hospital.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, String> {

    boolean existsByPatient_AadharNumber(String aadharNumber);
    List<Appointment> findByPatient_AadharNumber(String patientId);
}

