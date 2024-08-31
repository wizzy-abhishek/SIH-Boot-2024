package com.hospital.hospital.controller;

import com.hospital.hospital.model.Appointment;
import com.hospital.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Arrays;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService ;

    @RequestMapping("Appointment")
    public String appointmentPage(){
        return "Appointment" ;
    }

    @GetMapping("appointmentForm")
    public ModelAndView appointmentBooking(Appointment appointment , ModelAndView modelAndView){
        System.out.println("Appointment Booking Called");
        try {
            appointmentService.bookAppointment(appointment);
            modelAndView.addObject("appointmentBooked" , appointmentService.findByObj(appointment));
            modelAndView.setViewName("Success");
        }catch (ParseException e) {
            modelAndView.setViewName("Error");
            modelAndView.addObject("error", Arrays.toString(e.getStackTrace()));
            return modelAndView ;
        }
        return modelAndView ;
    }

}
