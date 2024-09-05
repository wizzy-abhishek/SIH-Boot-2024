package com.hospital.hospital.controller;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.service.DepartmentService;
import com.hospital.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping({"patient"})
    public String patientForm(Model model){
        System.out.println("In Patient Form controller ");

        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "patient";
    }

    @PostMapping("patient")
    public ModelAndView addPatient(@RequestParam("aadhaarNumber") String aadhaar,
                                   @RequestParam("name") String name,
                                   @RequestParam("mobile") String mobile,
                                   @RequestParam("date_of_Birth") String dob,
                                   @RequestParam("departmentList") Department department,
                                   ModelAndView modelAndView) {

        System.out.println("ADD PATIENT METHOD CALLED IN CONTROLLER");

        if(patientService.isPatientExists(aadhaar)) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Patient already exists");
            return modelAndView;
        }

        // Add new patient
        Patient patient = patientService.addNewPatient(aadhaar, name, mobile, dob, department);

        if (patient != null) {
            modelAndView.addObject("patientDetail", patient);
            modelAndView.setViewName("patient");
            List<Department> departments = departmentService.getAllDepartments();
            modelAndView.addObject("departments", departments);
        } else {
            System.out.println("Exception occurred while adding patient");

            modelAndView.setViewName("error");
            modelAndView.addObject("error", "ERROR OCCURRED");

            return modelAndView;
        }

        System.out.println("Patient added successfully");
        return modelAndView;
    }

    @GetMapping("search_Patient")
    public ModelAndView patientSearch(@RequestParam("searchPatient") String patientId , ModelAndView modelAndView){

        System.out.println("Patient Controller :- Search column ");

        Patient patient = patientService.findPatientById(patientId);

        if(patient == null){
            modelAndView.addObject("error" , "NOT FOUND");
            modelAndView.setViewName("error");
            return modelAndView ;
        }

        modelAndView.addObject("patientDetail" , patient);
        modelAndView.setViewName("patient");
        List<Department> departments = departmentService.getAllDepartments();
        modelAndView.addObject("departments", departments);

        return modelAndView ;
    }
}
