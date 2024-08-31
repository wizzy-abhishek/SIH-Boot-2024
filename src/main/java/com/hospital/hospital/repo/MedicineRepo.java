package com.hospital.hospital.repo;

import com.hospital.hospital.model.Medicines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<Medicines, String> {
}

