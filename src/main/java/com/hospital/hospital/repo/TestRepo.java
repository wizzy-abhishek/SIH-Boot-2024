package com.hospital.hospital.repo;


import com.hospital.hospital.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<Test, String> {
}
