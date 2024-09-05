package com.hospital.hospital.service;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.repo.DepartmentRepo;
import com.hospital.hospital.repo.queueRepo.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DeptRepo deptRepo ;

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

    public Department findByIdIgnoreCase(String dName) {
        return departmentRepo.findByNameIgnoreCase(dName).orElse(null);
    }

    @Transactional
    public void addInDepartment(String deptName){
        Department department = new Department() ;
        Dept dept = new Dept() ;

        department.setName(deptName);
        dept.setName(deptName);

        departmentRepo.save(department);
        deptRepo.save(dept);
    }

    public boolean doesDepartmentExist(String dName) {
        return departmentRepo.existsByNameIgnoreCase(dName);
    }
}
