package com.hospital.hospital.repo.queueRepo;

import com.hospital.hospital.model.queueModel.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeptRepo extends JpaRepository<Dept, String> {
    Optional<Dept> findByName(String name);
}
