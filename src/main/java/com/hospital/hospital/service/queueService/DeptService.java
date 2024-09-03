package com.hospital.hospital.service.queueService;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.repo.queueRepo.DeptRepo;
import com.hospital.hospital.repo.queueRepo.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptService {

    private final QueueRepo queueEntityRepository;

    @Autowired
    private DeptRepo deptRepo ;

    public DeptService(QueueRepo queueEntityRepository) {
        this.queueEntityRepository = queueEntityRepository;
    }

    @Transactional
    public void resetQueueForDept(Dept dept) {
        // Remove all QueueEntity items associated with the specified department
        queueEntityRepository.deleteByDept(dept);
    }

    @Transactional
    public void saveDept(Dept dept){
        deptRepo.saveAndFlush(dept);
    }

    public List<Dept> getAllDepartments() {
        return deptRepo.findAll();
    }
}
