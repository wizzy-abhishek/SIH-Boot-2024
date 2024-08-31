package com.hospital.hospital.service.queueService;

import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.repo.queueRepo.QueueRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptService {

    private final QueueRepo queueEntityRepository;

    public DeptService(QueueRepo queueEntityRepository) {
        this.queueEntityRepository = queueEntityRepository;
    }

    @Transactional
    public void resetQueueForDept(Dept dept) {
        // Remove all QueueEntity items associated with the specified department
        queueEntityRepository.deleteByDept(dept);
    }
}
