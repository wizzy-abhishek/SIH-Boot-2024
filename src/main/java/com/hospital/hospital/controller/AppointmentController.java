package com.hospital.hospital.controller;

import com.hospital.hospital.model.Appointment;
import com.hospital.hospital.model.Department;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Patient;
import com.hospital.hospital.service.AppointmentService;
import com.hospital.hospital.service.DepartmentService;
import com.hospital.hospital.service.DoctorService;
import com.hospital.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService ;

    @Autowired
    private DoctorService doctorService ;

    @Autowired
    private PatientService patientService ;

    @Autowired
    private DepartmentService departmentService ;

    @RequestMapping("appointment")
    public String appointmentPage(){
        return "appointment" ;
    }

    @PostMapping("/appointmentForm")
    public ModelAndView appointmentBooking(@RequestParam("date") String date, @RequestParam("patient_id") String patientId, @RequestParam("doctor_Id") String doctorId, ModelAndView modelAndView) {

        System.out.println("Appointment Booking Called");

        // Retrieve the doctor by ID
        Doctor doctor = doctorService.findById(doctorId);

        if (doctor == null) {
            modelAndView.addObject("error", "Doctor not found");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        // Retrieve the patient by ID
        Patient patient = patientService.findPatientById(patientId);

        if (patient == null) {
            modelAndView.addObject("error", "Patient not found");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        // Check if the patient is already assigned to the doctor
        boolean isPatientAssignedToDoctor = doctor.getPatients().stream()
                .anyMatch(p -> p.getAadharNumber().equals(patientId));

        // Check if the patient is already in the department
        Department department = doctor.getDept();
        boolean isPatientInDepartment = department.getPatients().stream()
                .anyMatch(p -> p.getAadharNumber().equals(patientId));

        // If the patient is not assigned to the doctor, assign them
        if (!isPatientAssignedToDoctor) {
            doctor.getPatients().add(patient);
            doctorService.saveDoctor(doctor);  // Save the updated doctor
        }

        // If the patient is not in the department, add them
        if (!isPatientInDepartment) {
            department.getPatients().add(patient);
            departmentService.addDepartment(department);  // Save the updated department
        }

        // Proceed to book the appointment
        Appointment appointment = appointmentService.bookAppointment(date, patientId, doctorId);

        if (appointment != null) {
            modelAndView.addObject("appointmentsBooked", Collections.singletonList(appointment)); // Note the change here
            modelAndView.setViewName("appointment");
        } else {
            modelAndView.addObject("error", "Appointment could not be booked");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }



    @DeleteMapping
    public String deleteAppointment(String appointmentId){
        appointmentService.deleteAppointmentById(appointmentId);
        return "success" ;
    }

    @PutMapping
    public ModelAndView updateAppointment(String appointmentId , String date , ModelAndView modelAndView){
        System.out.println("Appointment update called");
        return modelAndView ;
    }

    @GetMapping("search_Appointment")
    public ModelAndView searchPatientAppointments(@RequestParam("searchPatientAppointment") String patientId, ModelAndView modelAndView) {
        System.out.println("Appointment Controller: Search Patient Appointments");

        boolean found = appointmentService.isAppointmentExistForPatient(patientId);

        if (!found) {
            modelAndView.addObject("error", "No appointments found for the patient");
            modelAndView.setViewName("error");
            return modelAndView;
        }

        List<Appointment> appointments = appointmentService.findAppointmentByPatientId(patientId);

        System.out.println(appointments);

        if (appointments != null && !appointments.isEmpty()) {
            modelAndView.addObject("appointmentsBooked", appointments);
            modelAndView.setViewName("appointment");
            System.out.println("Here only ");
        } else {
            modelAndView.addObject("error", "Appointment details not available");
            modelAndView.setViewName("error");
        }
        System.out.println(modelAndView.getViewName());
        return modelAndView;
    }

}
