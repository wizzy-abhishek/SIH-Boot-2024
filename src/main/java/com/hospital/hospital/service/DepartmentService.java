package com.hospital.hospital.service;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Transactional
    public void addDepartment(Department dept){
        departmentRepo.saveAndFlush(dept);
    }

    public Department findByName(String deptName){
        return departmentRepo.findById(deptName).orElse(new Department());
    }

    public List<Department> findAll(){
        return departmentRepo.findAll();
    }

    public Department findById(String name){
        return departmentRepo.findById(name).orElse(new Department());
    }

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
}
