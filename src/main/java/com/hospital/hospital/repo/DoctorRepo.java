package com.hospital.hospital.repo;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, String> {

    @Query("SELECT d FROM Doctor d WHERE d.name = :name")
    Doctor findByName(@Param("name") String name);

    @Query("SELECT d FROM Doctor d WHERE d.dept = :dept")
    List<Doctor> findByDept(@Param("dept") Department dept);

}
