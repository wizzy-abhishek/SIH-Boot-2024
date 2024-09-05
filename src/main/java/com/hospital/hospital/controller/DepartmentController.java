package com.hospital.hospital.controller;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService ;

    @GetMapping("/department")
    public ModelAndView viewPage(ModelAndView modelAndView) {
        List<Department> departments = departmentService.getAllDepartments();
        modelAndView.addObject("departments", departments);
        modelAndView.setViewName("department");
        return modelAndView;
    }

    @PostMapping("/getDetails")
    public ModelAndView getDeptDetails(String departmentList, ModelAndView modelAndView) {
        Department department = departmentService.findByIdIgnoreCase(departmentList);
        if (department != null) {
            modelAndView.setViewName("department");
            modelAndView.addObject("departmentDetails" , departmentService.findById(departmentList));
            modelAndView.addObject("numOfPatient" , departmentService.findById(departmentList).getPatients().size());
            modelAndView.addObject("numOfDoctors" , departmentService.findById(departmentList).getDoctors().size());
            List<Department> departments = departmentService.getAllDepartments();
            modelAndView.addObject("departments", departments);
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Department not found");
        }
        return modelAndView;
    }

    @PostMapping("addDepartment")
    public ModelAndView addDeptInDb(String dName, ModelAndView modelAndView) {

        boolean departmentExists = departmentService.doesDepartmentExist(dName);

        if (!departmentExists) {
            try {
                departmentService.addInDepartment(dName);
                Department savedDept = departmentService.findByIdIgnoreCase(dName);
                modelAndView.setViewName("department");
                modelAndView.addObject("departmentSaved", savedDept);
                List<Department> departments = departmentService.getAllDepartments();
                modelAndView.addObject("departments", departments);
            } catch (Exception e) {
                modelAndView.setViewName("error");
                modelAndView.addObject("error", e.getStackTrace());
                return modelAndView;
            }
        } else {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "The department is already there");
            return modelAndView;
        }
        return modelAndView;
    }




}
