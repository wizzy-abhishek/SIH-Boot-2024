package com.hospital.hospital.repo;

import com.hospital.hospital.model.Bed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BedRepo extends JpaRepository<Bed , String> {

    List<Bed> findByIsAllotted(boolean input);
}
