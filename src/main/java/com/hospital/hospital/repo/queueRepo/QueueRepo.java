package com.hospital.hospital.repo.queueRepo;

import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.model.queueModel.QueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QueueRepo extends JpaRepository<QueueEntity, Long> {
    Optional<QueueEntity> findTopByDeptOrderByPatientNumberDesc(Dept dept);

    @Modifying
    @Query("DELETE FROM QueueEntity q WHERE q.dept = :dept")
    void deleteByDept(@Param("dept") Dept dept);
}
