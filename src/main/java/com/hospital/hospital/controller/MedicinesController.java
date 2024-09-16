package com.hospital.hospital.controller;

import com.hospital.hospital.model.Medicines;
import com.hospital.hospital.model.PatientAssignedMeds;
import com.hospital.hospital.service.MedicineService;
import com.hospital.hospital.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MedicinesController {

    private final MedicineService medicineService ;

    private final PatientService patientService ;

    public MedicinesController(MedicineService medicineService, PatientService patientService) {
        this.medicineService = medicineService;
        this.patientService = patientService;
    }

    @GetMapping(path = "/medicines")
    public ModelAndView medicineView(ModelAndView modelAndView){
        modelAndView.setViewName("medicines");

        List<Medicines> listOfMeds = medicineService.findAllMeds();
        modelAndView.addObject("ListOfMeds" , listOfMeds);

        return modelAndView ;
    }

    @PostMapping(path = "newArrivedMeds")
    public ModelAndView addNewMeds(@RequestParam("MedicineName") String nameOfMed ,
                                   ModelAndView modelAndView ){

        medicineService.addNewMed(nameOfMed);

        modelAndView.setViewName("medicines");

        List<Medicines> listOfMeds = medicineService.findAllMeds();
        modelAndView.addObject("ListOfMeds" , listOfMeds);

        return modelAndView ;
    }

    @PostMapping(path = "MedsToPatient")
    public ModelAndView assignMedsToPatient(@RequestParam("patientId") String patientId,
                                            @RequestParam("ListOfMeds") String medicinesName,
                                            @RequestParam("allotQuantity") Integer quantity,
                                            ModelAndView modelAndView) {

        boolean isPatientExists = patientService.isPatientExists(patientId);

        if (!isPatientExists) {
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "PATIENT DOESN'T EXIST");
            return modelAndView;
        }

        Medicines medicines = medicineService.findByIdByName(medicinesName);

        if(medicines.getQuantity() < quantity){
            modelAndView.setViewName("error");
            modelAndView.addObject("error", "Medicine less than required");
            return modelAndView;
        }

            // Assign the medicine to the patient (service layer will handle medicine stock check and assignment)
            patientService.assignMedsToPatient(patientId, medicinesName, quantity);

            // Set the view to show the patient's assigned meds
            modelAndView.setViewName("medicines");

            // Fetch the updated list of assigned medicines for the patient
            List<PatientAssignedMeds> patientMeds = patientService.getPatientMeds(patientId);
            modelAndView.addObject("patientMeds", patientMeds);


        return modelAndView;
    }


    @PostMapping(path = "quantityOfMeds")
    public ModelAndView addQuantityOfMeds(@RequestParam("ListOfMeds") String medsName ,
                                          @RequestParam("quantity") Integer quantity ,
                                          ModelAndView modelAndView){

        modelAndView.setViewName("medicines");

        List<Medicines> listOfMeds = medicineService.findAllMeds();
        modelAndView.addObject("ListOfMeds" , listOfMeds);

        medicineService.addQuantityOfMeds(medsName , quantity);

        return modelAndView ;
    }

}
