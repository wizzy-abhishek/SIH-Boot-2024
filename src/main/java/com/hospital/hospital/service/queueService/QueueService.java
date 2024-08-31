package com.hospital.hospital.service.queueService;

import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.model.queueModel.QueueEntity;
import com.hospital.hospital.repo.queueRepo.DeptRepo;
import com.hospital.hospital.repo.queueRepo.QueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class QueueService {

    @Autowired
    private QueueRepo queueRepository;

    @Autowired
    private DeptRepo departmentRepository;

    @Autowired
    private ApplicationContext context;

    private final Lock lock = new ReentrantLock();

    // Maps to track the state for each department
    private final Map<String, Integer> departmentPatientNumbers = new HashMap<>();
    private final Map<String, Integer> departmentCurrentDoctor = new HashMap<>();
    private final Map<String, List<Queue<Integer>>> departmentQueues = new HashMap<>();

    @PostConstruct
    public void init() {
        // Load initial state for each department from the database if needed
    }

    @Transactional
    public Map<String, Object> queueImplement(int num_of_doctor, String dept_Name) throws IOException {
        Dept dept = departmentRepository.findByName(dept_Name)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        Map<String, Object> resultMap = new HashMap<>();

        lock.lock(); // Ensure thread-safety
        try {
            // Initialize queues for the department if not already done
            departmentQueues.computeIfAbsent(dept_Name, k -> {
                List<Queue<Integer>> newQueueList = new ArrayList<>();
                for (int i = 0; i < num_of_doctor; i++) {
                    newQueueList.add(new LinkedList<>());
                }
                return newQueueList;
            });

            List<Queue<Integer>> queueList = departmentQueues.get(dept_Name);

            int currentPatientNumber = getCurrentPatientNumber(dept);
            int currentDoctor = getCurrentDoctor(dept);

            QueueEntity newQueueEntity = context.getBean(QueueEntity.class);
            newQueueEntity.setPatientNumber(currentPatientNumber);
            newQueueEntity.setDepartment(dept);

            queueRepository.save(newQueueEntity);

            queueList.get(currentDoctor).add(currentPatientNumber);

            int queueSize = queueList.get(currentDoctor).size();

            resultMap.put("assignedDoctor", currentDoctor + 1);
            resultMap.put("queuePosition", queueSize);

            currentPatientNumber++;
            currentDoctor = (currentDoctor + 1) % num_of_doctor;

            resultMap.put("queues", queueList);

            // Update the state in the maps after the session ends
            updateCurrentPatientNumber(dept, currentPatientNumber);
            updateCurrentDoctor(dept, currentDoctor);

        } finally {
            lock.unlock();
        }

        return resultMap;
    }


    private int getCurrentPatientNumber(Dept department) {
        lock.lock(); // Ensure thread-safety
        try {
            return departmentPatientNumbers.computeIfAbsent(department.getName(), depName -> {
                // Retrieve the last patient number from the repository, if exists
                Optional<QueueEntity> lastEntry = queueRepository.findTopByDeptOrderByPatientNumberDesc(department);
                // Return last patient number + 1 if present, otherwise start from 1
                return lastEntry.map(QueueEntity::getPatientNumber).orElse(0) + 1;
            });
        } finally {
            lock.unlock();
        }
    }


    private void updateCurrentPatientNumber(Dept department, int nextNumber) {
        lock.lock(); // Ensure thread-safety
        try {
            departmentPatientNumbers.put(department.getName(), nextNumber);
        } finally {
            lock.unlock();
        }
    }

    private int getCurrentDoctor(Dept department) {
        lock.lock(); // Ensure thread-safety
        try {
            return departmentCurrentDoctor.computeIfAbsent(department.getName(), depName -> 0);
        } finally {
            lock.unlock();
        }
    }

    private void updateCurrentDoctor(Dept department, int nextDoctor) {
        lock.lock(); // Ensure thread-safety
        try {
            departmentCurrentDoctor.put(department.getName(), nextDoctor);
        } finally {
            lock.unlock();
        }
    }


    @Transactional
    public void resetPatientNumbers(String departmentName) {
        Dept department = departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));

        lock.lock(); // Ensure thread-safety
        try {
            queueRepository.deleteByDept(department); // Delete all records for the department
            departmentPatientNumbers.put(department.getName(), 1); // Reset patient number to 1
            departmentCurrentDoctor.put(department.getName(), 0); // Reset doctor assignment to first doctor
            departmentQueues.remove(department.getName()); // Clear queues
        } finally {
            lock.unlock();
        }
    }

    @Transactional
    public void resetAllPatientNumbers() {
        lock.lock(); // Ensure thread-safety
        try {
            queueRepository.deleteAll(); // Delete all records from the DB
            departmentPatientNumbers.clear(); // Clear the patient number map
            departmentCurrentDoctor.clear(); // Clear the doctor assignment map
            departmentQueues.clear(); // Clear all department queues
        } finally {
            lock.unlock();
        }
    }
}