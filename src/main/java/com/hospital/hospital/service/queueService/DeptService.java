package com.hospital.hospital.service.queueService;

import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.repo.queueRepo.DeptRepo;
import com.hospital.hospital.repo.queueRepo.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class DeptService {

    private final QueueRepo queueEntityRepository;

    @Autowired
    private DeptRepo deptRepo ;

    private final Lock lock = new ReentrantLock(); // Lock to ensure thread safety

    public DeptService(QueueRepo queueEntityRepository) {
        this.queueEntityRepository = queueEntityRepository;
    }

    @Transactional
    public void resetQueueForDept(Dept dept) {
        lock.lock();
        try {
            // Remove all QueueEntity items associated with the specified department
            queueEntityRepository.deleteByDept(dept);
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void saveDept(Dept dept){
        lock.lock();
        try {
            deptRepo.saveAndFlush(dept);
        } finally {
            lock.unlock();
        }
    }

    public List<Dept> getAllDepartments() {
        lock.lock();
        try {
            return deptRepo.findAll();
        } finally {
            lock.unlock();
        }
    }
}
