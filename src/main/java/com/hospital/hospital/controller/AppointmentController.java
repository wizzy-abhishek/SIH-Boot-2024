package com.hospital.hospital.controller;

import com.hospital.hospital.model.Appointment;
import com.hospital.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Arrays;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService ;

    @RequestMapping("appointment")
    public String appointmentPage(){
        return "appointment" ;
    }

    @PostMapping("/appointmentForm")
    public ModelAndView appointmentBooking(@RequestParam("date") String date , @RequestParam("patient_id") String patient_id , @RequestParam("doctor_Id") String doctor_Id , ModelAndView modelAndView){

        System.out.println("Appointment Booking Called");
            Appointment appointment = appointmentService.bookAppointment(date , patient_id , doctor_Id);
            if(appointment != null) {
                modelAndView.addObject("appointmentBooked", appointment );
                modelAndView.setViewName("appointment");
            }else {
                modelAndView.addObject("error", "NULL");
                modelAndView.setViewName("error");

            }
        return modelAndView ;
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
}
