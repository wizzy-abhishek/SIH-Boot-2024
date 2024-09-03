package com.hospital.hospital.controller.queueController;

import com.hospital.hospital.model.queueModel.Dept;
import com.hospital.hospital.service.queueService.DeptService;
import com.hospital.hospital.service.queueService.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class QueueController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private DeptService deptService;

    // Handle all GET requests to display the home/queue page
    @GetMapping({"/queue", "/home", "/"})
    public String showHomePage(Model model) {
        try {
            List<Dept> departments = deptService.getAllDepartments();
            model.addAttribute("departments", departments);
            return "home";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error loading departments. Please try again later.");
            return "error";  // Display a generic error page
        }
    }

    @PostMapping("/queue")
    public ModelAndView handleQueueRequest(@RequestParam("doctors") int num_of_doctor,
                                           @RequestParam("department") String deptName,
                                           ModelAndView modelAndView) {
        try {
            Map<String, Object> queueData = queueService.queueImplement(num_of_doctor, deptName);
            List<Dept> departments = deptService.getAllDepartments();
            modelAndView.setViewName("home");
            modelAndView.addObject("queueData", queueData);
            modelAndView.addObject("departments", departments);
            return modelAndView;
        } catch (IllegalArgumentException e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", e.getMessage());
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "An unknown error occurred. Please contact support.");
            return modelAndView;
        }
    }

    // Handle the reset of a specific department queue
    @GetMapping("/reset")
    public ModelAndView resetDepartmentQueue(@RequestParam("department") Dept deptName,
                                             ModelAndView modelAndView) {
        try {
            Dept department = queueService.resetPatientNumbers(deptName);
            if (department == null) {
                modelAndView.setViewName("error");
                modelAndView.addObject("errorMessage", "Department not found.");
                return modelAndView;
            }
            List<Dept> departments = deptService.getAllDepartments();
            modelAndView.setViewName("home");
            modelAndView.addObject("departments", departments);
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Failed to reset the department queue. Please try again.");
            return modelAndView;
        }
    }

    // Handle the reset of all department queues
    @GetMapping("/reset_All")
    public ModelAndView resetAllQueues(ModelAndView modelAndView) {
        try {
            queueService.resetAllPatientNumbers();
            List<Dept> departments = deptService.getAllDepartments();
            modelAndView.setViewName("home");
            modelAndView.addObject("departments", departments);
            return modelAndView;
        } catch (Exception e) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMessage", "Failed to reset all queues. Please try again.");
            return modelAndView;
        }
    }

    // Global exception handler for this controller
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", "An unexpected error occurred: " + e.getMessage());
        return modelAndView;
    }
}
