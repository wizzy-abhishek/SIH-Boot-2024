package com.hospital.hospital.repo;

import com.hospital.hospital.model.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyRepo extends JpaRepository<Emergency , String> {

}
