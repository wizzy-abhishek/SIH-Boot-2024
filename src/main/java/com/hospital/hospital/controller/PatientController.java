package com.hospital.hospital.controller;

import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.service.DepartmentService;
import com.hospital.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping({"patient" , "/"})
    public String patientForm(Model model){
        System.out.println("In Patient Form");

        List<Department> departments = departmentService.getAllDepartments();
        model.addAttribute("departments", departments);

        return "NewPatient";
    }

    @PostMapping("patients")
    public ModelAndView addPatient(Patient patient , ModelAndView modelAndView){
        System.out.println("ADD PATIENT");
        System.out.println(patient.getDate_of_Birth());
        try {
            patientService.patientSave(patient);
            modelAndView.addObject("patientDetails" , patientService.findById(patient.getAadharNumber()));
            modelAndView.setViewName("Success");
        }catch (ParseException exception){
            System.out.println(Arrays.toString(exception.getStackTrace()));
            System.out.println("Exception");
            modelAndView.setViewName("Error");
            modelAndView.addObject("error",Arrays.toString(exception.getStackTrace()));
            return modelAndView ;
        }
        System.out.println("Success");
        return modelAndView ;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
